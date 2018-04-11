package com.lwt.news.config;


import com.lwt.news.interceptor.AdminInterCeptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class NewsWebMvcConfigure implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterCeptor()).
                addPathPatterns("/users/**");
    }
}
