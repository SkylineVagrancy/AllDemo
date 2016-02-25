package com.pzh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pzh.common.BaseUI;
import com.pzh.common.SharePreferenceUtil;
import com.pzh.pzhstudy.R;

/**
 * Created by pzh on 15/12/25.
 */
public class ImportBingo extends BaseUI {
    private EditText et_num;
    private EditText et_reward;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_bingo);
        et_num = (EditText) findViewById(R.id.et_num);
        et_reward = (EditText) findViewById(R.id.et_reward);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = et_reward.getText().toString();
                String value = et_num.getText().toString();
                if (key != null && value != null) {
                    SharePreferenceUtil.addAllDemoSharePreference(context,key,value );
                    Toast.makeText(context,"保存成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "奖项或者bingo卡不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
