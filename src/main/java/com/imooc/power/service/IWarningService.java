package com.imooc.power.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.imooc.power.command.WarningCommand;
import com.imooc.power.entity.Warning;

/**
 * 报警记录表 服务类
 *
 * @author kai.chen
 * @since 2019-06-29
 */
public interface IWarningService extends IService<Warning> {

    /**
     * 分页查询报警信息
     *
     * @param command 请求参数
     * @return
     */
    Page<Warning> getPageList(WarningCommand command);
}
