package com.pzh.ui;


import android.support.v4.widget.DrawerLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.pzh.bean.InfoBean;
import com.pzh.common.BaseUI;
import com.pzh.config.AppConfig;
import com.pzh.control.AllUiAdapter;
import com.pzh.pzhstudy.R;
import com.pzh.util.Util;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseUI {
    private LinearLayout left_container;
    private LinearLayout content_container;
    private ListView mListView;
    private List<InfoBean> data = new ArrayList<InfoBean>();
    private AllUiAdapter adapter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initDisplay();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.main_listview);
        adapter = new AllUiAdapter(this, data);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        String result = Util.getDataFromAssets(MainActivity.this, "all_activity.xml");
        data = JSON.parseArray(result, InfoBean.class);
        System.out.println(result);
    }

    public void initDisplay() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        AppConfig.Src_height = metrics.heightPixels;
        AppConfig.Src_width = metrics.widthPixels;
        AppConfig.density = metrics.densityDpi;
    }
}
