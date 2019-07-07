package com.imooc.power.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.imooc.power.command.UserCommand;
import com.imooc.power.entity.User;

import java.util.List;

/**
 * 用户管理服务类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
public interface IUserService extends IService<User> {

    /**
     * 通过accountId查询用户信息
     *
     * @param accountId 用户账号ID
     * @return
     */
    User getByAccountId(String accountId);

    /**
     * 查询账户信息列表
     *
     * @return
     */
    List<User> getUserList();

    /**
     * 分页查询用户信息列表
     *
     * @param command 请求参数
     * @return
     */
    Page<User> getPageList(UserCommand command);

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    Boolean addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Boolean updateUser(User user);

}
