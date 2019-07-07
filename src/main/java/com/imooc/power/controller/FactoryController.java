package com.imooc.power.controller;


import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.entity.Factory;
import com.imooc.power.service.IFactoryService;
import com.imooc.power.vo.FactoryMetersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工厂信息表 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/factory")
@Api(tags = "工厂", description = "工厂信息")
public class FactoryController {

    @Resource
    private IFactoryService factoryService;

    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询工厂信息列表", notes = "查询工厂信息列表")
    @ApiResponse(response = List.class, code = 200, message = "接口返回对象参数")
    List<Factory> list() {
        return factoryService.getList();
    }

    /**
     * 查询所有工厂以及工厂下的电表
     */
    @UserLoginToken
    @RequestMapping(value = "/listFactoryMeters", method = RequestMethod.GET)
    @ApiOperation(value = "查询工厂仪器信息列表", notes = "查询工厂仪器信息列表")
    @ApiResponse(response = List.class, code = 200, message = "接口返回对象参数")
    List<FactoryMetersVO> listFactoryMeters() {
        return factoryService.getFactoryMetersVOList();
    }
}
