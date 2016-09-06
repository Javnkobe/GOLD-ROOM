package com.example.administrator.housekeeperapplication.entity;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class PhoneInfo {
    private int icon;
    private String title;
    private String text;

    public PhoneInfo(int icon, String title, String text) {
        this.icon = icon;
        this.title = title;
        this.text = text;
    }

    public int getIcon() {

        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
