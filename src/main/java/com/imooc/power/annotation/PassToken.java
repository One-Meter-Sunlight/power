package com.imooc.power.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：跳过验证
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/28 22:47
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {

    boolean required() default true;

}
