package com.imooc.power.dto;

import lombok.Data;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/7 17:25
 */
@Data
public class EnergyDTO {

    private String date;
    private String meterNumb;
    private Long max;
    private Long min;
}
