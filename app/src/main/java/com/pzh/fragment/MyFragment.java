package com.pzh.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pzh.common.BaseFragment;
import com.pzh.pzhstudy.R;

/**
 * Created by junpeng.zhou on 2015/10/28.
 */
public class MyFragment extends BaseFragment {

    private static MyFragment myFragment;
    public MyFragment(){}

    public static  MyFragment getNewInstance(){
        if(myFragment==null){
            myFragment=new MyFragment();
        }
        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.express,null);
        return view;
    }
}
