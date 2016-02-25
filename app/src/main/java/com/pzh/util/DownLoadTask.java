package com.pzh.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class DownLoadTask extends AsyncTask<String, Integer, Bitmap> {


    private ImageLoader.ImageLoaded imageLoaded;
    private LruCache<String, Bitmap> mLruCache;


    public DownLoadTask(LruCache<String, Bitmap> mLruCache, ImageLoader.ImageLoaded imageLoaded) {
        this.imageLoaded = imageLoaded;
        this.mLruCache = mLruCache;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String path = params[0];
        Log.d("Net", path);
        System.out.println("zjp + path=" + path);
        Bitmap bitmap = mLruCache.get(path);
        if (bitmap == null) {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(in);
                } else {
                    Log.d("Net", "pzh +" + path + "," + conn.getResponseMessage());
                }
                if(bitmap != null ){
                    mLruCache.put(path,bitmap);
                }
                in.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.d("zjp", "图片path=" + path + " ------不用下载");
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageLoaded.imageLoaded(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        imageLoaded.onPreImageLoad();
    }


}
