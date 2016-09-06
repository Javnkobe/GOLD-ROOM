package com.example.administrator.housekeeperapplication.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.housekeeperapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShowSoftAdapter extends MyBaseAdapter<PackageInfo>{
    private final PackageManager packagemanager;
    private Context context;

    public HashMap<String, Boolean> getIschecks() {
        return ischecks;
    }

    private  HashMap<String,Boolean> ischecks = new HashMap<>();

    public ShowSoftAdapter(@Nullable List<PackageInfo> data, @Nullable Context context) {
        super(data, context);
        packagemanager = context.getPackageManager();
    }

    @Override
    public View MyGetView(final int position, View convertView, ViewGroup parent) {
        //if (convertView == null){
            convertView = inflater.inflate(R.layout.item_soft,null);
        //}
        PackageInfo packageinfo = getData().get(position);
        int versioncode = packageinfo.versionCode;//版本号
        final String packagename = packageinfo.packageName;//包名
        CharSequence lable = packageinfo.applicationInfo.loadLabel(packagemanager);
        Drawable drawble = packageinfo.applicationInfo.loadIcon(packagemanager);
        convertView.findViewById(R.id.iv_app_photo).setBackground(drawble);


        TextView app_name = (TextView) convertView.findViewById(R.id.tv_app_name);
        app_name.setText(lable);

        TextView app_code = (TextView) convertView.findViewById(R.id.tv_app_code);
        app_code.setText(versioncode+"");

        TextView app_package = (TextView) convertView.findViewById(R.id.tv_app_package);
        app_package.setText(packagename);

        final CheckBox box = (CheckBox) convertView.findViewById(R.id.cb_checkbox);
        Boolean ischeck = ischecks.get(packagename);
        box.setChecked(ischeck == null ? false:ischeck);
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //box.setChecked(isChecked);
                //Toast.makeText(context, ""+isChecked, Toast.LENGTH_SHORT).show();
                ischecks.put(packagename,isChecked);
            }
        });

        return convertView;
    }
    public void setAllItemChecked (boolean ischecked){
        List<PackageInfo> data = getData();
        ArrayList<String> packageNames = new ArrayList<>();
        for (PackageInfo packageInfo : data) {
            packageNames.add(packageInfo.packageName);
        }
        for (int i = 0; i <data.size() ; i++) {
            ischecks.put(packageNames.get(i),ischecked);
        }
    }
}
