package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Calling extends AppCompatActivity implements View.OnClickListener{
    private EditText et_phone;
    /*Author：HuangWei1824231127
      Date：2020.6.13
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        et_phone = (EditText) findViewById(R.id.et_phone);
        Button bt_call = (Button) findViewById(R.id.bt_call);

        bt_call.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            //获取输入的电话号码
            String phone = et_phone.getText().toString().trim();
            //创建打电话的意图
            Intent intent = new Intent();
            //设置拨打电话的动作
            intent.setAction(Intent.ACTION_CALL);
            //设置拨打电话的号码
            intent.setData(Uri.parse("tel:" + phone));
            //开启打电话的意图
            startActivity(intent);
        }

    }
