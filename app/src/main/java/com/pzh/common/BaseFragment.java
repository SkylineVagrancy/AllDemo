package com.pzh.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pzh.config.AppConfig;

import cn.bmob.v3.Bmob;

/**
 * Created by junpeng.zhou on 2015/10/28.
 */
public class BaseFragment extends Fragment {
    public Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
        Bmob.initialize(context, AppConfig.BmobKey);
    }
}
