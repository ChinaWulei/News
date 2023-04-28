package com.example.service.Impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.Utils.GenericCode6;
import com.example.mapper.PersonMapper;
import com.example.pojo.Person;
import com.example.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SendMsgServiceImpl implements SendMsgService {

    @Autowired
    private PersonMapper personMapper;

    //application.yml文件里配置的sms
    @Value("${sms.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.accessKeySecret}")
    private String accessKeySecret;
    //短信api的请求地址 固定的 dysmsapi.aliyuncs.com
    @Value("${sms.doMain}")
    private String doMain;
    //指定地域名称 短信API固定的 cn-hangzhou
    @Value("${sms.regionId}")
    private String regionId;
    //您的申请签名
    @Value("${sms.signName}")
    private String signName;
    //你的模板
    @Value("${sms.templateCode}")
    private String templateCode;

    @Override
    public String sendSms(String phoneNum) {
        System.out.println("---"+signName+"--" + templateCode);
        // 指定地域名称 短信API的就是 cn-hangzhou 不能改变  后边填写您的  accessKey 和 accessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        // 创建通用的请求对象
        CommonRequest request = new CommonRequest();
        // 指定请求方式
        request.setSysMethod(MethodType.POST);
        // 短信api的请求地址  固定
        request.setSysDomain(doMain);
        //签名算法版本  固定
        request.setSysVersion("2017-05-25");
        //请求 API 的名称
        request.setSysAction("SendSms");
        //指定地域名称
        request.putQueryParameter("RegionId", regionId);
        // 要给哪个手机号发送短信  指定手机号
        request.putQueryParameter("PhoneNumbers", phoneNum);
        // 您的申请签名
        request.putQueryParameter("SignName", signName);
        // 您申请的模板 code
        request.putQueryParameter("TemplateCode", templateCode);

        Map<String, Object> params = new HashMap<>();
        String code6 = GenericCode6.genericCode6();      //生成6位验证码
        //这里的code就是短信模板中的 ${code},可能会有多个参数
        params.put("code", code6);
        // 放入参数  需要把 map转换为json格式  使用fastJson进行转换
        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            return code6;

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code6;

    }

    @Override
    public Person loginByPhone(String phone) {
        return personMapper.loginByPhone(phone);
    }
}
