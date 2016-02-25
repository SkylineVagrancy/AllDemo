package com.pzh.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pzh on 16/1/13.
 */
public class WeatherServices extends Service {
    private WeatherServiceBinder binder = new WeatherServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class WeatherServiceBinder extends Binder {
        public WeatherServices getService() {
            return WeatherServices.this;
        }


    }

}
