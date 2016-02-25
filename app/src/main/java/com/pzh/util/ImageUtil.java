package com.pzh.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.pzh.common.AppApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by pzh on 15/11/23.
 */
public class ImageUtil {
    private static final String IMG_PATH = "/sdcad/alltest/images/";
    private Context context;

    public ImageUtil(Context context) {
        this.context = context;
    }

    public static void saveImageJpeg(String imagePath, Bitmap bitmap) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] b = bos.toByteArray();
        saveImage(imagePath, b);
        bos.flush();
        bos.close();

    }


    /**
     * @param imagePath
     * @param buffer
     * @throws IOException
     */
    public static void saveImage(String imagePath, byte[] buffer) throws IOException {
        File f = new File(imagePath);
        if (f.exists()) {
            return;
        } else {
            File parentFile = f.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagePath);
            fos.write(buffer);
            fos.flush();
            fos.close();
        }
    }

    /**
     * @param imagePath tupia
     * @return
     */
    public static Bitmap getImageFromLocal(String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            file.setLastModified(System.currentTimeMillis());
            return bitmap;
        }
        return null;
    }

    Bitmap mBitmap = null;
    String url = null;

    public Bitmap loadImage(final String imageName, final String imageUrl, final boolean isbuzy, final ImageCallback callback) {
        Bitmap bitmap = null;
        bitmap = AppApplication.mLruCache.get(imageUrl);
        if (bitmap != null) {
            callback.loadImage(bitmap, imageUrl);
            Log.d(AppApplication.IMAGE, "从本地图片中加载成功！");
            return bitmap;
        } else {
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.obj != null) {
                        synchronized (AppApplication.mLruCache) {
                            mBitmap= (Bitmap) msg.obj;

                            Map<String, Bitmap> bitmapMap = (Map<String, Bitmap>) msg.obj;
                            for (String str : bitmapMap.keySet()) {
                                mBitmap = bitmapMap.get(str);
                                url = str;
                                AppApplication.mLruCache.put(str, mBitmap);
                            }
                            if (android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                try {
                                    String imageName = url.substring(url.indexOf("/") + 1, url.length());
                                    if (!imageName.endsWith(".jpg") && !imageName.endsWith(".png")) {
                                        imageName += ".png";
                                    }
                                    String mImagePath = ImageUtil.IMG_PATH + imageName;
                                    saveBitmapToData(imageName, mBitmap, context);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            callback.loadImage(mBitmap, url);
                        }
                    }
                }
            };
            GetImageRunnable run=new GetImageRunnable(imageUrl,handler,callback);
            ThreadPoolManager.getInstance().addTask(run);


        }
        return bitmap;
    }

    class GetImageRunnable implements Runnable {
        private String url;
        private Handler mHandler;
        private ImageCallback mCallback;

        public GetImageRunnable(String imageUrl, Handler handler, ImageCallback callback) {
            this.url = imageUrl;
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override
        public void run() {
            try {
                synchronized (AppApplication.mLruCache) {
                    mCallback.onStart(url);
                }
                Bitmap bitmap = null;
                if (url != null && !"".equals(url)) {
                    byte[] b = getUrlbytes(url);
                    bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                }
                Message msg = mHandler.obtainMessage();
                Map<String ,Bitmap> bitmapMap=new HashMap<String,Bitmap>();
                bitmapMap.put(url,bitmap);
                msg.obj = bitmapMap;
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                synchronized (AppApplication.mLruCache) {
                    mCallback.onFiled(url);
                }
            }
        }
    }


    public static byte[] getUrlbytes(String urlpath) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        URL url = new URL(urlpath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10 * 1000);
        int responseCode = conn.getResponseCode();
        Log.d(AppApplication.IMAGE, "responseCode:" + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            in = conn.getInputStream();
        }
        if (in == null) {
            return null;
        } else {
            byte[] buffer = new byte[128];
            int len = 1;
            while ((len = in.read(buffer)) != -1) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            in.close();
            return outstream.toByteArray();
        }
    }

    public void saveBitmapToData(String name, Bitmap bitmap, Context context) {
        try {
            FileOutputStream localFileOutputStream = context.openFileOutput(name, 0);
            Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG;
            bitmap.compress(localCompressFormat, 100, localFileOutputStream);
            localFileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ImageCallback {
        void onStart(String imageYrl);

        void loadImage(Bitmap bitmap, String imageUrl);

        void onFiled(String url);
    }

}
