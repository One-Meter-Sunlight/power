package com.imooc.power.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 能耗历史曲线
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class HistoryRecordVO implements Serializable {

    /**
     * X轴数据
     */
    private List<String> xTitles;

    /**
     * 图表数据
     */
    private Map<String, Map<String, List<Float>>> map;

}
