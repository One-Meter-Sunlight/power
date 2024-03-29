package com.imooc.power.controller;


import com.alibaba.fastjson.JSONObject;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.command.WarningCommand;
import com.imooc.power.entity.Warning;
import com.imooc.power.service.IWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报警记录表 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/warning")
@Api(tags = "报警", description = "报警记录")
public class WarningController {

    @Resource
    private IWarningService warningService;

    /**
     * 分页查询报警信息
     *
     * @param command 请求参数
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询报警信息", notes = "分页查询报警信息")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject page(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) WarningCommand command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", warningService.getPageList(command));
        return jsonObject;
    }

    @UserLoginToken
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    @ApiOperation(value = "批量新增报警记录", notes = "批量新增报警记录")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject batchAdd(@RequestBody List<Warning> list) {
        warningService.batchAdd(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        return jsonObject;
    }

}
