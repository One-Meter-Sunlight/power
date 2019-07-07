package com.imooc.power.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.imooc.power.annotation.PassToken;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.command.UserCommand;
import com.imooc.power.entity.User;
import com.imooc.power.service.IUserService;
import com.imooc.power.util.TokenUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理 Controller
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/19 23:29
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户", description = "用户信息")
public class UserController {

    @Resource
    private IUserService userService;

    @PassToken
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "用户登录")
    @ApiResponse(response = Boolean.class, code = 200, message = "接口返回对象参数")
    public Object login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (null == user || StringUtils.isBlank(user.getAccountId())) {
            jsonObject.put("status", false);
            jsonObject.put("message", "请输入登录账号");
            return jsonObject;
        }
        User userForBase = userService.getByAccountId(user.getAccountId());
        if (userForBase == null) {
            jsonObject.put("status", false);
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("status", false);
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = TokenUtil.getToken(userForBase);
                jsonObject.put("status", true);
                jsonObject.put("token", token);
                userForBase.setPassword(null);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询账户信息", notes = "通过AccountId查询账户信息")
    @ApiImplicitParam(name = "accountId", value = "账户ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = User.class, code = 200, message = "接口返回对象参数")
    User getUserByAccountId(@PathVariable(value = "accountId") String accountId) {
        return userService.getByAccountId(accountId);
    }

    @UserLoginToken
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @ApiResponse(response = Boolean.class, code = 200, message = "接口返回对象参数")
    Boolean add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @UserLoginToken
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiResponse(response = Boolean.class, code = 200, message = "接口返回对象参数")
    Boolean update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @UserLoginToken
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询用户信息列表", notes = "分页查询用户信息列表")
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    Page<User> page(@RequestBody @ApiParam(name = "用户对象", value = "传入JSON格式", required = true) UserCommand command) {
        return userService.getPageList(command);
    }

    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询账户信息列表", notes = "查询账户信息列表")
    @ApiResponse(response = List.class, code = 200, message = "接口返回对象参数")
    List<User> list() {
        return userService.getUserList();
    }


}
