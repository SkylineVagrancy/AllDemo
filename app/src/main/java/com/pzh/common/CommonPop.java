package com.pzh.common;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pzh.pzhstudy.R;


/**
 * Created by pzh on 15/12/30.
 */
public class CommonPop {
    public static Dialog showLsitPop(Context context,String title,String[] items, final OnListItemClick click){
        View view= LayoutInflater.from(context).inflate(R.layout.listpop_layout,null);
        TextView tvTitle= (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setTextSize(18);
        ListView popListView= (ListView) view.findViewById(R.id.pop_list);
        popListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items));
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click.onItenClick(position);
            }
        });
        Dialog pop=new Dialog(context,R.style.CommonPop);
        pop.setContentView(view);
        return pop;
    }

    public interface OnListItemClick{
        public void onItenClick(int position);
    }
}
