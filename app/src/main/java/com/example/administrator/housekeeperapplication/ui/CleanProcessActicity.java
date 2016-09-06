package com.example.administrator.housekeeperapplication.ui;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.MyAppProcessAdapter;
import com.example.administrator.housekeeperapplication.entity.RunningAppInfo;
import com.example.administrator.housekeeperapplication.process.AndroidAppProcess;
import com.example.administrator.housekeeperapplication.process.ProcessManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CleanProcessActicity extends BaseActivity implements View.OnClickListener {

    private ActivityManager activityManager;
    private MyAppProcessAdapter appAdapter;
    private List<RunningAppInfo> runningAppInfos;
    private ProgressBar pb;
    private ProgressBar pb_memory;
    private TextView tv_scale_memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_process_acticity);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {

        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        TextView tv_brand = (TextView) findViewById(R.id.tv_brand);
        TextView tv_phone_type = (TextView) findViewById(R.id.tv_phone_type);
        tv_scale_memory = (TextView) findViewById(R.id.tv_scale_memory);
        Button btn_show_process = (Button) findViewById(R.id.btn_show_process);
        ListView listView = (ListView) findViewById(R.id.lv_process);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb_memory = (ProgressBar) findViewById(R.id.pb_phone_memory_use_rate);

        btn_show_process.setOnClickListener(this);
        findViewById(R.id.btn_clean_process).setOnClickListener(this);
        ((CheckBox) findViewById(R.id.cb_process_checkbox)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       /* appAdapter.setAllItemChecked(isChecked);*/
                        List<RunningAppInfo> infos = appAdapter.getData();
                        for (RunningAppInfo info : infos) {
                            info.setSelect(isChecked);
                        }
                        appAdapter.notifyDataSetChanged();
                    }
                }
        );
        String brand = Build.BRAND;//品牌
        String type = Build.MODEL + getPhoneSystemVersion();//手机型号


        int sdkInt = Build.VERSION.SDK_INT;
        List<RunningAppInfo> runningAppInfos;
        if (sdkInt >= 21) {
            runningAppInfos = getRunningAppInfo21();
        } else {
            runningAppInfos = getRunningAppInfo();
        }

        //runningAppInfos = getRunningAppInfo();
        appAdapter = new MyAppProcessAdapter(runningAppInfos, this);
        listView.setAdapter(appAdapter);
        //绑定数据
        tv_brand.setText(brand);
        tv_phone_type.setText(type);
        setMemoryInfo();

    }
        private void setMemoryInfo(){
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long totalMem = memoryInfo.totalMem;
        long availMem = memoryInfo.availMem;
        String strTotalMem = Formatter.formatFileSize(this, totalMem);
        String strUseMem = Formatter.formatFileSize(this, totalMem-availMem);
        tv_scale_memory.setText(strUseMem+ "/"+strTotalMem);
        pb.setMax(((int) totalMem)/1024/1024);
        pb.setProgress((int) ((totalMem-availMem)/1024/1024));
    }


    public String getPhoneSystemVersion() {
        return /*Build.VERSION.CODENAME + */Build.VERSION.RELEASE;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clean_process:
                List<RunningAppInfo> killedProcess = new ArrayList<>();
                if (runningAppInfos != null) {
                    for (int i = 0; i < runningAppInfos.size(); i++) {
                        RunningAppInfo runningAppInfo = runningAppInfos.get(i);
                        boolean select = runningAppInfo.isSelect();
                        if (select) {
                            activityManager.killBackgroundProcesses(runningAppInfo.getPackageName());
                            killedProcess.add(runningAppInfo);
                        }
                    }
                }
                runningAppInfos.removeAll(killedProcess);
                appAdapter.notifyDataSetChanged();
                setMemoryInfo();
                break;
            case R.id.btn_show_process:
                Button b = (Button) v;
                String text = (String) b.getText();
                List<RunningAppInfo> process = new ArrayList<>();
                boolean isShowSysApp = text.equals("用户进程");
                if (isShowSysApp) {
                    for (RunningAppInfo runningAppInfo : runningAppInfos) {
                        boolean isSysApp = runningAppInfo.isSysApp();
                        if (isSysApp) {
                            process.add(runningAppInfo);
                        }
                    }
                } else if (!isShowSysApp) {
                    for (RunningAppInfo runningAppInfo : runningAppInfos) {
                        boolean isSysApp = runningAppInfo.isSysApp();
                        if (!isSysApp) {
                            process.add(runningAppInfo);
                        }
                    }
                }
                b.setText(isShowSysApp ? "系统进程" : "用户进程");
                appAdapter.setData(process);
                appAdapter.notifyDataSetChanged();
                break;
        }
    }
    public List<RunningAppInfo> getRunningAppInfo21() {

        runningAppInfos = new ArrayList<>();
        List<AndroidAppProcess> processInfos = ProcessManager.getRunningAppProcesses();

        Iterator<AndroidAppProcess> iterator = processInfos.iterator();
        Toast.makeText(CleanProcessActicity.this, "" + processInfos.size(), Toast.LENGTH_SHORT).show();
        while (iterator.hasNext()) {
            AndroidAppProcess appProcess = iterator.next();
            int pid = appProcess.pid;//进程唯一标识
            String processName = appProcess.getPackageName();//包名，进程名
//            int importance = appProcess.ge;//获取进程的类别（前台，可见，后台，等等）

            //获取进程所占内存的大小
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{pid});
            Debug.MemoryInfo memoryInfo = processMemoryInfo[0];
            long totalPrivateDirty = memoryInfo.getTotalPrivateDirty() * 1024;//dirty 是 文件缩写 返回值的单位kb 转成b *1024
            //把占用内容大小的值，转化成M
            String memorySize = Formatter.formatShortFileSize(this, totalPrivateDirty);

            //通过包名获取该包的应用信息   获取单个包的信息
            try {
                PackageManager pm = getPackageManager();
                PackageInfo packageInfo = pm.
                        getPackageInfo(processName, PackageManager.GET_UNINSTALLED_PACKAGES);
                //得到应用程序的图标，即是手机桌面看到的图标
                Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
                //获取应用程序的标签名，即是手机桌面看到的应用名字
                String label = (String) packageInfo.applicationInfo.loadLabel(pm);
                //判断该进程是用户的还是系统的
                boolean isSysApp = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;

                RunningAppInfo runningAppInfo = new RunningAppInfo(label,processName, memorySize, drawable, isSysApp, 0);
                //将该对象保存到集合中
                runningAppInfos.add(runningAppInfo);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return runningAppInfos;
    }
    //解决版本低于21的
    public List<RunningAppInfo> getRunningAppInfo() {

        runningAppInfos = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

        Iterator<ActivityManager.RunningAppProcessInfo> iterator = processInfos.iterator();
        Toast.makeText(CleanProcessActicity.this, "" + processInfos.size(), Toast.LENGTH_SHORT).show();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo appProcess = iterator.next();
            int pid = appProcess.pid;//进程唯一标识
            String processName = appProcess.processName;//包名，进程名
//            int importance = appProcess.ge;//获取进程的类别（前台，可见，后台，等等）

            //获取进程所占内存的大小
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{pid});
            Debug.MemoryInfo memoryInfo = processMemoryInfo[0];
            long totalPrivateDirty = memoryInfo.getTotalPrivateDirty() * 1024;//dirty 是 文件缩写 返回值的单位kb 转成b *1024
            //把占用内容大小的值，转化成M
            String memorySize = Formatter.formatShortFileSize(this, totalPrivateDirty);

            //通过包名获取该包的应用信息   获取单个包的信息
            try {
                PackageManager pm = getPackageManager();
                PackageInfo packageInfo = pm.
                        getPackageInfo(processName, PackageManager.GET_UNINSTALLED_PACKAGES);
                //得到应用程序的图标，即是手机桌面看到的图标
                Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
                //获取应用程序的标签名，即是手机桌面看到的应用名字
                String label = (String) packageInfo.applicationInfo.loadLabel(pm);
                //判断该进程是用户的还是系统的
                boolean isSysApp = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;

                RunningAppInfo runningAppInfo = new RunningAppInfo(label,processName, memorySize, drawable, isSysApp, 0);
                //将该对象保存到集合中
                runningAppInfos.add(runningAppInfo);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return runningAppInfos;
    }


}
