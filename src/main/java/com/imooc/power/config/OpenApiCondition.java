package com.imooc.power.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 是否开启Swagger控制类
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/3 11:31
 */
public class OpenApiCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext,
                           AnnotatedTypeMetadata annotatedTypeMetadata) {
        String param = conditionContext.getEnvironment().getProperty("profiles.swagger");
        return param.equals("true");
    }
}
