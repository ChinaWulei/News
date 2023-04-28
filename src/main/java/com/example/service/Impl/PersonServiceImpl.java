package com.example.service.Impl;

import com.example.Utils.DateConvert;
import com.example.mapper.PersonMapper;
import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PersonMapper personMapper;

    @Override
    public Map login(Person person) {                //用户登录
        Person result = personMapper.login(person);
        if (result == null)            //用户不存在，登陆失败
            return null;
        /* 用户存在，生成token并保存到Redis ，有效期为30分钟 */
        String token = UUID.randomUUID().toString().replace("-" , "");
        String value = String.valueOf(result.getPid());
        redisTemplate.opsForValue().set("person:token:"+token , value , 30 , TimeUnit.MINUTES);  //在redis存储token值,有效期30分钟
        Map map = new HashMap();
        map.put("person" , result);
        map.put("token" , token);
        map.put("pid" , result.getPid());
        return map;
    }

    @Override
    public void logout(String token) {
        Object o = redisTemplate.opsForValue().get("person:token:" + token);
        System.out.println(o);
        if (o != null || o!= "")
            redisTemplate.delete("person:token:" + token);

    }

    @Override
    public Person myReleaseAllClickMany(Person person) {
        return personMapper.myReleaseAllClickMany(person);
    }

    @Override
    public Person myReleaseAllClickLow(Person person) {
        return personMapper.myReleaseAllClickLow(person);
    }

    @Override
    public Person myReleaseCheckedClickMany(Person person) {
        return personMapper.myReleaseCheckedClickMany(person);
    }

    @Override
    public Person myReleaseCheckedClickLow(Person person) {
        return personMapper.myReleaseCheckedClickLow(person);
    }

    @Override
    public Person myReleaseNotCheckedClickMany(Person person) {
        return personMapper.myReleaseNotCheckedClickMany(person);
    }

    @Override
    public Person myReleaseNotCheckedClickLow(Person person) {
        return personMapper.myReleaseNotCheckedClickLow(person);
    }

    @Override
    public Person register(Person person) {
        while(true){
            String paccount = "";
            Random random = new Random();
            int i = (random.nextInt(9))+1;
            String s = String.valueOf(i);
            paccount+=s;
            for(int j = 0 ; j<9 ; j++){
                s = String.valueOf(random.nextInt(10));
                paccount+=s;
            }
            person.setPaccount(paccount);
            Person person1 = personMapper.sameAccount(person);
            if (person1 == null)
                break;
        }
        personMapper.register(person);
        return person;

    }

    @Override
    public List<news> allNews() {
        return personMapper.allNews();
    }

    @Override
    public int release(Map map) {
        String token = (String) map.get("token");
        String pid = (String) redisTemplate.opsForValue().get("person:token:"+token);
        map.put("pid" , pid);
        Date now = new Date();
        String nowTime = DateConvert.DateToMySQLDateTimeString(now);
        map.put("ntime" , nowTime);
        return personMapper.release(map);

    }

    @Override
    public Person myRelease(Person person) {
        return personMapper.myRelease(person);
    }

    @Override
    public String searchpnameBynid(int nid) {
        return personMapper.searchpnameBynid(nid);
    }

    @Override
    @Transactional
    public news searchNewsBynid(int nid) {
        /* 1. 先在缓存中查询是否有该条新闻 */
        Object newsdetail = redisTemplate.opsForValue().get("person:newsdetail:" + nid);
        /* 2. 若缓存中存在该条新闻的数据，但是是空值 */
        if(newsdetail != null ) {
            System.out.println("缓存中有数据");
            if(String.valueOf(newsdetail).equals("")) {
                System.out.println("数据为空值，不要再攻击我的系统啦！");
                return null;
            }
            /* 2. 若缓存中存在该条新闻的数据并且不是空值 */
            return (news)newsdetail;
        }

        /* 3. 若缓存中不存在该条新闻的数据，则先去数据库中查询，再把查询到的数据写回缓存，然后把数据返回给前端 */
        else {
            System.out.println("缓存中没有数据");
            news news = personMapper.searchNewsBynid(nid);
            /* 4. 若数据库中也查询不到该条新闻id所对应的数据，则往redis中添加空数据，防止缓存穿透问题 */
            if(news == null) {
                redisTemplate.opsForValue().set("person:newsdetail:" + nid , "" , 2 , TimeUnit.MINUTES);
                System.out.println("不存在nid为" + nid + "的新闻的数据！别再攻击我的系统了，求你了！！！");
                return null;
            }
            /* 5. 若数据库中存在该条新闻， 则先把该条新闻数据写入Redis，再把数据返回给前端 */
            redisTemplate.opsForValue().set("person:newsdetail:" + nid , news , 30 , TimeUnit.MINUTES);
            return news;
        }
    }

    @Override
    public Person searchPersonBypid(int pid) {
        return personMapper.searchPersonBypid(pid);
    }

    @Override
    public int toRemark(Map map) {
        Date now = new Date();
        String nowTime = DateConvert.DateToMySQLDateTimeString(now);
        map.put("rtime" , nowTime);
        return personMapper.toRemark(map);
    }

    @Override
    public List<Remark> searchRemarksBypid(int pid) {
        return personMapper.searchRemarksBypid(pid);
    }

    @Override
    public int changePname(Person person) {
        return personMapper.changePname(person);
    }

    @Override
    public int changePpassword(Person person) {
        return personMapper.changePpassword(person);
    }

    @Override
    public List<news> getAllNewsByRemarkManyOrder() {
        return personMapper.getAllNewsByRemarkManyOrder();
    }

    public List<news> getAllNewsByRemarkLowOrder() {
        return personMapper.getAllNewsByRemarkLowOrder();
    }
}
