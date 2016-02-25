package com.pzh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pzh.common.BaseFragment;
import com.pzh.pzhstudy.R;

/**
 * Created by junpeng.zhou on 2015/10/28.
 */
public class FindFragment extends BaseFragment {

   private static  FindFragment findFragment;
    public FindFragment(){

    }
    public static FindFragment getNewInstance(){
        if(findFragment==null){
            findFragment=new FindFragment();
        }
        return  findFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.exprss_list_item,null);
        return view;
    }
}
