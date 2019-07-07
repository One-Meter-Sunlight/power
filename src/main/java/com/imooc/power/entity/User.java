package com.imooc.power.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户名
     */
    @TableField("account_id")
    private String accountId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 服务器地址
     */
    private String server;

    /**
     * 创建时间
     */
    private Date created;
}
