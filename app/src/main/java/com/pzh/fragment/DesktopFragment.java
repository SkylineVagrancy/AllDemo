package com.pzh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pzh.common.BaseFragment;
import com.pzh.pzhstudy.R;

import java.util.ArrayList;

/**
 * Created by pzh on 15/11/23.
 */
public class DesktopFragment extends BaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private LinearLayout container;
    private TextView homeIcon;
    private TextView homeName;
    private TextView findIcon;
    private TextView findName;
    private TextView myIcon;
    private TextView myName;
    private LinearLayout ll_home;
    private LinearLayout ll_find;
    private LinearLayout ll_my;
    private TextView[] icons;
    private TextView[] names;
    private int[] iconIds;
    private ViewPager pager;
    private ArrayList<BaseFragment> fragmentList=new ArrayList<>();
    private HomeFragment homeFragment;
    private FindFragment findFragmen;
    private MyFragment myFragment;
    private DesktopPager adapter;
    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_desktop,null);
        init();
        pager = (ViewPager) view.findViewById(R.id.desktopPager);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);
        homeIcon = (TextView) view.findViewById(R.id.desktop_home_icon);
        homeName = (TextView) view.findViewById(R.id.desktop_home_name);
        findIcon = (TextView) view.findViewById(R.id.desktop_find_icon);
        findName = (TextView) view.findViewById(R.id.desktop_find_name);
        myIcon = (TextView) view.findViewById(R.id.desktop_my_icon);
        myName = (TextView) view.findViewById(R.id.desktop_my_name);
        ll_home = (LinearLayout) view.findViewById(R.id.homeContainer);
        ll_find = (LinearLayout) view.findViewById(R.id.findContainer);
        ll_my = (LinearLayout) view.findViewById(R.id.myContainer);
        icons[0] = homeIcon;
        icons[1] = findIcon;
        icons[2] = myIcon;
        names[0] = homeName;
        names[1] = findName;
        names[2] = myName;
        setSelectIcon(0);
        setOnClick();
        return view;

    }

    private void init() {
        fm=getFragmentManager();
        icons = new TextView[3];
        names = new TextView[3];

        iconIds = new int[]{R.drawable.main_desktab_gray,
                R.drawable.main_desktab_green,
                R.drawable.main_desktop_find_gray,
                R.drawable.main_desktop_find_green,
                R.drawable.main_desktop_my_gray,
                R.drawable.main_desktop_my_green};
        homeFragment=HomeFragment.getNewInstance();
        findFragmen=FindFragment.getNewInstance();
        myFragment=MyFragment.getNewInstance();
        fragmentList.add(homeFragment);
        fragmentList.add(findFragmen);
        fragmentList.add(myFragment);
        adapter=new DesktopPager(fm);

    }
    private void setOnClick() {
        ll_my.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_home.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setSelectIcon(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeContainer:
                setSelectIcon(0);

                break;
            case R.id.findContainer:
                setSelectIcon(1);

                break;
            case R.id.myContainer:
                setSelectIcon(2);
                break;
        }
    }

    public class DesktopPager extends FragmentPagerAdapter {

        public DesktopPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    private void setSelectIcon(int position) {
        pager.setCurrentItem(position);
        for (int i = 0; i < 3; i++) {
            if (i == position) {
                icons[i].setBackgroundResource(iconIds[2 * i + 1]);
            } else {
                icons[i].setBackgroundResource(iconIds[2 * i]);
            }
        }
    }
}
