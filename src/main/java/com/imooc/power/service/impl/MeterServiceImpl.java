package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.power.dao.MeterMapper;
import com.imooc.power.entity.Meter;
import com.imooc.power.service.IMeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 仪表信息表 服务实现类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MeterServiceImpl extends ServiceImpl<MeterMapper, Meter> implements IMeterService {

    @Autowired
    private MeterMapper meterMapper;

    /**
     * 通过工厂编号查询仪表信息列表
     *
     * @param factoryNum 工厂编号
     * @return
     */
    @Override
    public List<Meter> getListByFactoryNum(String factoryNum) {
        return meterMapper.selectListByFactoryNum(factoryNum);
    }
}
