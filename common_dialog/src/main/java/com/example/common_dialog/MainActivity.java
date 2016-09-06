package com.example.common_dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_check).setOnClickListener(this);
        findViewById(R.id.btn_daycheck).setOnClickListener(this);
        findViewById(R.id.btn_timecheck).setOnClickListener(this);
        findViewById(R.id.btn_bubblecheck).setOnClickListener(this);
        findViewById(R.id.btn_custom_check).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                //final String[] data = {"北京","上海","深圳","广州"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示信息")
                        .setIcon(R.mipmap.ic_launcher);
               /* builder.setSingleChoiceItems(
                        data,
                        -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, data[which], Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                );*/
                builder.setMessage("是否退出当前页面并保存编辑内容");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "内容已保存", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "再见", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("不保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消保存", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.btn_custom_check:
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_alert, null);
                builder1.setView(view);
                view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "无法退出", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.create().show();
                break;
            case R.id.btn_daycheck:
                DatePickerDialog dialog1 = new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Toast.makeText(MainActivity.this, year + " " + monthOfYear + " " + dayOfMonth, Toast.LENGTH_SHORT).show();
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                dialog1.show();
                break;
            case R.id.btn_timecheck:
                TimePickerDialog dialog = new TimePickerDialog(
                        this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(MainActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                            }
                        },
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                        Calendar.getInstance().get(Calendar.MINUTE),
                        false
                );
                dialog.show();
                break;
            case R.id.btn_bubblecheck:
                final PopupWindow window = new PopupWindow(this);
                window.setHeight(250);
                window.setWidth(300);
                View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_custom_alert, null);
                window.setContentView(view1);
                //window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_custom_dialog_back));
                view1.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });
                window.showAsDropDown(v, -60, -60);
                break;
        }
    }
}
