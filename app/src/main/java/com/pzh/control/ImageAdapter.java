package com.pzh.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pzh.bean.NewsBean;
import com.pzh.pzhstudy.R;
import com.pzh.util.ImageLoader;
import com.pzh.util.Util;

import java.util.ArrayList;


/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class ImageAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private int mStart=-1;
    private int mEnd=-1;
    private Context context;
    private ArrayList<NewsBean> list;
    private LayoutInflater inflater;
    private ImageLoader loader;
    private boolean isFrist = true;
    private ReLoadMore loadMore;
    private int totalCount=-1;

    public ImageAdapter(Context context, ArrayList<NewsBean> list,ReLoadMore loadMore) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        loader = ImageLoader.getImageLoaderInstance();
        this.loadMore=loadMore;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewsBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icom);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setTag(list.get(position).picBig);
        viewHolder.tv_name.setText(list.get(position).name);
        viewHolder.icon.setImageBitmap(null);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            setVisibleIcon(view);
        } else {
            loader.stopAllTask();
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = mStart + visibleItemCount;
        totalCount=totalItemCount;

        if (isFrist && visibleItemCount > 0) {
            setVisibleIcon(view);
            isFrist = false;
        }

    }

    public class ViewHolder {
        ImageView icon;
        TextView tv_name;
    }

    public interface ReLoadMore {
        void onHead();
        void onBottom();
    }

    public void setVisibleIcon(final View v) {
        for (int i = mStart; i < mEnd; i++) {
            final int tem = i;
            loader.loadedImage(Util.dp2px(100), Util.dp2px(100), list.get(i).picBig, new ImageLoader.ImageLoaded() {
                @Override
                public void imageLoaded(Bitmap bitmap) {
                    ImageView imageIcon = (ImageView) v.findViewWithTag(list.get(tem).picBig);
                    if (imageIcon != null) {
                        imageIcon.setImageBitmap(bitmap);
                    }
                }

                @Override
                public void onPreImageLoad() {

                }
            });
        }
    }


}
