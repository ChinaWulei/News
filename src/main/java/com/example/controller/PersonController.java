package com.example.controller;

import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;
import com.example.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PersonController {

    @Autowired
    PersonService personService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("person/login")
    public Map login(Person person){               //登录
        Map login = personService.login(person);
        System.out.println(login);
        return login;
    }

    @RequestMapping("person/logout")        //退出登录
    public void logout(String token) {
        personService.logout(token);
    }

    @RequestMapping("person/register")
    public Person register(Person person){         //注册
        System.out.println(person);
        return personService.register(person);
    }

    @RequestMapping("person/allNews")              //查看所有新闻列表
    public List<news> allNews() {                     //查看所有新闻
        List<news> news = personService.allNews();
        System.out.println(news);
        return news;
    };

    @PostMapping("person/release")                     //发布新闻
    public int release(@RequestBody Map map , @RequestHeader String Auth) {
        map.put("token" , Auth);
        return personService.release(map);
    }

    @RequestMapping("/person/myRelease")                  //查看我发布的全部新闻 ，按时间最新排序
    public Person myRelease(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myRelease(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseAllClickMany")                  //查看我发布的全部新闻 ，按点击量最多排序
    public Person myReleaseAllClickMany(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseAllClickMany(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseAllClickLow")                  //查看我发布的全部新闻 ，按点击量最少排序
    public Person myReleaseAllClickLow(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseAllClickLow(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseCheckedClickMany")                  //查看我的被审核的新闻 ，按点击量最多排序
    public Person myReleaseCheckedClickMany(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseCheckedClickMany(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseCheckedClickLow")                  //查看我的被审核的新闻 ，按点击量最少排序
    public Person myReleaseCheckedClickLow(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseCheckedClickLow(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseNotCheckedClickMany")                  //查看我的未被审核的新闻 ，按点击量最多排序
    public Person myReleaseNotCheckedClickMany(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseNotCheckedClickMany(person);
        return personRes;
    }

    @RequestMapping("/person/myReleaseNotCheckedClickLow")                  //查看我的未被审核的新闻 ，按点击量最少排序
    public Person myReleaseNotCheckedClickLow(Person person , @RequestHeader String Auth) {
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(pid));
        Person personRes = personService.myReleaseNotCheckedClickLow(person);
        return personRes;
    }


    @RequestMapping("/person/searchpnameBynid")             //根据新闻nid查询pname
    public String searchpnameBynid(int nid) {
        String s = personService.searchpnameBynid(nid);
        return s;
    }

    @RequestMapping("/person/searchNewsBynid")
    public news searchNewsBynid(int nid) {
        news thisNews = personService.searchNewsBynid(nid);
        return thisNews;
    }

    @PostMapping("/person/toRemark")
    public int toRemark(@RequestBody Map map , @RequestHeader String Auth) {           //发表评论
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        map.put("rpid" , pid);
        return personService.toRemark(map);
    }

    @RequestMapping("/person/searchPersonBypid")             //查看个人资料
    public Person searchPersonBypid(int pid , @RequestHeader String Auth) {
        String realPid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        return personService.searchPersonBypid(Integer.parseInt(realPid));
    }

    @RequestMapping("/person/searchRemarksBypid")
    public List<Remark> searchRemarksBypid(int pid , @RequestHeader String Auth) {
        String realPid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        List<Remark> remarks = personService.searchRemarksBypid(Integer.parseInt(realPid));
        return remarks;
    }

    @RequestMapping("/person/changePname")
    public int changePname(Person person , @RequestHeader String Auth) {
        String realPid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(realPid));
        return personService.changePname(person);
    }

    @RequestMapping("/person/changePpassword")
    public int changePpassword(Person person , @RequestHeader String Auth) {
        String realPid = (String) redisTemplate.opsForValue().get("person:token:"+Auth);
        person.setPid(Integer.parseInt(realPid));
        return personService.changePpassword(person);
    }

    @RequestMapping("/person/getAllNewsByRemarkManyOrder")       // 按评论量多 对所有新闻进行排序
    public List<news> getAllNewsByRemarkManyOrder() {
        List<news> allNewsByRemarkManyOrder = personService.getAllNewsByRemarkManyOrder();
        System.out.println(allNewsByRemarkManyOrder);
        return allNewsByRemarkManyOrder;

    }

    @RequestMapping("/person/getAllNewsByRemarkLowOrder")    // 按评论量少 对所有新闻进行排序
    public List<news> getAllNewsByRemarkLowOrder() {
        List<news> allNewsByRemarkLowOrder = personService.getAllNewsByRemarkLowOrder();
        System.out.println(allNewsByRemarkLowOrder);
        return allNewsByRemarkLowOrder;

    }










}
