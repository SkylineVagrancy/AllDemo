package com.pzh.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by junpeng.zhou on 2015/10/23.
 */
public class TestBean extends BmobObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
