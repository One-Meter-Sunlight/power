package com.imooc.power.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 仪表信息表
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Data
public class Meter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仪表编号
     */
    @TableId(value = "meter_numb")
    private String meterNumb;

    /**
     * 仪表型号
     */
    @TableField("meter_model")
    private String meterModel;

    /**
     * 仪表地址
     */
    @TableField("meter_add")
    private String meterAdd;

    /**
     * 所在串口编号
     */
    @TableField("serial_numb")
    private String serialNumb;

    /**
     * 所在工厂编号
     */
    @TableField("location_factory_numb")
    private String locationFactoryNumb;

    /**
     * 仪表描述
     */
    @TableField("meter_desc")
    private String meterDesc;

    /**
     * 线路额定电压
     */
    @TableField("be_test_vol")
    private String beTestVol;

    /**
     * 线路额定电流
     */
    @TableField("be_test_cur")
    private String beTestCur;

    @TableField("reset_kw_date")
    private String resetKwDate;

    /**
     * 报警电流
     */
    @TableField("switch_cur")
    private String switchCur;

    /**
     * CT变比
     */
    @TableField("instru_trans_ct")
    private String instruTransCt;

    /**
     * 所在抽屉编号
     */
    @TableField("location_carbinet_numb")
    private String locationCarbinetNumb;

    /**
     * 所在抽屉柜列
     */
    @TableField("location_carbinet_column")
    private String locationCarbinetColumn;

    /**
     * 层数
     */
    private String floor;

    /**
     * 报警电压
     */
    @TableField("vol_warn_value")
    private String volWarnValue;

    /**
     * 报警电流
     */
    @TableField("cur_warn_value")
    private String curWarnValue;

    /**
     * 温度报警值
     */
    @TableField("tmp_warn_value")
    private String tmpWarnValue;

    /**
     * 其他参数
     */
    @TableField("meter_other_param")
    private String meterOtherParam;

}
