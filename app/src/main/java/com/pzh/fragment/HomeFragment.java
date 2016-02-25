package com.pzh.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pzh.common.BaseFragment;
import com.pzh.pzhstudy.R;
import com.pzh.view.AdSlidView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Vector;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;

/**
 * Created by junpeng.zhou on 2015/10/28.
 */
public class HomeFragment extends BaseFragment {
    public static HomeFragment homeFragment;
    private AdSlidView adSlidView;
    private Vector<View> views = new Vector<>();
    public ArrayList<String> adList = new ArrayList<>();
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                init(adList);
            }
        }
    };

    public static HomeFragment getNewInstance(){
        if(homeFragment ==null){
            homeFragment=new HomeFragment();
        }
        return homeFragment;
    }


    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        adSlidView = (AdSlidView) view.findViewById(R.id.main_adview);
        queryData(mHandler);

        return view;
    }

    public void init(ArrayList<String> list) {

        adSlidView.initAdView(list);
    }


    public void queryData(final Handler temHandler) {
        BmobQuery query = new BmobQuery("AdList");
        query.findObjects(context, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                int size = jsonArray.length();
                for (int i = 0; i < size; i++) {
                    String tem = "";
                    System.out.println();
                    try {
                        tem = jsonArray.getJSONObject(i).getString("adurl");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adList.add(tem);

                }
                temHandler.sendEmptyMessage(0x123);
            }

            @Override
            public void onFailure(int i, String s) {
                System.out.println(s + "下载失败");

                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
