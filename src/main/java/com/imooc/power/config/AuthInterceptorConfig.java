package com.imooc.power.config;

import com.imooc.power.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/28 23:05
 */
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        // 其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // swagger增加url映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(86400)
                .allowCredentials(true);
    }
}
