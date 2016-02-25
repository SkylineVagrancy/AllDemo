package com.pzh.config;

/**
 * Created by junpeng.zhou on 2015/10/9.
 */
public class AppConfig {
    public static int Src_width = 0;
    public static int Src_height = 0;
    public static int density = 0;
    public static String dataUrl = "http://www.imooc.com/api/teacher?type=4&num=";
    public static int request_ok=0x6666;
    public static int request_fail=0x1111;
    public static String apikey="4073eb1306c0d4119c78876f6cfb1258";
    public static String BmobKey="2fb69407379144cbe6712b0fb8c02b75";

    public static String getDataUrl(int index) {
        String tem = dataUrl + index * 10;
        return tem;
    }


}
