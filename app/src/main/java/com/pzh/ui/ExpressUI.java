package com.pzh.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.pzh.bean.ExpressBean;
import com.pzh.bean.ExpressInfo;
import com.pzh.common.BaseUI;
import com.pzh.common.SharePreferenceUtil;
import com.pzh.pzhstudy.R;
import com.pzh.util.NetConnect;
import com.pzh.util.UrlTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by junpeng.zhou on 2015/10/15.
 */
public class ExpressUI extends BaseUI {
    private int orderNum;

    private AutoCompleteTextView tv_order;
    private Spinner spinner;
    private ArrayList<ExpressInfo> expressData=new ArrayList<ExpressInfo>();
    private LayoutInflater inflater;
    private String orderName;
    private Button btn_check;
    private ExpressAdadpter expressAdapter;
    private ListView expressList;
    private ExpressBean bean;
    private TextView tv_back;
    private int selectPosition;
    private ProgressDialog dialog;
    private HashMap<String,String> data;
    private ArrayList<String> dataList=new ArrayList<>();
    private ArrayAdapter<String> adapter;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(dialog!= null && dialog.isShowing()){
                dialog.dismiss();
            }
            int returnCode=msg.what;
            if(returnCode == 1){
                String result = (String) msg.obj;
                System.out.println("pzh "+result);
                bean= JSON.parseObject(result,ExpressBean.class);
                if(bean.error_code == 0){
                    expressData=bean.data.result;
                    System.out.println("pzh 查询成功");
                    expressAdapter.notifyDataSetChanged();
                    SharePreferenceUtil.addSharepreference(context, tv_order.getText().toString(), selectPosition + "");
                    getShareList();
                    adapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,dataList);
                    tv_order.setAdapter(adapter);

                }else{
                    System.out.println("pzh 查询失败！");
                    Toast.makeText(ExpressUI.this, bean.message, Toast.LENGTH_LONG).show();
                    expressData.clear();
                    expressAdapter.notifyDataSetChanged();
                }
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.express);
        initView();
        getShareList();
        init();


    }

    private void init() {
        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,dataList);
        tv_order.setAdapter(adapter);
        tv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=data.get(dataList.get(position));
                int selectpos=Integer.parseInt(value);
                System.out.println("pzh selectposition:"+selectpos);
                spinner.setSelection(selectpos);
                System.out.println("select click");
            }
        });



        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        expressAdapter=new ExpressAdadpter();
        expressList.setAdapter(expressAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderName = parent.getItemAtPosition(position).toString().trim();
                selectPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                orderName = "";
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tem=tv_order.getText().toString();
                if(tem != null && orderName != null){
                    dialog.show();
                    NetConnect.getNetData(UrlTool.getOrderUrl(tem, orderName), mHandler, 1);
                }
            }
        });

    }

    private void initView() {
        inflater= LayoutInflater.from(context);
        dialog=new ProgressDialog(context);
        tv_order = (AutoCompleteTextView) findViewById(R.id.orderNum);
        data=SharePreferenceUtil.getValue(context,"express");
        spinner = (Spinner) findViewById(R.id.orderName);
        btn_check= (Button) findViewById(R.id.check);
        expressList= (ListView) findViewById(R.id.expressStatusList);
        tv_back= (TextView) findViewById(R.id.tv_back);
    }

    public void getShareList(){
        data=SharePreferenceUtil.getValue(context,"express");
        dataList.clear();
        Iterator item=data.entrySet().iterator();
        while(item.hasNext()){
            Map.Entry entry= (Map.Entry) item.next();
            String key= (String) entry.getKey();
            dataList.add(key);

        }

    }


    private class ExpressAdadpter extends BaseAdapter {

        @Override
        public int getCount() {
            return expressData.size();
        }

        @Override
        public Object getItem(int position) {
            return expressData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
           if(convertView==null){
               holder=new ViewHolder();
               convertView=inflater.inflate(R.layout.exprss_list_item,null);
                holder.tv_time= (TextView) convertView.findViewById(R.id.expressStatusTime);
               holder.tv_content= (TextView) convertView.findViewById(R.id.expressStatus);
               convertView.setTag(holder);
           }else{
               holder= (ViewHolder) convertView.getTag();
           }
            holder.tv_time.setText(expressData.get(position).time);
            holder.tv_content.setText(expressData.get(position).context);
            return convertView;
        }
    }


    public class ViewHolder{
        public TextView tv_time;
        public TextView tv_content;
    }

}
