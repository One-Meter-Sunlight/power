package com.imooc.power.service;

import com.imooc.power.entity.Meter;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 仪表信息表 服务类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
public interface IMeterService extends IService<Meter> {

    /**
     * 通过工厂编号查询仪表信息列表
     * @param factoryNum 工厂编号
     * @return
     */
    List<Meter> getListByFactoryNum(String factoryNum);
}
