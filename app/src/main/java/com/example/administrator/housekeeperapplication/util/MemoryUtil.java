package com.example.administrator.housekeeperapplication.util;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MemoryUtil {

    //获取手机最大运存   单位字节
    public static long getTotaMem(){

        File file=new File("proc/meminfo");
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] data = line.split("\\s+");//切割空格
            String s=data[1];
            int b = Integer.parseInt(s)*1024;
            br.close();
            return /*Long.valueOf(data[1])*/b/1024/1024;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



    //获取手机剩余运存
    public static long getFreeMem(Context context){
        //获取活动管理器
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //创建内存信息对象
        ActivityManager.MemoryInfo info=new ActivityManager.MemoryInfo();
        //获得内存信息存入对象
        am.getMemoryInfo(info);
        return info.availMem/1024/1024;/*availMem   有效内存*/
    }

}

