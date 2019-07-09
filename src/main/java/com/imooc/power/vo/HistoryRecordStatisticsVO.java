package com.imooc.power.vo;

import com.imooc.power.entity.Record;
import lombok.Data;

import java.io.Serializable;

/**
 * 能耗详情列表统计
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class HistoryRecordStatisticsVO extends Record implements Serializable {

    private String meterDesc;

}
