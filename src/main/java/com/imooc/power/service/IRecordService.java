package com.imooc.power.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.command.RecordCommand;
import com.imooc.power.entity.Record;
import com.imooc.power.vo.EnergyStatisticsVO;
import com.imooc.power.vo.RecordStatisticsVO;

import java.text.ParseException;

/**
 * 服务类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
public interface IRecordService extends IService<Record> {

    /**
     * 分页查询电压电流记录列表
     *
     * @param command
     * @return
     */
    Page<Record> getPageList(RecordCommand command);

    /**
     * 能耗统计
     *
     * @param command 能耗统计请求参数
     * @return
     */
    Object getEnergyStatisticsPageList(EnergyStatisticsCommand command);

    EnergyStatisticsVO getTotalEnergyStatistics(EnergyStatisticsCommand command) throws ParseException;

    Page<RecordStatisticsVO> getPageRecordStatistics(EnergyStatisticsCommand command);
}
