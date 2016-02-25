package com.pzh.bean;



/**
 * Created by pzh on 15/12/29.
 */
public abstract class TitleBean  {
    public String title;
    public int resourceId;
    public boolean menuIsShow;
    public abstract void onBackClick();
    public abstract void onMenuClick();

    public TitleBean(String title,int resourceId,boolean menuIsShow){
        this.title=title;
        this.resourceId=resourceId;
        this.menuIsShow=menuIsShow;
    }

}
