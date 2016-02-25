package com.pzh.ui;

import android.os.Bundle;
import android.widget.Toast;


import com.pzh.bean.ExpressBean;
import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by junpeng.zhou on 2015/10/14.
 */
public class LinearlayoutUI extends BaseUI {
    ExpressBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout_test);
        bean=new ExpressBean();
        bean.error_code=1;
        bean.data=null;
        bean.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        });


    }
}
