package com.example.service;

import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;

import java.util.List;
import java.util.Map;

public interface PersonService {

    public Map login(Person person);

    public void logout(String token);

    public Person register(Person person);

    public List<news> allNews();

    public int release(Map map);

    public Person myRelease(Person person);

    public Person myReleaseAllClickMany(Person person);

    public Person myReleaseAllClickLow(Person person);

    public Person myReleaseCheckedClickMany(Person person);

    public Person myReleaseCheckedClickLow(Person person);

    public Person myReleaseNotCheckedClickMany(Person person);

    public Person myReleaseNotCheckedClickLow(Person person);

    public String searchpnameBynid(int nid);

    public news searchNewsBynid(int nid);

    public int toRemark(Map map);

    public Person searchPersonBypid(int pid);

    public List<Remark> searchRemarksBypid(int pid);

    public int changePname(Person person);

    public int changePpassword(Person person);

    public List<news> getAllNewsByRemarkManyOrder();

    public List<news> getAllNewsByRemarkLowOrder();


}
