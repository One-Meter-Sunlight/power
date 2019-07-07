package com.imooc.power.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 报警记录表
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Data
public class Warning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报警时间
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
     * 报警内容
     */
    private String content;
}
