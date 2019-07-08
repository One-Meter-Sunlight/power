package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.dao.RecordMapper;
import com.imooc.power.dto.EnergyDTO;
import com.imooc.power.entity.Record;
import com.imooc.power.service.IRecordService;
import com.imooc.power.util.DateUtils;
import com.imooc.power.vo.EnergyStatisticsVO;
import com.imooc.power.vo.RecordStatisticsVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    private static final Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 近两个月总能耗统计
     *
     * @param command 请求参数
     * @return
     * @throws ParseException
     */
    @Override
    public EnergyStatisticsVO getTotalEnergyStatistics(EnergyStatisticsCommand command) throws RuntimeException {
        EnergyStatisticsVO vo = new EnergyStatisticsVO();

        if(null == command.getLocationFactoryNumb() || StringUtils.isBlank(command.getLocationFactoryNumb())) {
            return vo;
        }

        // 获得表格标题并统计当月和上月总能耗
        getTableTitleAndCalcTotalEnergy(command, vo);

        // 返回对象
        return vo;
    }

    /**
     * 获得表格标题并统计当月和上月总能耗
     *
     * @param command 请求参数
     * @param vo      返回对象
     */
    private void getTableTitleAndCalcTotalEnergy(EnergyStatisticsCommand command, EnergyStatisticsVO vo) {
        // 查询条件
        Date preDate = DateUtils.addMonth(-1);
        String query_date_current_month = DateUtils.format(new Date(), DateUtils.DATE_PATTERN_YYYY_MM);
        String query_date_pre_month = DateUtils.format(preDate, DateUtils.DATE_PATTERN_YYYY_MM);

        // 上月时间和上上月月份字符串
        String preDateStr;
        String pre2DateStr;

        if (StringUtils.isNoneBlank(command.getDate())) {
            try {
                Date selectDate = DateUtils.passDate(command.getDate(), DateUtils.DATE_PATTERN_YYYY_MM);

                // 上月时间和上上月时间
                Date date = DateUtils.addMonth(selectDate, -1);
                preDateStr = DateUtils.format(date, DateUtils.DATE_PATTERN_MM);

                Date date2 = DateUtils.addMonth(selectDate, -2);
                pre2DateStr = DateUtils.format(date2, DateUtils.DATE_PATTERN_MM);

                query_date_current_month = command.getDate();
                query_date_pre_month = DateUtils.format(date, DateUtils.DATE_PATTERN_YYYY_MM);
            } catch (Exception e) {
                log.error("时间转换异常：" + e.getMessage());
                throw new RuntimeException("系统异常");
            }
        } else {
            try {
                // 上月时间和上上月时间
                Date date = DateUtils.addMonth(-1);
                preDateStr = DateUtils.format(date, DateUtils.DATE_PATTERN_MM);

                Date date2 = DateUtils.addMonth(-2);
                pre2DateStr = DateUtils.format(date2, DateUtils.DATE_PATTERN_MM);
            } catch (Exception e) {
                log.error("时间转换异常：" + e.getMessage());
                throw new RuntimeException("系统异常");
            }
        }

        // 设置标题
        vo.setIdStr("编号");
        vo.setCurrentIdStr("本月能耗");
        vo.setPreIdStr(preDateStr + "月用电");
        vo.setPre2IdStr(pre2DateStr + "月用电");

        // 统计总能耗
        calcCurrAndPreEnergy(query_date_current_month, query_date_pre_month, command, vo);
    }

    /**
     * 统计本月和上月总能耗
     *
     * @param currentDate 当前年月字符串
     * @param preDate     上月年月字符串
     * @param vo          返回结果对象
     */
    private void calcCurrAndPreEnergy(String currentDate, String preDate, EnergyStatisticsCommand command, EnergyStatisticsVO vo) {
        // 所有仪表能耗记录
        List<EnergyDTO> energyDTOList = recordMapper.selectEnergyDTOList(Lists.newArrayList(currentDate, preDate),
                command.getLocationFactoryNumb(), command.getMeterNumbs());
        long energy = 0;
        long preEnergy = 0;
        if (null != energyDTOList && energyDTOList.size() > 0) {
            // 将数据转换成Map结构数据，Key = 年月， value为仪表能耗集合
            Map<String, List<EnergyDTO>> energyMap = energyDTOList.stream().collect(Collectors.groupingBy(EnergyDTO::getDate));
            // 当月所有仪表能耗集合
            List<EnergyDTO> currentList = energyMap.get(currentDate);
            // 上月所有仪表能耗集合
            List<EnergyDTO> preList = energyMap.get(preDate);

            if (null != currentList && currentList.size() > 0) {
                for (int i = 0, len = currentList.size(); i < len; i++) {
                    EnergyDTO dto = currentList.get(i);
                    energy += dto.getMax() - dto.getMin();
                }
            }

            if (null != preList && preList.size() > 0) {
                for (int i = 0, len = preList.size(); i < len; i++) {
                    EnergyDTO dto = currentList.get(i);
                    preEnergy += dto.getMax() - dto.getMin();
                }
            }
        }

        vo.setCurrentMonthTotalKwhStr(energy + "kwh");
        vo.setPreMonthTotalKwhStr(preEnergy + "kwh");
    }

    /**
     * 仪表能耗分页统计
     *
     * @param command 请求参数
     * @return
     */
    @Override
    public Page<RecordStatisticsVO> getPageRecordStatistics(EnergyStatisticsCommand command) {
        long startTime = System.currentTimeMillis();

        // 查询条件
        Date preDate = DateUtils.addMonth(-1);
        Date pre2Date = DateUtils.addMonth(-2);
        String query_date_current_month = DateUtils.format(new Date(), DateUtils.DATE_PATTERN_YYYY_MM);
        String query_date_pre_month = DateUtils.format(preDate, DateUtils.DATE_PATTERN_YYYY_MM);
        String query_date_pre2_month = DateUtils.format(pre2Date, DateUtils.DATE_PATTERN_YYYY_MM);

        if (StringUtils.isNoneBlank(command.getDate())) {
            try {
                Date selectDate = DateUtils.passDate(command.getDate(), DateUtils.DATE_PATTERN_YYYY_MM);
                query_date_current_month = command.getDate();

                // 上月时间和上上月时间
                Date date = DateUtils.addMonth(selectDate, -1);
                query_date_pre_month = DateUtils.format(date, DateUtils.DATE_PATTERN_YYYY_MM);

                Date date2 = DateUtils.addMonth(selectDate, -2);
                query_date_pre2_month = DateUtils.format(date2, DateUtils.DATE_PATTERN_YYYY_MM);
            } catch (Exception e) {
                log.error("时间转换异常：" + e.getMessage());
                throw new RuntimeException("系统异常");
            }
        }

        // 分页查询
        Page<RecordStatisticsVO> page = new Page<>(command.getCurrent(), command.getSize());

        if(null == command.getLocationFactoryNumb() || StringUtils.isBlank(command.getLocationFactoryNumb())) {
            return page;
        }

        Map<String, Object> condition = new HashMap<>();
        if (StringUtils.isNotBlank(command.getLocationFactoryNumb())) {
            condition.put("locationFactoryNumb", command.getLocationFactoryNumb());
        }
        if (null != command.getMeterNumbs() && command.getMeterNumbs().size() > 0) {
            condition.put("meterNumbs", command.getMeterNumbs());
        }
        condition.put("query_date_current_month", query_date_current_month);
        condition.put("query_date_pre_month", query_date_pre_month);
        condition.put("query_date_pre2_month", query_date_pre2_month);

        List<RecordStatisticsVO> recordStatisticsVOList = recordMapper.selectRecordStatisticsList(page, condition);
        page.setRecords(recordStatisticsVOList);

        log.info(">>>>>> 分页查询报警信息总耗时：[{}]ms", System.currentTimeMillis() - startTime);
        return page;
    }

}
