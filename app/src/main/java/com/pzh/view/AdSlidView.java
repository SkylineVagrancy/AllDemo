package com.pzh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pzh.pzhstudy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by junpeng.zhou on 2015/10/29.
 */
public class AdSlidView extends FrameLayout {
    private AdViewPager viewPager;
    private View contentView;
    private LinearLayout dotContainer;
    private AdAdapter adAdapter;
    private List<String> viewLists;
    private Vector<View> dotViews = new Vector<>();
    private Context context;
    public static int maxCount=1000;
    private int viewSize;
    public AdSlidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.main_ad_layout, null);
        viewPager = (AdViewPager) contentView.findViewById(R.id.ad_viewpager);
        dotContainer = (LinearLayout) contentView.findViewById(R.id.main_dot_container);
        addView(contentView);
    }

    public AdSlidView(Context context) {
        super(context, null);
    }

    public void initAdView(ArrayList<String> viewList){
        viewLists=viewList;
        adAdapter=new AdAdapter();
        viewSize=viewList.size();
        viewPager.setAdapter(adAdapter);
        viewPager.setCurrentItem(maxCount/2);
    }




    public class AdAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return maxCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int tem=position%viewSize;
            final ImageView iv=new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(viewLists.get(tem),iv);
            ImageLoader.getInstance().loadImage(viewLists.get(tem), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    System.out.println("failed");
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    System.out.println("pzh:complete");
                    iv.setImageBitmap(loadedImage);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
