package com.example.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor(redisTemplate));
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/person/login/**").excludePathPatterns("/person/logout").excludePathPatterns("/person/allNews").excludePathPatterns("/person/searchpnameBynid").excludePathPatterns("/person/searchNewsBynid/**").excludePathPatterns("/person/testCode/**").excludePathPatterns("/person/sendMsg/**");
    }
}
