package com.imooc.power.dao;

import com.imooc.power.dto.EnergyDTO;
import com.imooc.power.entity.Record;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    List<EnergyDTO> selectEnergyDTOList(@Param("dateList") List<String> dateList,
                                        @Param("locationFactoryNumb") String locationFactoryNumb,
                                        @Param("meterNumbs") List<String> meterNumbs);
}
