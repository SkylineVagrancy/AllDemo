package com.pzh.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.pzh.bean.InfoBean;
import com.pzh.common.BaseUI;
import com.pzh.config.AppConfig;
import com.pzh.control.AllUiAdapter;
import com.pzh.pzhstudy.R;
import com.pzh.util.ActivityTool;
import com.pzh.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by junpeng.zhou on 2015/10/15.
 */
public class AllDemoUI extends BaseUI {
    private ListView mListView;
    private List<InfoBean> data=new ArrayList<InfoBean>();
    private AllUiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alldemo);
        initDisplay();
        mListView= (ListView) findViewById(R.id.alldemoListView);
        String result= Util.getDataFromAssets(AllDemoUI.this, "all_activity.xml");
        System.out.println("pzh result:" + result);
        data = JSON.parseArray(result, InfoBean.class);
        adapter=new AllUiAdapter(this,data);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityTool.forwardActivity(AllDemoUI.this, data.get(position).getName());
            }
        });
    }
    public void initDisplay(){
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        AppConfig.Src_height=metrics.heightPixels;
        AppConfig.Src_width=metrics.widthPixels;
        AppConfig.density=metrics.densityDpi;
    }
}
