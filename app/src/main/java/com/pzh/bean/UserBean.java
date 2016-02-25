package com.pzh.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by junpeng.zhou on 2015/10/26.
 */
public class UserBean extends BmobUser{
    private String sessionId = "";// 会话id
    private String account;// 帐号
    private String name;//认证姓名
    private String certNo;// 身份证
    private String phone;// 手机
    private String nickname="";
    private String betToken="";
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBetToken() {
        return betToken;
    }

    public void setBetToken(String betToken) {
        this.betToken = betToken;
    }


}
