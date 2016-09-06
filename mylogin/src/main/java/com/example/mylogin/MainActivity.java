package com.example.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  EditText usename;
    private  EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usename = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        SharedPreferences preferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uname = preferences1.getString("username", "zhansan");
        String pwd = preferences1.getString("password", "12");

       /* SharedPreferences preferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
        String u1 = preferences1.getString("username", "123132");

        String p1 = preferences1.getString("password","123");*/

        if ("jjw".equals(uname) && "123456".equals(pwd)){
            Intent intent = new Intent();
            intent.setClass(this,ChatRomeActivity.class);
            this.startActivity(intent);
        }
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        assert checkBox != null;
        boolean isChecked=false;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("username",usename.getText().toString());
                    edit.putString("password",password.getText().toString());
                    edit.apply();
                    }
                }
        });
    }
    public void login(View v){
        String u = usename.getText().toString();
        String p = password.getText().toString();
        if ("jjw".equals(u) && "123456".equals(p) ){
            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(this,ChatRomeActivity.class);
            this.startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
        }
    }
}
