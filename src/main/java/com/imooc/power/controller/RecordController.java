package com.imooc.power.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.imooc.power.annotation.UserLoginToken;
import com.imooc.power.command.EnergyStatisticsCommand;
import com.imooc.power.command.HistoryRecordCommand;
import com.imooc.power.command.HistoryStatisticsCommand;
import com.imooc.power.service.IRecordService;
import com.imooc.power.vo.EnergyStatisticsVO;
import com.imooc.power.vo.HistoryRecordStatisticsVO;
import com.imooc.power.vo.HistoryRecordVO;
import com.imooc.power.vo.RecordStatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

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

    /**
     * 总能耗统计
     */
    @UserLoginToken
    @RequestMapping(value = "/totalEnergyStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "总能耗统计", notes = "近两个月总能耗统计")
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    EnergyStatisticsVO totalEnergyStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) EnergyStatisticsCommand command) throws ParseException {
        return recordService.getTotalEnergyStatistics(command);
    }

    /**
     * 仪表能耗分页统计
     */
    @UserLoginToken
    @RequestMapping(value = "/pageRecordStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "仪表能耗分页统计", notes = "仪表能耗分页统计")
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    Page<RecordStatisticsVO> pageRecordStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) EnergyStatisticsCommand command) {
        return recordService.getPageRecordStatistics(command);
    }

    /**
     * 分页查询历史数据
     */
    @UserLoginToken
    @RequestMapping(value = "/pageHistoryRecordStatistics", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询历史数据", notes = "分页查询历史数据")
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    Page<HistoryRecordStatisticsVO> pageHistoryRecordStatistics(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) HistoryStatisticsCommand command) {
        return recordService.getPageHistoryRecordStatistics(command);
    }

    /**
     * 查询历史曲线信息
     */
    @UserLoginToken
    @RequestMapping(value = "/historyRecord", method = RequestMethod.POST)
    @ApiOperation(value = "查询历史曲线信息", notes = "查询历史曲线信息")
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    HistoryRecordVO historyRecord(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式", required = true) HistoryRecordCommand command) {
        return recordService.getHistoryRecord(command);
    }

}
