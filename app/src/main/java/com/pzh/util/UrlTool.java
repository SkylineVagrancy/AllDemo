package com.pzh.util;

/**
 * Created by junpeng.zhou on 2015/10/15.
 */
public class UrlTool {
    private static String orderUrl="http://a.apix.cn/apixlife/express/delivery?";
    public static String getOrderUrl(String id,String logistics){
        StringBuffer sbf=new StringBuffer(orderUrl);
        sbf.append("id=").append(id).append("&").append("logistics=").append(logistics);
        return sbf.toString();
    }



}
