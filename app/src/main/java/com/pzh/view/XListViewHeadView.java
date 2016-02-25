package com.pzh.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pzh.pzhstudy.R;
import com.pzh.util.Util;


/**
 * Created by junpeng.zhou on 2015/10/10.
 */
public class XListViewHeadView extends LinearLayout {
    private LayoutInflater inflater;
    /**
     * 箭头图标
     */
    public ImageView arrowImage;
    /**
     * 提示语
     */
    private TextView tv_hint;
    /**
     * 上次更新时间
     */
    private TextView tv_time;
    private LinearLayout mContainer;



    private RotateAnimation rotateDown;
    private RotateAnimation rotateUp;
    public int height;

    public static final int STATUS_TO_DOWN=1;
    public static final int STATUS_TO_UP=2;



    public XListViewHeadView(Context context) {
        super(context);
        init(context);
    }


    public XListViewHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        setOrientation(VERTICAL);
        mContainer = (LinearLayout) inflater.inflate(R.layout.headview, null);
        addView(mContainer);
        arrowImage = (ImageView) mContainer.findViewById(R.id.arrow);
        tv_hint = (TextView) mContainer.findViewById(R.id.tv_hint);
        tv_time = (TextView) mContainer.findViewById(R.id.tv_refreshtime);
        rotateDown = new RotateAnimation(180f, 0f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateUp = new RotateAnimation(0f, 180f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                height= Util.px2dp(mContainer.getHeight());
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                System.out.println("pzh headview:"+height);
            }
        });
    }

    public void setVisibaleHeight(int height) {
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height/5;

        mContainer.setLayoutParams(lp);

    }

    public void setStatus(int status){
        if(status==STATUS_TO_DOWN){
            arrowImage.startAnimation(rotateUp);
        }
        if(status==STATUS_TO_UP){
            arrowImage.clearAnimation();
        }

    }




}
