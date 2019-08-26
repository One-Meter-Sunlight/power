package com.imooc.power.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.power.entity.Factory;
import com.imooc.power.vo.FactoryMetersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工厂信息表 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Mapper
public interface FactoryMapper extends BaseMapper<Factory> {

    /**
     * 查询工厂列表
     *
     * @return
     */
    List<Factory> selectFactoryList();

    /**
     * 查询所有工厂以及工厂下的电表
     *
     * @return
     */
    List<FactoryMetersVO> selectFactoryMetersVOList();
}
