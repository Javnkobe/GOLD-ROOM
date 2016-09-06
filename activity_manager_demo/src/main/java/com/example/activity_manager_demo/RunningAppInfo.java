package com.example.activity_manager_demo;/*
package com.example.activity_manager_demo;

import android.graphics.drawable.Drawable;


public class RunningAppInfo {

    String packageName;//进程名
    String memorySize;//进程大小
    Drawable isSysApp;//是否为系统应用
    Drawable appIcon;//应用图标
    int important;//进程类别

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

    public boolean isSysApp() {
        return isSysApp;
    }

    public void setSysApp(boolean sysApp) {
        isSysApp = sysApp;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public RunningAppInfo(String packageName, String memorySize, Drawable isSysApp, boolean appIcon, int important) {
        this.packageName = packageName;
        this.memorySize = memorySize;
        this.isSysApp = isSysApp;
        this.appIcon = appIcon;

        this.important = important;
    }
}
*/
import android.graphics.drawable.Drawable;


public class RunningAppInfo {

    String packageName;//进程名，包名
    String lable;//应用名
    String memorySize;//进程占内存的大小
    Drawable  appIcon;//应用的图标
    boolean isSysApp;//是否是系统应用，true 表示是系统应用
    int importance;//进程类别

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

