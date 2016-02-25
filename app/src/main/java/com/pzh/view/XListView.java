package com.pzh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by junpeng.zhou on 2015/10/10.
 */
public class XListView extends ListView implements AbsListView.OnScrollListener {
    private XListViewHeadView headView;

    private int mDown = -1;
    private int mMOve = -1;
    private int mUp = -1;
    private int diatance = 0;
    private int mTotalCount;
    private boolean isFrist = true;
    private boolean isHeadShow = false;
    private boolean isFootShow = false;

    /**
     * 正常状态
     */
    private static final int STATUS_NORMAL = 1;

    /**
     * 下拉了，但是不刷新，松开之后自动返回
     */
    private static final int STATUS_PULL = 2;

    /**
     * 松开刷新
     */
    private static final int STATUS_RELESE_REFRESH = 3;

    /**
     * 正在刷新
     */
    private static final int STATUS_REFRESHING = 4;

    private int currentStatus = STATUS_NORMAL;

    private int topHeight;
    private MarginLayoutParams params;
    private LinearLayout.LayoutParams lp;
    private RotateAnimation rotateUp;


    public XListView(Context context) {
        super(context);
        init(context);
    }


    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {

        rotateUp = new RotateAnimation(0f, 180f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateUp.setDuration(180);
        rotateUp.setFillAfter(true);
        headView = new XListViewHeadView(context);
        addHeaderView(headView);
        headView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                topHeight = headView.getHeight();
                headView.setVisibaleHeight(0);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                System.out.println("pzh topHeight:" + topHeight);
            }
        });


        setOnScrollListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDown = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMOve = (int) ev.getRawY();
                diatance = mMOve - mDown;
                System.out.println("pzh + diatace:" + diatance);
                System.out.println("pzh + fristVisiblePosition:" + getFirstVisiblePosition());
                if (getFirstVisiblePosition() == 0 && diatance > 0) {
                    if (diatance > headView.height && currentStatus != STATUS_RELESE_REFRESH) {
                        currentStatus = STATUS_RELESE_REFRESH;
                        System.out.println("pzh 到了这里");
                        headView.arrowImage.startAnimation(rotateUp);

                    }
                    headView.setVisibaleHeight(diatance);
                }


                break;
            case MotionEvent.ACTION_UP:
                mUp = (int) ev.getRawY();

                break;

        }

        return super.onTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalCount = totalItemCount - 1;

    }


}
