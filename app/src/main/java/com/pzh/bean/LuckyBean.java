package com.pzh.bean;

/**
 * Created by pzh on 15/12/29.
 */
public class LuckyBean {
    public String getLuckyNum() {
        return luckyNum;
    }

    public void setLuckyNum(String luckyNum) {
        this.luckyNum = luckyNum;
    }

    public boolean isLucky() {
        return isLucky;
    }

    public void setIsLucky(boolean isLucky) {
        this.isLucky = isLucky;
    }

    private String luckyNum;
    private boolean isLucky=false;
}
