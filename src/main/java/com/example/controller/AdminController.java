package com.example.controller;

import com.example.pojo.Admin;
import com.example.pojo.Person;
import com.example.pojo.Remark;
import com.example.pojo.news;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("admin/login")
    public Admin login(Admin admin){
        Admin res = adminService.login(admin);
        return res;
    }

    @RequestMapping("admin/allNews")              //查看所有新闻列表
    public List<news> allNews() {                     //查看所有新闻
        List<news> news = adminService.allNews();

        return news;
    };

    @RequestMapping("/admin/newsChecked")           //把该条新闻状态改为 ‘已审核’
    public int newsChecked(int nid) {
        return adminService.newsChecked(nid);
    }

    @RequestMapping("/admin/newsNotChecked")        //把该条新闻状态改为 ‘未审核’
    public int newsNotChecked(int nid) {

        return adminService.newsNotChecked(nid);
    }

    @RequestMapping("/admin/remarkChecked")        //把该条评论状态改为 ‘已审核’
    public int remarkChecked(int rid) {
        return adminService.remarkChecked(rid);
    }

    @RequestMapping("/admin/remarkNotChecked")        //把该条新闻状态改为 ‘未审核’
    public int remarkNotChecked(int rid) {
        return adminService.remarkNotChecked(rid);
    }



    @RequestMapping("/admin/allRemarks")
    public List<Remark> allRemarks() {
        List<Remark> remarks = adminService.allRemarks();
        System.out.println(remarks);
        return remarks;

    }


    @RequestMapping("/admin/searchNewByNid")
    public news searchNewByNid(int nid) {
        return adminService.searchNewByNid(nid);
    }

    @RequestMapping("/admin/newsPrize")             //查看所有用户资料
    public List<Person> newsPrize() {
        List<Person> people = adminService.newsPrize();
        System.out.println(people);
        return people;
    }

    @RequestMapping("/admin/remarksPrize")             //查看所有用户资料
    public List<Person> remarksPrize() {
        List<Person> people = adminService.remarksPrize();
        System.out.println(people);
        return people;
    }

    @RequestMapping("/admin/seeAdmin")             //查看管理员个人资料
    public Admin seeAdmin() {
        return adminService.seeAdmin();
    }


}
