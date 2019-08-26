package com.imooc.power.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.power.command.UserCommand;
import com.imooc.power.dao.UserMapper;
import com.imooc.power.entity.User;
import com.imooc.power.service.IUserService;
import com.imooc.power.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理服务实现类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询用户信息列表
     *
     * @param command 请求参数
     * @return
     */
    @Override
    public Page<User> getPageList(UserCommand command) {
        Page<User> page = new Page<>(command.getCurrent(), command.getSize());
        User user = BeanUtil.copyProperties(command, User.class);
        EntityWrapper<User> eWrapper = new EntityWrapper<>(user);
        return selectPage(page, eWrapper);
    }

    /**
     * 通过accountId查询用户信息
     *
     * @param accountId 用户账号ID
     * @return
     */
    @Override
    public User getByAccountId(String accountId) {
        return userMapper.selectOneByAccountId(accountId);
    }

    /**
     * 查询账户信息列表
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        log.info("查询账户信息列表");
        return userMapper.selectUserList();
    }

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Boolean addUser(User user) {
        return userMapper.insert(user) > 0 ? true : false;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateById(user) > 0 ? true : false;
    }

}
