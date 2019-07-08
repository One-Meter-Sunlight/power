package com.imooc.power.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 总能耗
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class EnergyStatisticsVO implements Serializable {
    /**
     * 表头字段
     */
    private String idStr;
    private String currentIdStr;
    private String preIdStr;
    private String pre2IdStr;

    /**
     * 当月电表总能耗
     */
    private String currentMonthTotalKwhStr;

    /**
     * 上月电表总能耗
     */
    private String preMonthTotalKwhStr;
}
