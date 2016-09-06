package com.example.administrator.housekeeperapplication.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.ShowSoftAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShowSoftActivity extends BaseActivity {
    public final int OK = 1;
    private  Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == OK){
                if (msg.obj instanceof List) {
                    List<PackageInfo> data = (List<PackageInfo>) msg.obj;
                    dialog.dismiss();
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };
    private ShowSoftAdapter adapter;
    private ProgressDialog dialog;
    private ArrayList<String> myPackageName;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_soft);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        registerReceiver(receiver,filter);
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_container);
//        layout.setVisibility(View.INVISIBLE);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("提示");
        dialog.setMessage("请耐心等待,数据加载中...");
        dialog.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        dialog.show();

        ListView listView = (ListView) findViewById(R.id.lv_soft);

        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",-1);


        asyncLoadDate(flag);
        List<PackageInfo> data = new ArrayList<>();
        adapter = new ShowSoftAdapter(data,this);
        listView.setAdapter(adapter);

        CheckBox checkAll = (CheckBox) findViewById(R.id.checkbox);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAllItemChecked(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.btn_uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPackageName = getMyPackageName(adapter.getIschecks());
                for (String packageName : myPackageName) {
                    Intent intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(Uri.parse("package:"+packageName));
                    startActivity(intent);
                }
            }
        });
    }

    private void asyncLoadDate(final int flag) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<PackageInfo> data = selectInstalledPackage(flag);
                Message m = new Message();
                m.what = OK;
                m.obj = data;
                h.sendMessage(m);
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private ArrayList<String> getMyPackageName(HashMap<String, Boolean> ischecks) {
        ArrayList<String> packageNames = new ArrayList<>();
        Set<Map.Entry<String, Boolean>> entries = ischecks.entrySet();
        Iterator<Map.Entry<String, Boolean>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Boolean> next = iterator.next();
            String key = next.getKey();
            Boolean value = next.getValue();
            if (value){
                packageNames.add(key);
            }
        }
        return packageNames;
    }

    private List<PackageInfo> selectInstalledPackage(int flag) {
        PackageManager pm = getPackageManager();
        List<PackageInfo> allData = pm.getInstalledPackages(
                PackageManager.GET_UNINSTALLED_PACKAGES);


        if (flag == SoftMgrActivity.ALL_SOFT){
            return allData;
        }
        List<PackageInfo> data = new ArrayList<>();
        Iterator<PackageInfo> iterator = allData.iterator();
        while (iterator.hasNext()){
            PackageInfo packageInfo = iterator.next();
            int flags = packageInfo.applicationInfo.flags;
            switch (flag){
                case SoftMgrActivity.SYSTEM_SOFT:
                    if ((flags & ApplicationInfo.FLAG_SYSTEM) == 1){
                        data.add(packageInfo);
                    }
                    break;
                case SoftMgrActivity.USER_SOFT:
                    if ((flags & ApplicationInfo.FLAG_SYSTEM) != 1){
                        data.add(packageInfo);
                    }
                    break;
            }
        }
        return data;
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(ShowSoftActivity.this,"receiver",Toast.LENGTH_LONG).show();
            for (String s : myPackageName) {
                adapter.getIschecks().remove(s);
            }
            adapter.notifyDataSetChanged();
        }
    }


   /* public void delete (ListView l, View v, int position){
        Intent intent = new Intent(Intent.ACTION_DELETE);
        PackageInfo packageInfo = (PackageInfo) l.getItemAtPosition(position);
        intent.setData(Uri.parse("package"+packageInfo));
        startActivity(intent);
    }*/
   /* @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(Intent.ACTION_DELETE);
        PackageInfo packageInfo = (PackageInfo) l.getItemAtPosition(position);
        intent.setData(Uri.parse("package"+packageInfo));
        startActivity(intent);
    }*/
}
