package com.example.service.Impl;

import com.example.mapper.AdminMapper;
import com.example.pojo.Admin;
import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public List<news> allNews() {
        return adminMapper.allNews();
    }

    @Override
    public int newsChecked(int nid) {
        return adminMapper.newsChecked(nid);
    }

    @Override
    public int newsNotChecked(int nid) {
        return adminMapper.newsNotChecked(nid);
    }



    @Override
    public List<Remark> allRemarks() {
        return adminMapper.allRemarks();
    }

    @Override
    public news searchNewByNid(int nid) {
        return adminMapper.searchNewByNid(nid);
    }

    @Override
    public int remarkChecked(int rid) {
        return adminMapper.remarkChecked(rid);
    }

    @Override
    public int remarkNotChecked(int rid) {
        return adminMapper.remarkNotChecked(rid);
    }



    @Override
    public List<Person> newsPrize() {
        return adminMapper.newsPrize();
    }

    @Override
    public List<Person> remarksPrize() {
        return adminMapper.remarksPrize();
    }

    @Override
    public Admin seeAdmin() {
        return adminMapper.seeAdmin();
    }
}
