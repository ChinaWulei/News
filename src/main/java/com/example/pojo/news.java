package com.example.pojo;

import java.util.Date;
import java.util.List;

public class news {
    private int nid;

    private String ntitle;

    private String ncontent;

    private String nstate;

    private Date ntime;

    private int nclick;

    private int nlike;

    @Override
    public String toString() {
        return "news{" +
                "nid=" + nid +
                ", ntitle='" + ntitle + '\'' +
                ", ncontent='" + ncontent + '\'' +
                ", nstate='" + nstate + '\'' +
                ", ntime=" + ntime +
                ", nclick=" + nclick +
                ", nlike=" + nlike +
                ", person=" + person +
                ", remark=" + remark +
                ", remarkNum=" + remarkNum +
                ", admin=" + admin +
                '}';
    }

    public int getNclick() {
        return nclick;
    }

    public void setNclick(int nclick) {
        this.nclick = nclick;
    }

    public int getNlike() {
        return nlike;
    }

    public void setNlike(int nlike) {
        this.nlike = nlike;
    }

    private Person person;

    private List<Remark> remark;

    private int remarkNum;  //评论数

    private Admin admin;


    public int getRemarkNum() {
        return remarkNum;
    }

    public void setRemarkNum(int remarkNum) {
        this.remarkNum = remarkNum;
    }


    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public String getNstate() {
        return nstate;
    }

    public void setNstate(String nstate) {
        this.nstate = nstate;
    }

    public Date getNtime() {
        return ntime;
    }

    public void setNtime(Date ntime) {
        this.ntime = ntime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Remark> getRemark() {
        return remark;
    }

    public void setRemark(List<Remark> remark) {
        this.remark = remark;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
