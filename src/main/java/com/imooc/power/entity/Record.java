package com.imooc.power.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 电压电流温度记录信息
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Data
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 记录时间
     */
    @TableField("record_time")
    private Date recordTime;

    /**
     * 所在工厂编号
     */
    @TableField("location_factory_numb")
    private String locationFactoryNumb;

    /**
     * 仪表编号
     */
    @TableField("meter_numb")
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
     * 平均电压
     */
    @TableField("v_ave")
    private Float vAve;

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

    /**
     * 平均电流
     */
    @TableField("a_ave")
    private Float aAve;

    /**
     * 当前电表能耗
     */
    private Long kwh;

    /**
     * 正向视在功率
     */
    @TableField("app_kwh")
    private Long appKwh;

    /**
     * 正向有功功率
     */
    private Long kw;

    /**
     * A相温度
     */
    @TableField("temp_a")
    private Float tempA;

    /**
     * B相温度
     */
    @TableField("temp_b")
    private Float tempB;

    /**
     * C相温度
     */
    @TableField("temp_c")
    private Float tempC;

}
