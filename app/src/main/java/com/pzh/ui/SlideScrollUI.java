package com.pzh.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;

/**
 * Created by pzh on 16/1/12.
 */
public class SlideScrollUI extends BaseUI {
    private ListView list;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_scroll_layout);
        inflater=LayoutInflater.from(this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.slide_scroll_item,null);
                }

                return convertView;
            }
        });
    }
}
