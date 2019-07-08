package com.imooc.power.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.power.dto.EnergyDTO;
import com.imooc.power.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
