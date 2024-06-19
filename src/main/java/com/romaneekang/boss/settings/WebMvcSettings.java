package com.romaneekang.boss.settings;

import com.romaneekang.boss.interceptor.MenuInterceptor;
import com.romaneekang.boss.interceptor.TokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcSettings implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;
    @Resource
    private MenuInterceptor menuInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //拦截地址,所有请求都拦截
                .excludePathPatterns("/operator/login");    //登录请求不拦截
        registry.addInterceptor(menuInterceptor)
                .addPathPatterns("/operator/menus");
    }

    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截的请求地址，哪些地址需要进行跨域拦截
                .allowedOriginPatterns("*") // 请求的来源域名
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")  // 允许使用的请求方式
                .allowCredentials(true) // 支持cookie
                .maxAge(3600)   // 域检有效期
                .allowedHeaders("*");   // 允许出现的请求header的名称

    }
}
