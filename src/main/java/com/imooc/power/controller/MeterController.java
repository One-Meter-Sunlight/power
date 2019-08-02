package com.imooc.power.controller;


import com.alibaba.fastjson.JSONObject;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.entity.Meter;
import com.imooc.power.service.IMeterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仪表信息表 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/meter")
@Api(tags = "仪表", description = "仪表信息")
public class MeterController {

    @Resource
    private IMeterService meterService;

    @UserLoginToken
    @RequestMapping(value = "/{factoryNum}", method = RequestMethod.GET)
    @ApiOperation(value = "查询仪表信息列表", notes = "通过工厂编号查询仪表信息列表")
    @ApiImplicitParam(name = "factoryNum", value = "工厂编号", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject getListByFactoryNum(@PathVariable(value = "factoryNum") String factoryNum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", meterService.getListByFactoryNum(factoryNum));
        return jsonObject;
    }
}
