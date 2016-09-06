package com.example.mylogin;

import android.app.Activity;
import android.app.ListActivity;;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRomeActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Intent intent = getIntent();*/
        setResult(Activity.RESULT_OK,new Intent().putExtra("key","value"));
        ListView listView = getListView();
        String[] d = LasterData.data;
        int len = LasterData.data.length;
        String[] sf = {"image","text"};
        int[] imageId = {R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e,R.mipmap.a1,R.mipmap.b1,R.mipmap.c1,R.mipmap.d1,R.mipmap.e1};
        List<Map<String,Object>> data2 = new ArrayList<>();
        for (int i=0;i<len;i++){
            Map<String,Object> map = new HashMap<>();
            map.put(sf[0],imageId[i]);
            map.put(sf[1],d[i]);
            data2.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data2,
                R.layout.activity_chat_rome,
                sf,
                new int[]{R.id.imageview,R.id.textview}
        );
        listView.setAdapter(adapter);
    }
}
