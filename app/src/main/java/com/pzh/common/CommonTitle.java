package com.pzh.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pzh.bean.TitleBean;
import com.pzh.pzhstudy.R;

/**
 * Created by pzh on 15/12/25.
 */
public class CommonTitle extends LinearLayout{
    private TextView tv_back;
    private TextView tv_menu;
    private TextView tv_title;
    private View view;
    public CommonTitle(Context context) {
        this(context, null);
    }

    public CommonTitle( final Context context, AttributeSet attrs) {
        super(context, attrs);
        view=LayoutInflater.from(context).inflate(R.layout.comon_title,this);
        tv_back= (TextView) view.findViewById(R.id.tv_back);
        tv_menu= (TextView) view.findViewById(R.id.tv_menu);
        tv_title= (TextView) view.findViewById(R.id.tv_title);
    }
    public void initTitle(final TitleBean titleBean){
        view.setBackgroundColor(titleBean.resourceId);
        tv_title.setText(titleBean.title);
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBean.onBackClick();
            }
        });
        tv_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBean.onMenuClick();
            }
        });
        if(titleBean.menuIsShow){
            tv_menu.setVisibility(VISIBLE);
        }else{
            tv_menu.setVisibility(GONE);
        }
    }


}
