package com.imooc.power.command;

import com.imooc.power.base.PageInfo;
import lombok.Data;

import java.util.Date;

/**
 * 电压电流记录请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/29 14:14
 */
@Data
public class RecordCommand extends PageInfo {

    private Date recordTimeBegin;
    private Date recordTimeEnd;
    private String locationFactoryNumb;
    private String meterNumb;
    /**
     * 电压电流类型
     */
    private String type;

}
