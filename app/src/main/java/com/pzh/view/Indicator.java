package com.pzh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.pzh.pzhstudy.R;


/**
 * Created by pzh on 15/12/16.
 */
public class Indicator extends LinearLayout {
    public Paint mPaint;
    public int mColor;
    private int mTop;
    private int mLeft;
    private int mWidth;
    private int mHeight = 5;
    private int mChildCount;

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.TRANSPARENT);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Indicator, 0, 0);
        mColor = ta.getColor(R.styleable.Indicator_mcolor, 0x0000ff);
        ta.recycle();
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mChildCount = getChildCount();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTop = getMeasuredHeight();
        int width = getMeasuredWidth();
        int height = mTop + mHeight;
        mWidth = width / mChildCount;
        System.out.println("zjp " + mWidth);
        setMeasuredDimension(width, height);
    }

    public void scroll(int position, float offset) {
        mLeft = (int) ((position + offset) * mWidth);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(mLeft, mTop, mLeft + mWidth, mTop + mHeight);
        canvas.drawRect(rect, mPaint);
        super.onDraw(canvas);
    }
}
