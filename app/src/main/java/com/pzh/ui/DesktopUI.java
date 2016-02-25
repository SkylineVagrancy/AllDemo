package com.pzh.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.pzh.bean.InfoBean;
import com.pzh.common.BaseUI;
import com.pzh.config.AppConfig;
import com.pzh.control.AllUiAdapter;
import com.pzh.fragment.DesktopFragment;
import com.pzh.pzhstudy.R;
import com.pzh.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junpeng.zhou on 2015/10/28.
 */
public class DesktopUI extends BaseUI {
    private ListView demoList;
    private List<InfoBean> data = new ArrayList<InfoBean>();
    private AllUiAdapter adapter;
    private DesktopFragment desttopFragment;
    private LinearLayout content;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop);
        initDisplay();
        initData();
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.argb(0x99, 0x00, 0x00, 0x00));
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        demoList= (ListView) findViewById(R.id.left_drawer);
        adapter = new AllUiAdapter(this, data);
        demoList.setAdapter(adapter);
        content= (LinearLayout) findViewById(R.id.content_frame);
        fm.beginTransaction().add(R.id.content_frame,new DesktopFragment()).commit();
    }
    private void initData() {
        String result = Util.getDataFromAssets(DesktopUI.this, "all_activity.xml");
        data = JSON.parseArray(result, InfoBean.class);
        System.out.println(result);
    }
    public void initDisplay(){
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        AppConfig.Src_height=metrics.heightPixels;
        AppConfig.Src_width=metrics.widthPixels;
        AppConfig.density=metrics.densityDpi;
    }
}
