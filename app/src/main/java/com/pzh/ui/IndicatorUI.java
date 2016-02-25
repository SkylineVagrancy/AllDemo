package com.pzh.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pzh.bean.TitleBean;
import com.pzh.common.BaseUI;
import com.pzh.common.CommonTitle;
import com.pzh.pzhstudy.R;
import com.pzh.view.Indicator;

import java.util.ArrayList;

/**
 * Created by pzh on 15/12/25.
 */
public class IndicatorUI extends BaseUI implements View.OnClickListener{
    private Indicator mIndicator;
    private TextView mTabOne;
    private TextView mTabTwo;
    private TextView mTabThree;
    private TextView mTabFour;
    private TextView tv_back;
    private TextView tv_menu;
    private TextView tv_title;
    private ViewPager mContainer;
    private CommonTitle titleView;
    private ArrayList<TextView> mViews=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iindicator_layout);
        mIndicator= (Indicator) findViewById(R.id.indicator);
        mContainer= (ViewPager) findViewById(R.id.container);

        mTabOne= (TextView) findViewById(R.id.tab1);
        mTabTwo= (TextView) findViewById(R.id.tab2);
        mTabThree= (TextView) findViewById(R.id.tab3);
        mTabFour= (TextView) findViewById(R.id.tab4);

        titleView= (CommonTitle) findViewById(R.id.title);
        TitleBean titleBean=new TitleBean("指示器",R.color.title_background,false) {
            @Override
            public void onBackClick() {
                finish();
            }

            @Override
            public void onMenuClick() {

            }
        };
        titleView.initTitle(titleBean);


        mTabFour.setOnClickListener(this);
        mTabThree.setOnClickListener(this);
        mTabTwo.setOnClickListener(this);
        mTabOne.setOnClickListener(this);
        initViews();
    }

    private void initViews() {
        for (int i = 0; i < 4; i++) {
            TextView tv=new TextView(this);
            tv.setText("tab"+i);
            tv.setTextSize(100);
            mViews.add(tv);
        }
        mContainer.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view=mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
        });
        mContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
//                mIndicator.scroll();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab1:

                mContainer.setCurrentItem(0);
                break;
            case R.id.tab2:
                mContainer.setCurrentItem(1);
                break;
            case R.id.tab3:
                mContainer.setCurrentItem(2);
                break;
            case R.id.tab4:
                mContainer.setCurrentItem(3);
                break;

        }

    }

}
