package com.example.administrator.housekeeperapplication.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.PhonemgrAdapter;;
import com.example.administrator.housekeeperapplication.entity.PhoneInfo;
import com.example.administrator.housekeeperapplication.util.CommonUtil;
import com.example.administrator.housekeeperapplication.util.MemoryUtil;

import java.util.ArrayList;
import java.util.List;

public class PhonemgrActivity extends BaseActivity {

    private BatteryReceiver broadcastReceiver;
    private LinearLayout layout;
    private int level, scale, status, health, temperature;
    private ProgressBar progressBar;
    private int point;
    private TextView textViewPoint;
    private ListView listView;
    private PhonemgrAdapter adapter;
    private ArrayList<PhoneInfo> phoneInfos = new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonemgr);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        layout = (LinearLayout) findViewById(R.id.ll_layout_battery);
        progressBar = (ProgressBar) findViewById(R.id.pb_battery);
        textViewPoint = (TextView) findViewById(R.id.tv_battery);
        listView = (ListView) findViewById(R.id.listviewLoad);

        //动态注册（打开广播）
        //创建一个意图过滤器
        broadcastReceiver = new BatteryReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);//当电量变化时广播
        registerReceiver(broadcastReceiver, filter);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("提示");
        dialog.setMessage("请耐心等待,数据加载中...");
        dialog.setIcon(getResources().getDrawable(R.mipmap.my_launcher));
        dialog.show();

        asyncLoadDate();
        //获取数据源
        getData();
        adapter = new PhonemgrAdapter(phoneInfos,this);

    }
    private void asyncLoadDate() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       listView.setAdapter(adapter);
                       adapter.notifyDataSetChanged();
                   }
               });
            }


        }.start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
    public void getData() {
        new Thread() {
            @Override
            public void run() {
                setPhoneInfo(R.mipmap.setting_info_icon_version, "设备名称:" + CommonUtil.getBrand(), "系统版本:" + CommonUtil.getVersion());
                setPhoneInfo(R.mipmap.setting_info_icon_space, "全部运行内存:" + MemoryUtil.getTotaMem()+"M", "剩余运行内存:" + MemoryUtil.getFreeMem(PhonemgrActivity.this)+"M");
                setPhoneInfo(R.mipmap.setting_info_icon_cpu, "CPU名称:" + CommonUtil.getCPUName(), "CPU数量:" + CommonUtil.getPhoneCPUNumber());
                setPhoneInfo(R.mipmap.setting_info_icon_camera, "手机分辨率:" + CommonUtil.getWindowPixel(PhonemgrActivity.this), "相机分辩率:" +"存在未解决问题"/* CommonUtil.getCameraPixel()*/);
                setPhoneInfo(R.mipmap.setting_info_icon_root, "基带版本:" + CommonUtil.getRadio(), "是否Root:" + (CommonUtil.isRoot() ? "是" : "不是"));
                //将数据源传递到adapter
                adapter.setData(phoneInfos);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //在主线程中更新界面
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }
    private void setPhoneInfo(int icon, String info1, String info2) {
        PhoneInfo info = new PhoneInfo(icon, info1, info2);
        phoneInfos.add(info);
    }
    class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取电量
            level = intent.getIntExtra("level", -1);/*level 电池电量*/
            scale = intent.getIntExtra("scale", -1);/*scale  总电量 */
            //获取电池温度
            temperature = intent.getIntExtra("temperature", 0);
            status = intent.getIntExtra("status", -1);/*status  当前电池的状态*/
            health = intent.getIntExtra("health", -1);
            //计算电量百分比
            point = (int) Math.round(level * 100 / scale );
            progressBar.setProgress(point);
            //设置文字
            textViewPoint.setText(point + "%");
        }
    }
}


