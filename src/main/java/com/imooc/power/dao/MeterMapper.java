package com.imooc.power.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.power.entity.Meter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 仪表信息表 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Mapper
public interface MeterMapper extends BaseMapper<Meter> {

    /**
     * 通过工厂编号查询仪表信息列表
     *
     * @param factoryNum 工厂编号
     * @return
     */
    List<Meter> selectListByFactoryNum(String factoryNum);
}
