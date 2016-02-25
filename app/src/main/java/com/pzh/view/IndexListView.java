package com.pzh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by pzh on 16/1/14.
 */
public class IndexListView extends ListView {
    private DrawView drawView;
    public IndexListView(Context context) {
        this(context, null);

    }

    public IndexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawView=new DrawView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
        drawView.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        drawView.onSizeChanged(w,h,oldw,oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {



        return super.onTouchEvent(ev);
    }
}