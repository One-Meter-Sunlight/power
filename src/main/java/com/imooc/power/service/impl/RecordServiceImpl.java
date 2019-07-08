package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.command.RecordCommand;
import com.imooc.power.dao.RecordMapper;
import com.imooc.power.dto.EnergyDTO;
import com.imooc.power.entity.Record;
import com.imooc.power.service.IRecordService;
import com.imooc.power.util.BeanUtil;
import com.imooc.power.util.DateUtils;
import com.imooc.power.vo.EnergyStatisticsVO;
import com.imooc.power.vo.RecordStatisticsVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
     * 分页查询电压电流记录列表
     *
     * @param command 请求参数
     * @return
     */
    @Override
    public Page<Record> getPageList(RecordCommand command) {
        Page<Record> page = new Page<>(command.getCurrent(), command.getSize());
        Record record = BeanUtil.copyProperties(command, Record.class);
        EntityWrapper<Record> eWrapper = new EntityWrapper<>(record);
        if (null != command.getRecordTimeBegin()) {
            eWrapper.ge("record_time", command.getRecordTimeBegin());
        }
        if (null != command.getRecordTimeEnd()) {
            eWrapper.le("record_time", command.getRecordTimeEnd());
        }
        Page<Record> pageList = selectPage(page, eWrapper);
        return pageList;
    }

    @Override
    public EnergyStatisticsVO getTotalEnergyStatistics(EnergyStatisticsCommand command) throws RuntimeException {
        EnergyStatisticsVO vo = new EnergyStatisticsVO();

        // 获得表格标题并统计当月和上月总能耗
        getTableTitleAndCalcTotalEnergy(command, vo);

        //


        return null;
    }

    /**
     * 获得表格标题
     *
     * @param command
     * @param vo
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
        if (null != energyDTOList && energyDTOList.size() > 0) {
            // 将数据转换成Map结构数据，Key = 年月， value为仪表能耗集合
            Map<String, List<EnergyDTO>> energyMap = energyDTOList.stream().collect(Collectors.groupingBy(EnergyDTO::getDate));
            // 当月所有仪表能耗集合
            List<EnergyDTO> currentList = energyMap.get(currentDate);
            // 上月所有仪表能耗集合
            List<EnergyDTO> preList = energyMap.get(currentDate);
            if (null != currentList && currentList.size() > 0) {
                long energy = 0;
                for (int i = 0, len = currentList.size(); i < len; i++) {
                    EnergyDTO dto = currentList.get(i);
                    energy += dto.getMax() - dto.getMin();
                }
                vo.setCurrentIdStr(energy + "kwh");
            }

            if (null != preList && preList.size() > 0) {
                long energy = 0;
                for (int i = 0, len = preList.size(); i < len; i++) {
                    EnergyDTO dto = currentList.get(i);
                    energy += dto.getMax() - dto.getMin();
                }
                vo.setCurrentIdStr(energy + "kwh");
            }
        }


    }

    @Override
    public Page<RecordStatisticsVO> getPageRecordStatistics(EnergyStatisticsCommand command) {
        return null;
    }

}
