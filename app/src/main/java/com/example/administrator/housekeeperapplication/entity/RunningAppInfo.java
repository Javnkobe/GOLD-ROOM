package com.example.administrator.housekeeperapplication.entity;
import android.graphics.drawable.Drawable;


public class RunningAppInfo {

    String packageName;//进程名，包名
    String lable;//应用名
    String memorySize;//进程占内存的大小
    Drawable  appIcon;//应用的图标
    boolean isSysApp;//是否是系统应用，true 表示是系统应用
    int importance;//进程类别
    boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public boolean isSysApp() {
        return isSysApp;
    }

    public void setSysApp(boolean sysApp) {
        isSysApp = sysApp;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public RunningAppInfo(String lable, String packageName, String memorySize, Drawable appIcon, boolean isSysApp, int importance) {
        this.packageName = packageName;
        this.lable = lable;
        this.memorySize = memorySize;
        this.appIcon = appIcon;
        this.isSysApp = isSysApp;
        this.importance = importance;
    }
}

