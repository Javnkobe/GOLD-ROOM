package com.example.administrator.housekeeperapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.entity.RunningAppInfo;

import java.util.List;

public class MyAppProcessAdapter extends MyBaseAdapter<RunningAppInfo> {


    public MyAppProcessAdapter(@Nullable List<RunningAppInfo> data, @Nullable Context context) {
        super(data, context);
    }

    @Override
    public View MyGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_progress, null);
            ImageView icon = (ImageView) convertView.findViewById(R.id.iv_app_photo);
            TextView appName = (TextView) convertView.findViewById(R.id.tv_app_name);
            TextView memorySize = (TextView) convertView.findViewById(R.id.tv_app_memorysize);
            TextView appType = (TextView) convertView.findViewById(R.id.tv_app_type);
            //item 中的checkbox
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.cb_checkbox);
            vh = new ViewHolder(icon,appName,memorySize,appType,checkBox);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        final RunningAppInfo runningAppInfo = getData().get(position);
        vh.icon.setImageDrawable(runningAppInfo.getAppIcon());
        vh.appName.setText(runningAppInfo.getLable());
        vh.memorySize.setText(String.format("内存：%s",runningAppInfo.getMemorySize()));
        vh.appType.setText(runningAppInfo.isSysApp()?"系统进程":"用户进程");

        vh.checkBox.setTag(runningAppInfo);
        vh.checkBox.setChecked(runningAppInfo.isSelect());
        vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox cb = (CheckBox) buttonView;
                RunningAppInfo appInfo = (RunningAppInfo) cb.getTag();
                appInfo.setSelect(isChecked);
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView appName;
        TextView memorySize;
        TextView appType;
        CheckBox checkBox;

        public ViewHolder(ImageView icon, TextView appName, TextView memorySize, TextView appType,  CheckBox checkBox) {
            this.icon = icon;
            this.appName = appName;
            this.memorySize = memorySize;
            this.appType = appType;
            this.checkBox = checkBox;
        }
    }

}

  /*  private final List<RunningAppInfo> runningAppInfos;
    private final Context context;

    public MyAppProcessAdapter(@NonNull List<RunningAppInfo> runningAppInfos, Context context) {
        this.runningAppInfos = runningAppInfos;
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return runningAppInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return runningAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
*/
   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_progress,null);

        ImageView icon = (ImageView) view.findViewById(R.id.iv_app_photo);
        TextView appName = (TextView) view.findViewById(R.id.tv_app_name);
        TextView memorySize = (TextView) view.findViewById(R.id.tv_app_memorysize);
        TextView appType = (TextView) view.findViewById(R.id.tv_app_type);

        RunningAppInfo runningAppInfo = runningAppInfos.get(position);
        icon.setImageDrawable(runningAppInfo.getAppIcon());
        appName.setText(runningAppInfo.getLable());
        memorySize.setText(String.format("内存：%s",runningAppInfo.getMemorySize()));
        appType.setText(runningAppInfo.isSysApp()?"系统进程":"用户进程");
        return view;
    }*/

