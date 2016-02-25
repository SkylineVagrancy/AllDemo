package com.pzh.ui;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Weather;
import com.pzh.common.BaseUI;
import com.pzh.services.WeatherServices;

import java.util.Map;

/**
 * Created by pzh on 16/1/13.
 */
public class WeatherUI extends BaseUI implements APICallback{
    ServiceConnection conn;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Weather api= (Weather) MobAPI.getAPI(Weather.NAME);
        api.getSupportedCities(this);

    }

    private void bindService(){
        Intent intent=new Intent(context, WeatherServices.class);
        startService(intent);
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onSuccess(API api, int i, Map<String, Object> map) {

    }

    @Override
    public void onError(API api, int i, Throwable throwable) {

    }
}
