package com.pzh.ui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Weather;
import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by pzh on 16/1/13.
 */
public class WeatherCityUI extends BaseUI implements APICallback {
    private ArrayList<HashMap<String, Object>> resultList;
    private ExpandableListView expandableListView;
    private LayoutInflater inflater;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_support_city);
        inflater = LayoutInflater.from(context);
        expandableListView = (ExpandableListView) findViewById(R.id.cityList);
        Weather api = (Weather) MobAPI.getAPI(Weather.NAME);
        api.getSupportedCities(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onSuccess(API api, int i, Map<String, Object> map) {
        resultList = (ArrayList<HashMap<String, Object>>) map.get("result");
        Log.d("TAG", "返回的城市数据----->" + map.toString());
//        final ArrayList<HashMap<String, ArrayList<String>>> provinces = new ArrayList<>();
        final ArrayList<ArrayList<String>> provinces=new ArrayList<>();
        final ArrayList<String> groups=new ArrayList<>();

        for (HashMap<String, Object> province : resultList) {
            ArrayList<String> cityTem = new ArrayList<>();
            ArrayList<HashMap<String, String>> cityList = (ArrayList<HashMap<String, String>>) province.get("city");
            for (HashMap<String, String> city : cityList) {
                cityTem.add(city.get("city"));
            }
            provinces.add(cityTem);
            groups.add(province.get("province").toString());
        }
        Log.d("TAG",provinces.size()+"");
        Log.d("TAG",provinces.get(0).size()+"");
        Log.d("TAG",provinces.toString());
        expandableListView.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount()
            {
                return provinces.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return provinces.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return groups.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return provinces.get(groupPosition).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                convertView = inflater.inflate(R.layout.view_weather_district, null);
                TextView tvName = (TextView) convertView.findViewById(R.id.tvDistrict);
                tvName.setText(groups.get(groupPosition) + "");
                return convertView;

            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                convertView = inflater.inflate(R.layout.view_weather_district, null);
                TextView tvName = (TextView) convertView.findViewById(R.id.tvDistrict);
                tvName.setText(provinces.get(groupPosition).get(childPosition));
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        });

    }

    @Override
    public void onError(API api, int i, Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, "有异常，请查看logcat", Toast.LENGTH_SHORT).show();
    }


}
