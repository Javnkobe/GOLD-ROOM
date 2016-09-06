package com.example.administrator.housekeeperapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.entity.TelClassList;
import com.example.administrator.housekeeperapplication.entity.TelNumInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class ShowTelNumAdapter extends MyBaseAdapter<TelNumInfo>{
    public ShowTelNumAdapter(@Nullable List<TelNumInfo> data, @Nullable Context context) {
        super(data, context);
    }
/*    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }*/

    public View MyGetView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_tel_num_class, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
        tv_name.setText(getData().get(position).getName());
        tv_num.setText(getData().get(position).getTelNum()+"");
        return view;
    }
}
