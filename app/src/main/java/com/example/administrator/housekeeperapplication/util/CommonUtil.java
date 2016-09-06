package com.example.administrator.housekeeperapplication.util;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CommonUtil {
    //获取手机品牌
    public static String getBrand() {
        return Build.BRAND;
    }

    //获取手机型号
    public static String getModels() {

        return Build.MODEL;
    }

    //获取手机版本号
    public static String getVersion() {

        return Build.VERSION.RELEASE;
    }

    //获取CPU的名称
    public static String getCPUName(){
        return Build.CPU_ABI;
    }
   /* public static String getCPUName() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("proc/cpuinfo"));
            String readLine = bufferedReader.readLine();
            String[] arr = readLine.split("\\s+", 2);
            bufferedReader.close();
            return arr[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    //获取CPU数量
    public static int getPhoneCPUNumber() {

        File file = new File("sys/devices/system/cpu");
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //如果文件的名称是0-9的说明是一个CPU
                if (pathname.getName().matches("cpu[0-9]")) {
                    return true;
                }
                return false;
            }
        });

        return files.length;
    }

    //获取屏幕分辨率（宽*高）
    public static String getWindowPixel(Context context) {
        //获得屏幕管理器
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowMgr.getDefaultDisplay();//返回屏幕的对象

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);//获取屏幕的尺寸

        return metrics.widthPixels + "*" + metrics.heightPixels;
    }

    //获取相机最大分辨率
    public static String getCameraPixel() {
        //打开摄像头  默认后置
        android.hardware.Camera camera = android.hardware.Camera.open();
        android.hardware.Camera.Parameters parameters = camera.getParameters();//获得手机像度
        //获取所支持的照片大小
        List<Camera.Size> sizeList = parameters.getSupportedPictureSizes();
        int max = 0;
        int[] sizes = new int[2];
        for (android.hardware.Camera.Size size : sizeList) {
            //从集合中所有尺寸中获取最大分辨率
            if (max < size.width * size.height) {
                max = size.width * size.height;
                sizes[0] = size.width;
                sizes[1] = size.height;
            }
        }
        //关闭摄像机
        camera.release();
        camera = null;
        return sizes[0] + "*" + sizes[1];
    }
    //获取基带版本
    public static String getRadio(){
        return Build.RADIO;
    }
    //是否Root
    public static boolean isRoot(){
        if (!new File("/system/bin/su").exists()&& !new File("/system/xbin/su").exists()){
            return false;
        }else {

            return true;
        }
    }

}
