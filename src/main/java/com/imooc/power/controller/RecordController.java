package com.imooc.power.controller;


import com.alibaba.fastjson.JSONObject;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.command.HistoryRecordCommand;
import com.imooc.power.command.HistoryStatisticsCommand;
import com.imooc.power.entity.Record;
import com.imooc.power.service.IRecordService;
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
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/record")
@Api(tags = "电压电流记录", description = "电压电流记录")
public class RecordController {

    @Resource
    private IRecordService recordService;

    @UserLoginToken
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    @ApiOperation(value = "批量新增仪表电压电流温度记录", notes = "批量新增仪表电压电流温度记录")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject batchAdd(@RequestBody List<Record> list) {
        recordService.batchAdd(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        return jsonObject;
    }

    /**
     * 查询主页电表电压电流
     */
    @UserLoginToken
    @RequestMapping(value = "/meterRecord", method = RequestMethod.GET)
    @ApiOperation(value = "查询主页电表电压电流", notes = "查询主页电表电压电流")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject meterRecord() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", recordService.getMeterRecords());
        return jsonObject;
    }

    /**
     * 总能耗统计
     */
    @UserLoginToken
    @RequestMapping(value = "/totalEnergyStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "总能耗统计", notes = "近两个月总能耗统计")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject totalEnergyStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) EnergyStatisticsCommand command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", recordService.getTotalEnergyStatistics(command));
        return jsonObject;
    }

    /**
     * 仪表能耗分页统计
     */
    @UserLoginToken
    @RequestMapping(value = "/pageRecordStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "仪表能耗分页统计", notes = "仪表能耗分页统计")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject pageRecordStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) EnergyStatisticsCommand command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", recordService.getPageRecordStatistics(command));
        return jsonObject;
    }

    /**
     * 分页查询历史数据
     */
    @UserLoginToken
    @RequestMapping(value = "/pageHistoryRecordStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询历史数据", notes = "分页查询历史数据")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject pageHistoryRecordStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) HistoryStatisticsCommand command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", recordService.getPageHistoryRecordStatistics(command));
        return jsonObject;
    }

    /**
     * 查询历史曲线信息
     */
    @UserLoginToken
    @RequestMapping(value = "/historyRecord", method = RequestMethod.POST)
    @ApiOperation(value = "查询历史曲线信息", notes = "查询历史曲线信息")
    @ApiResponse(response = JSONObject.class, code = 200, message = "接口返回对象参数")
    JSONObject historyRecord(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) HistoryRecordCommand command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("status", true);
        jsonObject.put("data", recordService.getHistoryRecord(command));
        return jsonObject;
    }

}
