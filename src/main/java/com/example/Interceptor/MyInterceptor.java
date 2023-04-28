package com.example.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


public class MyInterceptor extends HandlerInterceptorAdapter {

    private RedisTemplate redisTemplate;

    public MyInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在请求处理之前进行拦截处理

        System.out.println("测试拦截器------preHandle");
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String auth = request.getHeader("Auth");
        System.out.println(auth);
        if(auth == null || auth.equals("") || redisTemplate.opsForValue().get("person:token:"+auth) == null ) {  //用户没有登录  ，进行拦截
            response.setStatus(401);
            return false;
        }
        Object id = redisTemplate.opsForValue().get("person:token:"+auth);
        redisTemplate.opsForValue().set("person:token:"+auth , id , 30 , TimeUnit.MINUTES);        //对该用户进行续命


        //返回true表示继续执行请求，返回false表示停止执行请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //在请求处理之后进行拦截处理
        System.out.println("测试拦截器------postHandle");
    }
}

