package com.example.administrator.housekeeperapplication.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.housekeeperapplication.entity.TelClassList;
import com.example.administrator.housekeeperapplication.entity.TelNumInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jiangjiawen on 2016/8/4.
 */
public class DBread {
    public static ArrayList<TelClassList> readTelClass(File file){
        ArrayList<TelClassList> data = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(file,null);
        Cursor cursor = database.rawQuery("select * from classlist",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int idx =  cursor.getInt(cursor.getColumnIndex("idx"));
            TelClassList tel = new TelClassList(name,idx);
            data.add(tel);
        }
        cursor.close();
        return data;
    }
    public static ArrayList<TelNumInfo> readTelNum(File file,int idx){
        ArrayList<TelNumInfo> data = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(file,null);
        Cursor cursor = database.rawQuery("select * from table"+idx,null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            long num =  cursor.getLong(cursor.getColumnIndex("number"));
            TelNumInfo tel = new TelNumInfo(name,num);
            data.add(tel);
        }
        cursor.close();
        return data;
    }
}
