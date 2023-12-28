package com.ceshiren.page.entity;

public class User {
    public String username;
    public String acctid;
    public String tel;

    public User(String username, String acctid, String tel) {
        this.username = username;
        this.acctid = acctid;
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAcctid() {
        return acctid;
    }

    public void setAcctid(String acctid) {
        this.acctid = acctid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
