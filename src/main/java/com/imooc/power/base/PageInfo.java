package com.imooc.power.base;

import lombok.Data;

import java.util.List;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/23 14:35
 */
@Data
public class PageInfo {

    /**
     * 当前第N页
     */
    private int current;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 总条数（总记录数）
     */
    private int total;

    /**
     * 每页条数
     */
    private int size = 20;

    /**
     * 数据
     */
    private List<Object> records;
}
