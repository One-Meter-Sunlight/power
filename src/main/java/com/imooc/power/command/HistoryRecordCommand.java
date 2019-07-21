package com.imooc.power.command;

import lombok.Data;

/**
 * 历史数据统计请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 21:01
 */
@Data
public class HistoryRecordCommand {

    /**
     * 工厂编号
     */
    private String locationFactoryNumb;
    /**
     * 仪表
     */
    private String meterNumb;
    /**
     * 年-月-日 时:分
     */
    private String beginDate;
    /**
     * 年-月-日 时:分
     */
    private String endDate;
}
