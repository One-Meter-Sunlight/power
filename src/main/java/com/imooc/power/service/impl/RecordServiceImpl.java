package com.imooc.power.service.impl;

import com.alibaba.fastjson.JSONObject;
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

        // 获得表格标题
        getTableTitle(command, vo);

        //


        return null;
    }

    /**
     * 获得表格标题
     *
     * @param command
     * @param vo
     */
    private void getTableTitle(EnergyStatisticsCommand command, EnergyStatisticsVO vo) {
        if (StringUtils.isNoneBlank(command.getDate())) {
            try {
                Date selectDate = DateUtils.passDate(command.getDate(), DateUtils.DATE_PATTERN_YYYY_MM);

                // 上月时间和上上月时间
                Date preDate = DateUtils.addMonth(selectDate, -1);
                String preDateStr = DateUtils.format(preDate, DateUtils.DATE_PATTERN_MM);

                Date pre2Date = DateUtils.addMonth(selectDate, -2);
                String pre2DateStr = DateUtils.format(pre2Date, DateUtils.DATE_PATTERN_MM);

                vo.setIdStr("编号");
                vo.setCurrentIdStr("本月能耗");
                vo.setPreIdStr(preDateStr + "月用电");
                vo.setPre2IdStr(pre2DateStr + "月用电");

                // 统计总能耗
                calcCurrAndPreEnergy(command.getDate(), DateUtils.format(preDate, DateUtils.DATE_PATTERN_YYYY_MM),
                        command, vo);
            } catch (Exception e) {
                log.error("时间转换异常：" + e.getMessage());
                throw new RuntimeException("系统异常");
            }
        } else {
            try {
                // 上月时间和上上月时间
                Date preDate = DateUtils.addMonth(-1);
                String preDateStr = DateUtils.format(preDate, DateUtils.DATE_PATTERN_MM);

                Date pre2Date = DateUtils.addMonth(-2);
                String pre2DateStr = DateUtils.format(pre2Date, DateUtils.DATE_PATTERN_MM);

                vo.setIdStr("编号");
                vo.setCurrentIdStr("本月能耗");
                vo.setPreIdStr(preDateStr + "月用电");
                vo.setPre2IdStr(pre2DateStr + "月用电");

                // 统计总能耗
                calcCurrAndPreEnergy(DateUtils.format(new Date(), DateUtils.DATE_PATTERN_YYYY_MM), DateUtils.format(preDate, DateUtils.DATE_PATTERN_YYYY_MM),
                        command, vo);
            } catch (Exception e) {
                log.error("时间转换异常：" + e.getMessage());
                throw new RuntimeException("系统异常");
            }
        }
    }

    /**
     * 统计本月和上月总能耗
     *
     * @param currentDate
     * @param preDate
     * @param vo
     */
    private void calcCurrAndPreEnergy(String currentDate, String preDate, EnergyStatisticsCommand command, EnergyStatisticsVO vo) {

        List<EnergyDTO> energyDTOList = recordMapper.selectEnergyDTOList(Lists.newArrayList(currentDate, preDate),
                command.getLocationFactoryNumb(), command.getMeterNumbs());


    }

    @Override
    public Page<RecordStatisticsVO> getPageRecordStatistics(EnergyStatisticsCommand command) {
        return null;
    }

    /**
     * 能耗统计
     *
     * @param command 能耗统计请求参数
     * @return
     */
    @Override
    public Object getEnergyStatisticsPageList(EnergyStatisticsCommand command) {
        // 返回值
        JSONObject jsonObject = new JSONObject();

        // 分页对象
        Page<RecordStatisticsVO> page = new Page<>();
        page.setCurrent(command.getCurrent());
        page.setSize(command.getSize());

        /*// 循环查询
        if (null != meterPage && null != meterPage.getRecords() && meterPage.getRecords().size() > 0) {
            List<Meter> meterList = meterPage.getRecords();
            // 根据条件统计当月、上月、上上月的能耗数据
            List<RecordStatisticsVO> recordStatisticsVOList = selectEnergyStatisticsList(meterList, command);
            if (null != recordStatisticsVOList && recordStatisticsVOList.size() > 0) {
                // 总计
                calcTotal(recordStatisticsVOList, jsonObject);

                // 设置分页列表
                page.setRecords(recordStatisticsVOList);
            }
        }*/

        return jsonObject;
    }

}
