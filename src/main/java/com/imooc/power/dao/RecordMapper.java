package com.imooc.power.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.imooc.power.dto.EnergyDTO;
import com.imooc.power.entity.Record;
import com.imooc.power.vo.HistoryRecordStatisticsVO;
import com.imooc.power.vo.RecordStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    /**
     * 分组查询所有仪表能耗记录
     *
     * @param dateList            时间字符串集合
     * @param locationFactoryNumb 工厂编号
     * @param meterNumbs          仪表编号集合
     * @return
     */
    List<EnergyDTO> selectEnergyDTOList(@Param("dateList") List<String> dateList,
                                        @Param("locationFactoryNumb") String locationFactoryNumb,
                                        @Param("meterNumbs") List<String> meterNumbs);

    /**
     * 仪表能耗分页统计
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return
     */
    List<RecordStatisticsVO> selectRecordStatisticsList(Page<RecordStatisticsVO> page, Map<String, Object> condition);

    /**
     * 分页查询历史数据
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return
     */
    List<HistoryRecordStatisticsVO> selectHistoryRecordStatistics(Page<HistoryRecordStatisticsVO> page, Map<String, Object> condition);
}
