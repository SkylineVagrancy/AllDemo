package com.pzh.common;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.mob.mobapi.MobAPI;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class AppApplication extends Application {
    public static  String NEt="Net";
    public static String IMAGE="Image";
    public static String TEXT="Text";
    public static  LruCache<String,Bitmap> mLruCache;
    public  int maxMemonry;
    @Override
    public void onCreate() {
        super.onCreate();
        MobAPI.initSDK(getApplicationContext(),"ea14a6fbcea8");
        maxMemonry= (int) Runtime.getRuntime().maxMemory();
        DisplayImageOptions defaultOptions=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).
                defaultDisplayImageOptions(defaultOptions)
                .memoryCacheSize(8*1024*1024)
                .diskCacheSize(32*1024*1024)
                .diskCacheFileCount(100)
                .threadPoolSize(Thread.NORM_PRIORITY+2)
                .tasksProcessingOrder(QueueProcessingType.FIFO).build();
        ImageLoader.getInstance().init(configuration);
        mLruCache=new LruCache<String ,Bitmap>(maxMemonry/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                Log.d(IMAGE,"hard cache is full,push to soft cache");
            }
        };
    }
}
