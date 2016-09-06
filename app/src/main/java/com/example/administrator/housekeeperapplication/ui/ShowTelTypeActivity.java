package com.example.administrator.housekeeperapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.ShowTelClassAdapter;
import com.example.administrator.housekeeperapplication.db.DBread;
import com.example.administrator.housekeeperapplication.entity.TelClassList;
import com.example.administrator.housekeeperapplication.util.MyAssetManger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jiangjiawen on 2016/8/4.
 */
public class ShowTelTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private File file;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tel_type);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        ListView listView = (ListView) findViewById(R.id.show_tel_class_list_view);
        file = MyAssetManger.copyAssetsFileToSDFile(this);
        ArrayList<TelClassList> data = DBread.readTelClass(file);
        ShowTelClassAdapter adapter = new ShowTelClassAdapter(data,this);
        assert listView != null;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent intent = new Intent(this,ShowTelNumActivity.class);
      // intent.putExtra("file",file);
        TelClassList tel = (TelClassList) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("file",file);
        bundle.putInt("idx",tel.getIdx());
        // intent.putExtras(bundle);
        mystartactivity(ShowTelNumActivity.class,bundle);
    }
}
