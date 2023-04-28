package com.example.pojo;

import java.util.List;

public class Person {
    private int pid;
    private String pname;
    private String paccount;
    private String ppassword;
    private String psex;
    private int page;

    private int newsNum;        //新闻发布量

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", paccount='" + paccount + '\'' +
                ", ppassword='" + ppassword + '\'' +
                ", psex='" + psex + '\'' +
                ", page=" + page +
                ", newsNum=" + newsNum +
                ", remarkNum=" + remarkNum +
                ", news=" + news +
                ", remark=" + remark +
                '}';
    }

    public int getNewsNum() {
        return newsNum;
    }

    public void setNewsNum(int newsNum) {
        this.newsNum = newsNum;
    }

    private int remarkNum;

    public int getRemarkNum() {
        return remarkNum;
    }

    public void setRemarkNum(int remarkNum) {
        this.remarkNum = remarkNum;
    }

    private List<news> news;                     //一个用户所发布的新闻

    private List<Remark> remark;                    //一个用户所发表的评论


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPaccount() {
        return paccount;
    }

    public void setPaccount(String paccount) {
        this.paccount = paccount;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<com.example.pojo.news> getNews() {
        return news;
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
