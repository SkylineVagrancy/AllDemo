package com.pzh.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;
import com.pzh.view.BlankView;
import com.pzh.view.DrawView;
import com.pzh.view.IndexListView;

/**
 * Created by pzh on 16/1/14.
 */
public class DrawTest extends BaseUI {
    private IndexListView list;
    private String[] alls = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "z"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_test_layout);
        list= (IndexListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String >(context,android.R.layout.simple_list_item_1,alls));
    }

}
