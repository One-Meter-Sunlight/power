package com.imooc.power.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 能耗详情列表统计
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class RecordStatisticsVO implements Serializable {

    private String meterNumb;
    private String meterDesc;

    /**
     * 当月电表总能耗
     */
    private Long currentKwh;

    /**
     * 上月电表总能耗
     */
    private Long preMonthKwh;

    /**
     * 上上月电表总能耗
     */
    private Long pre2MonthKwh;
}
