package com.pzh.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.pzh.bean.AllNewsBean;
import com.pzh.bean.NewsBean;
import com.pzh.common.BaseUI;
import com.pzh.config.AppConfig;
import com.pzh.control.ImageAdapter;
import com.pzh.pzhstudy.R;
import com.pzh.util.ImageLoader;
import com.pzh.util.NetConnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyActivity extends BaseUI {
    /**
     * Called when the activity is first created.
     */
    private ListView listView;
    private ImageLoader loader;
    private AllNewsBean allNewsBean = new AllNewsBean();
    private ArrayList<NewsBean> allNewsList = new ArrayList<NewsBean>();
    private String datas;
    private ImageAdapter adapter;
    private int index = 1;
    private ProgressDialog dialog;
    private Button btn_to;


    private void updateView() {
        adapter = new ImageAdapter(this, allNewsList, new ImageAdapter.ReLoadMore() {
            @Override
            public void onHead() {

            }

            @Override
            public void onBottom() {
//                index++;
//                if(index<=3){
//                    getData(index);
//                }

            }
        });
        listView.setAdapter(adapter);
        listView.setOnScrollListener(adapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
        dialog = new ProgressDialog(this);

        dialog.setTitle("请稍后，正在加载中");

        getData(index);

    }

    private void init() {
        loader = ImageLoader.getImageLoaderInstance();
        listView = (ListView) findViewById(R.id.listview);
        btn_to= (Button) findViewById(R.id.btn_to);
        btn_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyActivity.this,LinearlayoutUI.class);
                startActivity(intent);
            }
        });
        updateView();
    }

    public String getDataFromAssets() {
        String result = "";
        try {
            InputStreamReader reader = new InputStreamReader(getResources().getAssets().open("image.xml"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tem = "";
            while ((tem = bufferedReader.readLine()) != null) {
                result += tem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void getData(int count) {
       Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int returnCode=msg.what;
                if(returnCode == 2){
                    String result = (String) msg.obj;
                    Log.d("pzh", result);
                    allNewsBean = JSON.parseObject(result, AllNewsBean.class);
                    allNewsList.clear();
                    allNewsList.addAll(allNewsBean.data);
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    adapter.notifyDataSetChanged();

                }
            }
        };
        String path = AppConfig.getDataUrl(count);
        NetConnect.getNetData(path, mHandler, 2);
    }
}
