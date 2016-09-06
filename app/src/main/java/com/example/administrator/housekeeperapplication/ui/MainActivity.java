package com.example.administrator.housekeeperapplication.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.view.ClearArcView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ClearArcView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initview();
        c.setAngleWithAnim(90);
    }

    @Override
    public void initview() {
        assert toolbar != null;
        toolbar.setNavigationIcon(R.mipmap.my_launcher);

        findViewById(R.id.btn_rocket).setOnClickListener(this);
        findViewById(R.id.btn_sofemgr).setOnClickListener(this);
        findViewById(R.id.btn_phonemgr).setOnClickListener(this);
        findViewById(R.id.btn_telmgr).setOnClickListener(this);
        findViewById(R.id.btn_filemgr).setOnClickListener(this);
        findViewById(R.id.btn_sdcclean).setOnClickListener(this);
        c = (ClearArcView) findViewById(R.id.homeclear_arc);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
            case R.id.share:
                showToast("share");
                break;
            case R.id.save:
                showToast("save");
                break;
            case R.id.delete:
                showToast("delete");
                break;
            case R.id.setting:
               /* Toast toast = new Toast(this);
                toast.setGravity(Gravity.TOP|Gravity.LEFT,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setText("setting");
                toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();*/
                showToast("setting");
                mystartactivity(SettingActivity.class);
                break;
            case R.id.homeclear_arc:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_rocket:
                mystartactivity(CleanProcessActicity.class);
                break;
            case R.id.btn_sofemgr:
                mystartactivity(SoftMgrActivity.class);
                break;
            case R.id.btn_phonemgr:
                mystartactivity(PhonemgrActivity.class);
                break;
            case R.id.btn_telmgr:
                mystartactivity(ShowTelTypeActivity.class);
                break;
            case R.id.btn_filemgr:
                mystartactivity(FileMgrActivity.class);
                break;
            case R.id.btn_sdcclean:
                break;
        }
    }
}

