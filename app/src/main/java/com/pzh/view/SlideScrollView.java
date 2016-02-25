package com.pzh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by pzh on 16/1/12.
 */
public class SlideScrollView extends HorizontalScrollView {
    public SlideScrollView(Context context) {
        this(context, null);
    }

    public SlideScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
