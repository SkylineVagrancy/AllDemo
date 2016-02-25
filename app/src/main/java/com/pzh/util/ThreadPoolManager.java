package com.pzh.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pzh on 15/11/23.
 */
public class ThreadPoolManager {
    private ExecutorService services;
    private ThreadPoolManager(){
        int num=Runtime.getRuntime().availableProcessors();
        services= Executors.newFixedThreadPool(num);
    }
    private static final ThreadPoolManager manager=new ThreadPoolManager();
    public static ThreadPoolManager getInstance(){
        return manager;
    }
    public void addTask(Runnable runnable){
        services.execute(runnable);
    }
}
