package com.pzh.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;
import com.pzh.view.XListView;

import java.util.ArrayList;

/**
 * Created by junpeng.zhou on 2015/10/10.
 */
public class RefreshActivity extends BaseUI {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private XListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refreshview);
        for (int i = 0; i < 100; i++) {
            String tem = "test " + i;
            list.add(tem);
        }
        listView= (XListView) findViewById(R.id.refreshListView);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}
