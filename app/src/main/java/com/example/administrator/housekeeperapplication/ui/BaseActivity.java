package com.example.administrator.housekeeperapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.administrator.housekeeperapplication.R;

public abstract class BaseActivity extends AppCompatActivity{

    private  final boolean isOpenTestToast = false;
    ActionBar actionBar;
    Toolbar toolbar;

    public  void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void showTestToast(String msg){
        if (isOpenTestToast){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public abstract void initview();

    public void mystartactivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    public void mystartactivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
