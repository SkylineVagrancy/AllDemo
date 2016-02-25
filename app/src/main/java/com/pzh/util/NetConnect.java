package com.pzh.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.pzh.config.AppConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class NetConnect {
    public static void getNetData(final String path, final Handler mHandler, final int returnCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("apix-key", "b42f12ed525f4cad4f17e7b6f9f4f741");
                    Log.i("pzh", path);
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = conn.getInputStream();
                        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String tem = "";
                        while ((tem = bufferedReader.readLine()) != null) {
                            result += tem;
                        }
                        in.close();
                        conn.disconnect();
                        Message message = Message.obtain();
                        message.what = returnCode;
                        message.obj = result;
                        mHandler.sendMessage(message);
                    } else {
                        Message message = Message.obtain();
                        message.what = AppConfig.request_fail;
                        message.obj = conn.getResponseMessage();
                        mHandler.sendMessage(message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    public static void getNetData(String path, Handler mHandler, HashMap<String, String> params,int returnCode) {
        String httpUrl=getResultUrl(path,params);
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", AppConfig.apikey);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            Message msg = Message.obtain();
            msg.what = returnCode;
            msg.obj = result;
            mHandler.sendMessage(msg);
            Log.d("Net", result);
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = Message.obtain();
            msg.what = AppConfig.request_fail;
            msg.obj = e.getMessage();
            mHandler.sendMessage(msg);
        }


    }

    public static String getResultUrl(String path, HashMap<String, String> params) {
        String result = "";
        for (String tem : params.keySet()) {
            String value = params.get(tem);
            result = "&" + result + tem + "=" + value;
        }
        result = result.substring(1);
        result = path + result;
        return result;
    }


    public static void getWeather(final String cityName, final Handler mHandler, final int returnCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String weather = null;

                BufferedReader reader = null;
                String result = null;
                StringBuffer sbf = new StringBuffer();
//                    String  httpUrl = "http://apis.baidu.com/heweather/weather/free?city=麻城&apikey:5831108d6f8d1d8de3010f325ab08b24";
                String httpUrl = "http://apis.baidu.com/heweather/weather/free?city=" + cityName + "&apikey:5831108d6f8d1d8de3010f325ab08b24";
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("apikey", AppConfig.apikey);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    result = sbf.toString();
                    Message msg = Message.obtain();
                    msg.what = returnCode;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                    Log.d("Net", result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = AppConfig.request_fail;
                    msg.obj = e.getMessage();
                    mHandler.sendMessage(msg);
                }

            }
        }).start();


    }
}
