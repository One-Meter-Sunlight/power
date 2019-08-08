package com.imooc.power.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * 电表电压电流对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/8 20:27
 */
@Data
public class MeterRecordVO {

    private String meterNumb;
    /**
     * A相电压
     */
    @TableField("v_ab")
    private Float vAb;

    /**
     * B相电压
     */
    @TableField("v_bc")
    private Float vBc;

    /**
     * C相电压
     */
    @TableField("v_ca")
    private Float vCa;

    /**
     * A相电流
     */
    @TableField("a_a")
    private Float aA;

    /**
     * B相电流
     */
    @TableField("a_b")
    private Float aB;

    /**
     * C相电流
     */
    @TableField("a_c")
    private Float aC;

}
