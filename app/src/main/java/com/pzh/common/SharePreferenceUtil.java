package com.pzh.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by pzh on 15/11/17.
 */
public class SharePreferenceUtil {
    public static void addAllDemoSharePreference(Context context, String name, String value) {
        SharedPreferences preferences = context.getSharedPreferences("AllDemo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name,value);
        editor.commit();
    }
    public static String getVe(Context context,String name){
        SharedPreferences preferences=context.getSharedPreferences("AllDemo",Context.MODE_APPEND);
        String value=preferences.getString(name,null);
        return  value;
    }

    public static void addSharepreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("express", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static HashMap<String, String> getValue(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        HashMap<String, String> shareData;
        shareData = (HashMap<String, String>) sharedPreferences.getAll();
        return shareData;
    }
}
