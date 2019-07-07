package com.imooc.power.vo;

import lombok.Data;

import java.util.List;

/**
 * 工厂电表信息 VO
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/6 13:58
 */
@Data
public class FactoryMetersVO {

    private String factoryNumb;
    private String factoryDesc;

    List<MeterVO> meterVOList;
}
