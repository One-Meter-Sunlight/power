package com.imooc.power.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.command.HistoryRecordCommand;
import com.imooc.power.command.HistoryStatisticsCommand;
import com.imooc.power.entity.Record;
import com.imooc.power.vo.EnergyStatisticsVO;
import com.imooc.power.vo.HistoryRecordStatisticsVO;
import com.imooc.power.vo.HistoryRecordVO;
import com.imooc.power.vo.RecordStatisticsVO;

import java.text.ParseException;
import java.util.List;

/**
 * 服务类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
public interface IRecordService extends IService<Record> {

    /**
     * 近两个月总能耗统计
     *
     * @param command 请求参数
     * @return
     * @throws ParseException
     */
    EnergyStatisticsVO getTotalEnergyStatistics(EnergyStatisticsCommand command) throws ParseException;

    /**
     * 仪表能耗分页统计
     *
     * @param command 请求参数
     * @return
     */
    Page<RecordStatisticsVO> getPageRecordStatistics(EnergyStatisticsCommand command);

    /**
     * 分页查询历史数据
     *
     * @param command 请求参数
     * @return
     */
    Page<HistoryRecordStatisticsVO> getPageHistoryRecordStatistics(HistoryStatisticsCommand command);

    /**
     * 查询历史曲线信息
     *
     * @param command
     * @return
     */
    HistoryRecordVO getHistoryRecord(HistoryRecordCommand command);

    /**
     * 批量新增仪表电压电流温度记录
     *
     * @param list
     */
    void batchAdd(List<Record> list);
}
