package com.example.administrator.housekeeperapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.biz.MemoryManager;

public class SoftMgrActivity extends BaseActivity implements View.OnClickListener {

    public static final int ALL_SOFT = 0;
    public static final int USER_SOFT = 1;
    public static final int SYSTEM_SOFT = 2;
    //private PiechartView pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr);
//        pv = new PiechartView(this);
//        pv.setPiechartProportionWithAnim(0.5f,0.3f);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        findViewById(R.id.btn_all_soft).setOnClickListener(this);
        findViewById(R.id.btn_uti_soft).setOnClickListener(this);
        findViewById(R.id.btn_sys_soft).setOnClickListener(this);
        ProgressBar pb_phone_extral_space = (ProgressBar) findViewById(R.id.pb_phone_extral_space);
        ProgressBar pb_phone_internal_space = (ProgressBar) findViewById(R.id.pb_phone_internal_space);

        long outSDCardSize = MemoryManager.getPhoneOutSDCardSize(this) / 1024 / 1024;
        pb_phone_extral_space.setMax((int) outSDCardSize);
        long outSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(this) / 1024 / 1024;
        pb_phone_extral_space.setProgress((int) (outSDCardSize-outSDCardFreeSize));
        TextView tv_extral = (TextView) findViewById(R.id.tv_extral);
        tv_extral.setText((int) (outSDCardSize-outSDCardFreeSize)+"/"+outSDCardSize+"MB");

        long selfSDCardSize = MemoryManager.getPhoneSelfSDCardSize() / 1024 / 1024;
        pb_phone_internal_space.setMax((int) selfSDCardSize);
        long selfSDCardFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize() / 1024 / 1024;
        pb_phone_internal_space.setProgress((int) (selfSDCardSize-selfSDCardFreeSize));
        TextView tv_internal = (TextView) findViewById(R.id.tv_internal);
        tv_internal.setText((int) (selfSDCardSize-selfSDCardFreeSize)+"/"+selfSDCardSize+"M");


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ShowSoftActivity.class);
        switch (v.getId()){
            case R.id.btn_all_soft:
                intent.putExtra("flag",ALL_SOFT);
                break;
            case R.id.btn_uti_soft:
                intent.putExtra("flag",USER_SOFT);
                break;
            case R.id.btn_sys_soft:
                intent.putExtra("flag",SYSTEM_SOFT);
                break;
        }
        startActivity(intent);
    }
}
