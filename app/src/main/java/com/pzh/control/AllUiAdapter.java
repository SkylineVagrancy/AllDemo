package com.pzh.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pzh.bean.InfoBean;
import com.pzh.pzhstudy.R;
import com.pzh.util.ActivityTool;
import com.pzh.util.Util;

import java.util.List;

/**
 * Created by junpeng.zhou on 2015/10/15.
 */
public class AllUiAdapter extends BaseAdapter {
    private List<InfoBean> data;
    private LayoutInflater inflater;
    private Context context;
    public AllUiAdapter(Context context, List<InfoBean> list) {
        data = list;
        inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return data.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int arg0, View content, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if (content == null) {
            viewHolder = new ViewHolder();
            content = inflater.inflate(R.layout.alldemo_listview_item, null);
            viewHolder.title = (TextView) content.findViewById(R.id.title);
            viewHolder.content = (TextView) content.findViewById(R.id.content);
            content.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) content.getTag();
        }
        viewHolder.title.setText(data.get(arg0).getTitle());
        viewHolder.content.setText(data.get(arg0).getContent());
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(data.get(arg0).getName());
                ActivityTool.forwardActivity(context, data.get(arg0).getName());
            }
        });
        return content;
    }


    class ViewHolder {
        TextView title;
        TextView content;
    }

}
