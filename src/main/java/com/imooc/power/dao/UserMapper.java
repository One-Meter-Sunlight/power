package com.imooc.power.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.power.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户管理 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过accountId查询用户信息
     *
     * @param accountId 用户账号ID
     * @return
     */
    User selectOneByAccountId(String accountId);

    /**
     * 查询账户信息列表
     *
     * @return
     */
    List<User> selectUserList();

}
