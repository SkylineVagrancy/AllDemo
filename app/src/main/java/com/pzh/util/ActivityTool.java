package com.pzh.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by junpeng.zhou on 2015/10/15.
 */
public class ActivityTool {
    public static void forwardActivity(Context context, String className) {
        try {
            Class c = Class.forName(className);
            Intent intent = new Intent(context, c);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void forwardActivity(Context context, String className, Intent intent) {
        try {
            Class c = Class.forName(className);
            intent.setClass(context, c);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void forwardActivity(Context context, Class forwardClass) {
            
            Intent intent = new Intent(context, forwardClass);
            context.startActivity(intent);

    }
}
