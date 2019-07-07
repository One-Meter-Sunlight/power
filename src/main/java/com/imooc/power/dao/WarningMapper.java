package com.imooc.power.dao;

import com.imooc.power.entity.Warning;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报警记录表 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@Mapper
public interface WarningMapper extends BaseMapper<Warning> {

}
