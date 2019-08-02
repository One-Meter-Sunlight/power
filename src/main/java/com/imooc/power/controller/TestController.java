package com.imooc.power.controller;


import com.alibaba.fastjson.JSONObject;
import com.imooc.power.annotation.PassToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/test")
@Api(tags = "服务器地址测试", description = "服务器地址测试", hidden = true)
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PassToken
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "服务器地址测试", notes = "服务器地址测试")
    JSONObject test() {
        log.info(">>>>>>>>>> 服务器地址测试 <<<<<<<<<<");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        return jsonObject;

    }

}
