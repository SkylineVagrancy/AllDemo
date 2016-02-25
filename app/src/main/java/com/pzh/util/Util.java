package com.pzh.util;

import android.content.Context;


import com.pzh.config.AppConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class Util {
    public static int dp2px(int dp) {
        return dp * AppConfig.density;
    }

    public static int px2dp(int px) {

        return px / AppConfig.density;
    }

    public static String getDataFromAssets(Context context, String fileName) {
        String result = "";
        try {
            InputStream in= context.getResources().getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String tem = "";
            while ((tem = reader.readLine()) != null) {
                result += tem;
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
