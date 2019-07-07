package com.imooc.power.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.power.entity.Factory;
import com.imooc.power.vo.FactoryMetersVO;

import java.util.List;

/**
 * 工厂信息表 服务类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
public interface IFactoryService extends IService<Factory> {

    /**
     * 查询工厂信息列表
     *
     * @return
     */
    List<Factory> getList();

    /**
     * 查询所有工厂以及工厂下的电表
     *
     * @return
     */
    List<FactoryMetersVO> getFactoryMetersVOList();
}
