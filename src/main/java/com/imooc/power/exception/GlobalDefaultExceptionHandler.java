package com.imooc.power.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/23 13:16
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> handleException(RuntimeException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "500");
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "500");
        map.put("message", "系统访问异常：" + ex.getMessage());
        return map;
    }
}
