package com.pzh.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.ArrayList;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class ImageLoader {
    private static ImageLoader mInstance;
    private LruCache<String, Bitmap> mLruCache;
    private ArrayList<DownLoadTask> taskQUeue;


    private ImageLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory);
        taskQUeue=new ArrayList<DownLoadTask>();
    }

    public static ImageLoader getImageLoaderInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void loadedImage(int width, int height, String path, ImageLoaded imageLoaded) {
        DownLoadTask task=new DownLoadTask(mLruCache,imageLoaded);
        taskQUeue.add(task);
        task.execute(path);
    }

    public interface ImageLoaded {
        void imageLoaded(Bitmap bitmap);
         void onPreImageLoad();
    }
    public void stopAllTask(){
        for (int i = 0; i < taskQUeue.size(); i++) {
            DownLoadTask task=taskQUeue.get(i);
            task.cancel(true);
        }
    }


}
