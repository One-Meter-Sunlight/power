package com.imooc.power.command;

import com.imooc.power.base.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 报警记录请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/29 14:14
 */
@Data
public class WarningCommand extends PageInfo {

    private String date;
    private String locationFactoryNumb;
    private List<String> meterNumbs;

}
