package com.example.administrator.housekeeperapplication.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.entity.PhoneInfo;

import java.util.List;
import java.util.zip.Inflater;

public class PhonemgrAdapter extends MyBaseAdapter<PhoneInfo> {

    public PhonemgrAdapter(@Nullable List<PhoneInfo> data, @Nullable Context context) {
        super(data, context);
    }

    @Override
    public View MyGetView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_phonemgr_listitem, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_phonemgr_icon);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_phonemgr_title);
            viewHolder.text = (TextView) convertView.findViewById(R.id.tv_phonemgr_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        PhoneInfo info = getData().get(position);
        //设置数据
        viewHolder.icon.setImageResource(info.getIcon());
        viewHolder.title.setText(info.getTitle());
        viewHolder.text.setText(info.getText());
        switch (position % 3) {
            case 0:
                viewHolder.icon.setBackgroundResource(R.drawable.notification_information_progress_green);
                break;
            case 1:
                viewHolder.icon.setBackgroundResource(R.drawable.notification_information_progress_red);
                break;
            case 2:
                viewHolder.icon.setBackgroundResource(R.drawable.notification_information_progress_yellow);
                break;
            default:
        }
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView title, text;
    }
}
        /*
        if (convertView == null) {
            LayoutInflater layoutInflater = new LayoutInflater();
            convertView = Inflater.inflate(R.layout.layout_phonemgr_listitem, null);
        }
        PhoneInfo phoneInfo = (PhoneInfo) getItem(position);

        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_phonemgr_icon);
        TextView title = (TextView) convertView.findViewById(R.id.tv_phonemgr_title);
        TextView text = (TextView) convertView.findViewById(R.id.tv_phonemgr_text);

        icon.setImageDrawable(phoneInfo.getIcon());
        title.setText(phoneInfo.getTitle());
        text.setText(phoneInfo.getText());*/

        // 给每个图加不同背景(无实际作用)

       // return convertView;




