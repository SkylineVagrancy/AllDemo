package com.pzh.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.pzh.bean.LuckyBean;
import com.pzh.bean.TitleBean;
import com.pzh.common.BaseUI;
import com.pzh.common.CommonPop;
import com.pzh.common.CommonTitle;
import com.pzh.common.SharePreferenceUtil;
import com.pzh.pzhstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by pzh on 15/12/25.
 */
public class BingoUI extends BaseUI {
    private EditText et_num;
    private TextView tv_luckyNum;
    private Button btn_add;
    private GridView bingoCard;
    private CommonTitle commonTitle;
    private Dialog rewardDia;
    public ArrayList<LuckyBean> luckyBeanList = new ArrayList<>();
    public ArrayList<String> luckyList = new ArrayList<>();
    public HashMap<String, String> datas = new HashMap<>();
    public String[] rewardItem;
    private String[] rewardNum;
    private Dialog menuDialog;
    private LuckyAdapter luckyAdapter;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bingo);
        initView();
        init();
    }

    private void init() {
        luckyAdapter=new LuckyAdapter();
        bingoCard.setAdapter(luckyAdapter);
        rewardDia = new Dialog(context);
        datas = SharePreferenceUtil.getValue(context, "AllDemo");
        for (String key : datas.keySet()) {
            if (key.contains("奖")) {
                luckyList.add(key);
            }
        }
        rewardItem = new String[luckyList.size()];
        for (int i = 0; i < luckyList.size(); i++) {
            rewardItem[i] = luckyList.get(i);
        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("奖项列表");
//        builder.setItems(rewardItem, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //TODO notifidatachange 通知界面改变
//                getLuckyBean(which);
//                if (menuDialog != null & menuDialog.isShowing()) {
//                    menuDialog.dismiss();
//                }
//            }
//        });
        menuDialog = CommonPop.showLsitPop(context, "奖项", rewardItem, new CommonPop.OnListItemClick() {
            @Override
            public void onItenClick(int position) {
                getLuckyBean(position);
                if (menuDialog != null & menuDialog.isShowing()) {
                    menuDialog.dismiss();
                }
            }
        });

        TitleBean titleBean = new TitleBean("幸运兑奖", R.color.title_background, true) {
            @Override
            public void onBackClick() {
                finish();
            }

            @Override
            public void onMenuClick() {
                menuDialog.show();
            }
        };
        commonTitle.initTitle(titleBean);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luckyNum = et_num.getText().toString();
                if ("".equals(luckyNum)) {
                    Toast.makeText(context, "请输入幸运号码！", Toast.LENGTH_SHORT).show();
                } else {
                    changeLuckyCard(luckyNum);
                    et_num.setText("");
                }
            }
        });
    }

    private void changeLuckyCard(String luckyNum) {
        String tem=tv_luckyNum.getText().toString();
        if(tem.equals("")){
            tem+=luckyNum;
        }else{
            tem=tem+","+luckyNum;
        }
        tv_luckyNum.setText(tem);
        for (LuckyBean bean:luckyBeanList) {
            if(bean.getLuckyNum().equals(luckyNum)){
                bean.setIsLucky(true);
                luckyAdapter.notifyDataSetChanged();
                Toast.makeText(context,"真好，离中奖又进了一步！",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    /**
     * 初始化界面view
     */
    private void initView() {
        inflater=LayoutInflater.from(context);
        et_num = (EditText) findViewById(R.id.et_num);
        tv_luckyNum = (TextView) findViewById(R.id.lucky_num);
        btn_add = (Button) findViewById(R.id.btn_add);
        bingoCard = (GridView) findViewById(R.id.bingocard);
        commonTitle = (CommonTitle) findViewById(R.id.commonTitle);



    }

    public void getLuckyBean(int position) {
        tv_luckyNum.setText(null);
        et_num.setText(null);
        String key=luckyList.get(position);
        String rewardData=SharePreferenceUtil.getVe(context, key);
        if(rewardData == null){
            return;
        }
        rewardNum=rewardData.split(",");
        for (int i = 0; i <rewardNum.length ; i++) {
            LuckyBean bean=new LuckyBean();
            bean.setLuckyNum(rewardNum[i]);
            bean.setIsLucky(false);
            luckyBeanList.add(bean);

        }
        luckyAdapter.notifyDataSetChanged();
    }

    public class LuckyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return luckyBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return luckyBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.bingo_card_item,null);
            TextView tv_nu= (TextView) view.findViewById(R.id.tv_item);
            tv_nu.setText(luckyBeanList.get(position).getLuckyNum());
            if(luckyBeanList.get(position).getLuckyNum().equals("bingo")){
                luckyBeanList.get(position).setIsLucky(true);
            }
            if(luckyBeanList.get(position).isLucky()) {
                tv_nu.setBackgroundResource(R.color.item_selected);
            }else{
                tv_nu.setBackgroundResource(R.color.title_color);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    luckyBeanList.get(position).setIsLucky(!luckyBeanList.get(position).isLucky());
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
