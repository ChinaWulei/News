package com.example.pojo;

import java.util.Date;

public class Remark {
    private int rid;

    private String rcontent;

    private Date rtime;

    private String rstate;

    private int rnum;

    @Override
    public String toString() {
        return "Remark{" +
                "rid=" + rid +
                ", rcontent='" + rcontent + '\'' +
                ", rtime=" + rtime +
                ", rstate='" + rstate + '\'' +
                ", rnum=" + rnum +
                ", news=" + news +
                ", person=" + person +
                ", admin=" + admin +
                '}';
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    private news news;

    private Person person;

    private Admin admin;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRcontent() {
        return rcontent;
    }

    public void setRcontent(String rcontent) {
        this.rcontent = rcontent;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public String getRstate() {
        return rstate;
    }

    public void setRstate(String rstate) {
        this.rstate = rstate;
    }

    public com.example.pojo.news getNews() {
        return news;
    }

    public void setNews(com.example.pojo.news news) {
        this.news = news;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
