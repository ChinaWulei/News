package com.example.controller;

import com.example.pojo.Person;
import com.example.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class SendMsgController {

    @Autowired
    SendMsgService sendMsgService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/person/sendMsg/{phone}")
    public Map sendMsg(@PathVariable("phone")String phone) {
        Object o = redisTemplate.opsForValue().get(phone);
        Map map = new HashMap();
        map.put("phone" , phone);
        if(o != null) {
            map.put("message" , "验证码未过期");
            return map;
        }
 /*       String code6 = sendMsgService.sendSms(phone);    //调用阿里云短信API , 发送短信*/
        String code6 = "159753";
        redisTemplate.opsForValue().set(phone , code6 , 2 , TimeUnit.MINUTES);
        map.put("code" , code6);
        map.put("message" , "该验证码为"+code6);
        System.out.println(map);
        return map;



    }
    @RequestMapping("/person/testCode")
    public Map testCode(String phone , String code) {
        Map map = new HashMap();
        Object o = redisTemplate.opsForValue().get(phone);
        System.out.println(o+"---" +code);
        if(code.equals(String.valueOf(o))) {
            System.out.println("验证码正确");
            int pid = sendMsgService.loginByPhone(phone).getPid();
            String token = UUID.randomUUID().toString().replace("-" , "");
            String value = String.valueOf(pid);
            redisTemplate.opsForValue().set("person:token:"+token , value , 30 , TimeUnit.MINUTES);  //在redis存储token值,有效期30分钟
            map.put("pid" , pid);
            map.put("token" , token);
            map.put("result" , "true");
            return map;
        }
        map.put("result" , "false");
        return map;

        
    }
}
