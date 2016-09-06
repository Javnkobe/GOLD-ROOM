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

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by jiangjiawen on 2016/8/4.
 */
public class ShowTelClassAdapter extends MyBaseAdapter<TelClassList> {
    public ShowTelClassAdapter(@Nullable List<TelClassList> data, @Nullable Context context) {
        super(data, context);
    }
    /*private Context context;
    private LayoutInflater inflater;
    private ArrayList<TelClassList> data;*/

    /*public ShowTelClassAdapter(@NonNull ArrayList<TelClassList> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    @Override
   /* public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
*/


    public View MyGetView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_tel_type_class, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(getData().get(position).getName());
        return view;
    }
}
