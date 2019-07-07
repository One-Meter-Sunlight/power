package com.imooc.power.command;

import com.imooc.power.base.PageInfo;
import lombok.Data;

import java.util.Date;

/**
 * 用户管理 请求参数对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/23 14:24
 */
@Data
public class UserCommand extends PageInfo {

    private Integer id;
    private String accountId;
    private String name;
    private String password;
    private String server;
    private Date created;

}
