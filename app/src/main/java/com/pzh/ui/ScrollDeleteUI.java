package com.pzh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;
import com.pzh.view.SlideCuntListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pzh on 16/1/12.
 */
public class ScrollDeleteUI extends BaseUI implements SlideCuntListView.RemoveListener {
    private SlideCuntListView slideCutListView ;
    private ArrayAdapter<String> adapter;
    private List<String> dataSourceList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_delete_layout);
        init();
    }

    private void init() {
        slideCutListView = (SlideCuntListView) findViewById(R.id.slideCutListView);
        slideCutListView.setmRemoveListener(this);

        for(int i=0; i<20; i++){
            dataSourceList.add("滑动删除" + i);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.scroll_delete_item, R.id.list_item, dataSourceList);
        slideCutListView.setAdapter(adapter);

        slideCutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(context, dataSourceList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //滑动删除之后的回调方法
    @Override
    public void removeItem(SlideCuntListView.RemoveDirection direction, int position) {
        adapter.remove(adapter.getItem(position));
        switch (direction) {
            case RIGHT:
                Toast.makeText(this, "向右删除  "+ position, Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "向左删除  "+ position, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }
}
