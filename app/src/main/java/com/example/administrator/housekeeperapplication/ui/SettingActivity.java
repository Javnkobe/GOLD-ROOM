package com.example.administrator.housekeeperapplication.ui;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.housekeeperapplication.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        findViewById(R.id.tv_clear).setOnClickListener(this);
        findViewById(R.id.tv_help).setOnClickListener(this);
        findViewById(R.id.tv_study).setOnClickListener(this);
        findViewById(R.id.tv_share).setOnClickListener(this);
        findViewById(R.id.tv_update).setOnClickListener(this);
        findViewById(R.id.tv_about).setOnClickListener(this);
        showToast("未完，待续");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_clear:
                showToast("待清理");
                break;
            case R.id.tv_help:
                showToast("待帮助");
                break;
            case R.id.tv_study:
                showToast("待修改意见");
                break;
            case R.id.tv_share:
                showToast("待分享");
                break;
            case R.id.tv_update:
                showToast("待更新");
                break;
            case R.id.tv_about:
                showToast("待管理版本");
                break;
        }
    }
}
