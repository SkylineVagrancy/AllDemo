package com.pzh.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by pzh on 15/11/14.
 */
public class AdViewPager extends ViewPager  {
    private int mStart;
    private int mUp;
    private boolean isTouch=false;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    public AdViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdViewPager(Context context) {
        super(context);
    }
    @Override
    public boolean
    onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStart= (int) event.getRawX();
                isTouch=true;
                break;
            case MotionEvent.ACTION_MOVE:
                mUp= (int) event.getRawX();

                if(getCurrentItem() == 0){
                    if(mUp-mStart>50){

                        setCurrentItem(getAdapter().getCount() - 1);
                        return true;
                    }

                }
                if(getCurrentItem()==getAdapter().getCount()-1){
                    if(mUp-mStart<-50){

                        setCurrentItem(0);
                        return true;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                isTouch=false;
                break;

        }

        return super.onTouchEvent(event);
    }


}
