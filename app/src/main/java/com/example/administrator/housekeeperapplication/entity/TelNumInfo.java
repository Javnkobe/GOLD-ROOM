package com.example.administrator.housekeeperapplication.entity;

/**
 * Created by jiangjiawen on 2016/8/4.
 */
public class TelNumInfo {
    private String name;
    private long telNum;

    public TelNumInfo( String name,long telNum) {
        this.telNum = telNum;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTelNum() {
        return telNum;
    }

    public void setTelNum(long telNum) {
        this.telNum = telNum;
    }
}