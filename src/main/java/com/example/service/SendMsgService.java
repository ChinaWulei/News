package com.example.service;


import com.example.pojo.Person;

public interface SendMsgService {

    /**
     * 发送短信的接口
     *
     * @param phoneNum 手机号
     * @param message 消息
     * @return
     */
    public String sendSms(String phoneNum);

    public Person loginByPhone(String phone);


}
