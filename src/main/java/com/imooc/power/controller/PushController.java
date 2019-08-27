package com.imooc.power.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.imooc.power.annotation.PassToken;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.command.UserCommand;
import com.imooc.power.entity.PushBean;
import com.imooc.power.entity.User;
import com.imooc.power.service.IUserService;
import com.imooc.power.service.JiGuangPushService;
import com.imooc.power.util.TokenUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/push")
@Api(tags = "推送", description = "消息推送")
public class PushController {

    @Autowired
    private JiGuangPushService jiGuangPushService;

    /**
     * 群推，广播
     *
     * @param title   推送标题
     * @param content 推送内容
     * @return
     */
    @UserLoginToken
    @PostMapping("/pushAll")
    @ApiOperation(value = "推送", notes = "消息推送")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    public JSONObject pushAll(@RequestParam String title, @RequestParam String content) {
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        jiGuangPushService.pushAll(pushBean);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        return jsonObject;
    }

}
