package com.imooc.power.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 仪表信息表VO
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Data
public class MeterVO implements Serializable {

    private String meterNumb;
    private String meterDesc;
}
