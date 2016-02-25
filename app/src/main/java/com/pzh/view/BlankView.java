package com.pzh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzh on 16/1/14.
 */
public class BlankView extends View {
    private DrawView drawView;
    public BlankView(Context context) {
        super(context);
        drawView=new DrawView(context);
    }

    public BlankView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawView.onDraw(canvas);
        super.onDraw(canvas);
    }
}
