package com.example.singledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText2;
    private EditText editText1;
    private String s2;
    private String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.et_one);
        editText2 = (EditText) findViewById(R.id.et_two);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        s1 = editText1.getText().toString();
        s2 = editText2.getText().toString();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("key1",s1);
                intent.putExtra("key2",s2);
                startActivity(intent);
                break;
            case R.id.btn_two:

                break;
        }
    }
}
