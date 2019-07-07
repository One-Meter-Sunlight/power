package com.imooc.power.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 工厂信息表
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Data
public class Factory implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 工厂编号
     */
    @TableId(value = "factory_numb")
    private String factoryNumb;

    /**
     * 工厂名称描述
     */
    @TableField("factory_desc")
    private String factoryDesc;
}
