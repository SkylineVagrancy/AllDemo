package com.pzh.common;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.pzh.config.AppConfig;
import com.pzh.config.AppData;

import cn.bmob.v3.Bmob;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class BaseUI extends FragmentActivity {
    public Context context;
    public FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        fm=getSupportFragmentManager();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bmob.initialize(this, AppConfig.BmobKey);
        AppData.activityList.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppData.activityList.remove(this);
    }
}
