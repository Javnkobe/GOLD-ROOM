package com.example.administrator.housekeeperapplication.ui;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.ShowTelNumAdapter;
import com.example.administrator.housekeeperapplication.db.DBread;
import com.example.administrator.housekeeperapplication.entity.TelNumInfo;

import java.io.File;
import java.util.ArrayList;

public class ShowTelNumActivity extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int idx = bundle.getInt("idx", 1);
        File file = (File) bundle.getSerializable("file");
        ArrayList<TelNumInfo> telNumInfos = DBread.readTelNum(file, idx);

        ShowTelNumAdapter adapter = new ShowTelNumAdapter(telNumInfos, this);
        ListView listView = getListView();
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        TelNumInfo tel = (TelNumInfo) parent.getItemAtPosition(position);
        intent.setData(Uri.parse("tel:" + tel.getTelNum()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
}
