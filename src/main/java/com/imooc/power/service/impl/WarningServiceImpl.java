package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.power.command.WarningCommand;
import com.imooc.power.dao.WarningMapper;
import com.imooc.power.entity.Warning;
import com.imooc.power.service.IWarningService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 报警记录表 服务实现类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class WarningServiceImpl extends ServiceImpl<WarningMapper, Warning> implements IWarningService {

    private static final Logger log = LoggerFactory.getLogger(WarningServiceImpl.class);

    /**
     * 分页查询报警信息
     *
     * @param command 请求参数
     * @return
     */
    @Override
    public Page<Warning> getPageList(WarningCommand command) {
        long startTime = System.currentTimeMillis();

        Page<Warning> page = new Page<>(command.getCurrent(), command.getSize());
        EntityWrapper<Warning> eWrapper = new EntityWrapper<>();
        if (null != command.getMeterNumbs() && command.getMeterNumbs().size() > 0) {
            eWrapper.in("meter_numb", command.getMeterNumbs());
        }
        if (StringUtils.isNoneBlank(command.getDate())) {
            eWrapper.eq("DATE_FORMAT(record_time, '%Y-%m-%d')", command.getDate());
        }
        eWrapper.orderBy("record_time", false);

        log.info(">>>>>> 分页查询报警信息总耗时：[{}]ms", System.currentTimeMillis() - startTime);

        return selectPage(page, eWrapper);
    }
}
