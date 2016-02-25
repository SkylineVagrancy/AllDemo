package com.pzh.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.pzh.common.BaseUI;
import com.pzh.pzhstudy.R;
import com.pzh.util.ActivityTool;

/**
 * Created by pzh on 15/12/25.
 */
public class SelectBingo extends BaseUI {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_bingo);
        findViewById(R.id.btn_duijiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTool.forwardActivity(context, BingoUI.class);
            }
        });
        findViewById(R.id.btn_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTool.forwardActivity(context,ImportBingo.class);
            }
        });
    }
}
