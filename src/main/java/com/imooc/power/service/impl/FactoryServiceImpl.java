package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.power.dao.FactoryMapper;
import com.imooc.power.entity.Factory;
import com.imooc.power.service.IFactoryService;
import com.imooc.power.vo.FactoryMetersVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工厂信息表 服务实现类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements IFactoryService {

    private static final Logger log = LoggerFactory.getLogger(FactoryServiceImpl.class);

    @Autowired
    private FactoryMapper factoryMapper;

    /**
     * 查询工厂信息列表
     *
     * @return
     */
    @Override
    public List<Factory> getList() {
        log.info("查询工厂列表");
        return factoryMapper.selectList();
    }

    /**
     * 查询所有工厂以及工厂下的电表
     *
     * @return
     */
    @Override
    public List<FactoryMetersVO> getFactoryMetersVOList() {
        log.info("查询工厂列表");
        return factoryMapper.selectFactoryMetersVOList();
    }
}
