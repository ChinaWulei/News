package com.example.service;

import com.example.mapper.PersonMapper;
import com.example.pojo.Admin;
import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AdminService {

    Admin login(Admin admin);
    public List<news> allNews();

    public int newsChecked(int nid);

    public int newsNotChecked(int nid);

    public int remarkChecked(int rid);

    public int remarkNotChecked(int rid);

    public List<Remark> allRemarks();

    public news searchNewByNid(int nid);

    public List<Person> newsPrize();

    public List<Person> remarksPrize();

    public Admin seeAdmin();








}
