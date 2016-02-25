package com.pzh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by pzh on 16/1/14.
 */
public class DrawView {
    private int srcHeight;// 屏幕高度
    private int srcWidth;
    private int viewWidth = 15;//宽度
    private int appendRight = 50;
    private Context context;
    private int mDensity;
    private int mScaleDensity;
    private int textSize;
    private RectF rectF;
    private String[] alls = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "z"};
    private static int STATE_HIDDEN = 0;
    private static int STATE_SHOWING = 1;
    private static int STATE_SHOWN = 2;
    private static int STATE_HIDING = 3;
    private float alphaScale = 1;


    public DrawView(Context context) {

        this.context = context;
        mDensity = (int) context.getResources().getDisplayMetrics().density;
        mScaleDensity = (int) context.getResources().getDisplayMetrics().scaledDensity;
        viewWidth = viewWidth * mDensity;
        appendRight = appendRight * mDensity;


        Log.d("TAG", "mDensity" + mDensity);
        Log.d("TAG", "mScaleDensity" + mScaleDensity);
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha((int) (256 * alphaScale));
        canvas.drawRect(rectF, paint);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(10 * mScaleDensity);

        float mSelectionHeight = (int) (rectF.height() / alls.length);
        float appendTop = (srcHeight - textPaint.descent() + textPaint.ascent()) / 2;
        for (int i = 0; i < alls.length; i++) {
            float paddingLeft = (viewWidth - textPaint.measureText(alls[i])) / 2;
            canvas.drawText(alls[i], rectF.left + paddingLeft, mSelectionHeight * i - textPaint.ascent(), textPaint);
        }
        Log.d("TAG", "onDraw");


    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        srcHeight = h;
        srcWidth = w;
        rectF = new RectF(w - appendRight - viewWidth, 0, w - appendRight, h);
    }

    public void onTouchEvent(MotionEvent event) {

    }

}
