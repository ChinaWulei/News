package com.example.pojo;

import java.util.List;

public class Admin {
    private int aid;

    private String aname;

    private String aaccount;

    private String apassword;

    private String asex;

    private List<news> news;

    private List<Remark> remark;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAsex(String asex) {
        this.asex = asex;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAaccount() {
        return aaccount;
    }

    public void setAaccount(String aaccount) {
        this.aaccount = aaccount;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public List<com.example.pojo.news> getNews() {
        return news;
    }

    public String getAsex() {
        return asex;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", aaccount='" + aaccount + '\'' +
                ", apassword='" + apassword + '\'' +
                ", asex='" + asex + '\'' +
                ", news=" + news +
                ", remark=" + remark +
                '}';
    }

    public void setNews(List<com.example.pojo.news> news) {
        this.news = news;
    }

    public List<Remark> getRemark() {
        return remark;
    }

    public void setRemark(List<Remark> remark) {
        this.remark = remark;
    }


}
