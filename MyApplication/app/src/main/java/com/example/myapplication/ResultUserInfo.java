package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ResultUserInfo extends AppCompatActivity implements View.OnClickListener {
    private TextView result;
    private Button resultbun;
    HashMap<String, String> usermap= new HashMap<String, String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        initView();


    }

    private void initView() {
        result = (TextView) findViewById(R.id.result);
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("data");
        usermap=new HashMap<String, String>();
        usermap.put("username",b.getString("username"));
        usermap.put("password",b.getString("password"));
        result.setText("用户名:" + b.getString("username") + "\n" +
                "用户密码:" + b.getString("password") + "\n" +
                "用户性别:" + b.getString("ssex") + "\n" +
                "用户爱好:" + b.getString("hobby") + "\n" +
                "用户职位:"+b.getString("position")+"\n"+
                "婚否:" + b.getString("marriage")
        );
        resultbun = (Button) findViewById(R.id.resultbun);
        resultbun.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resultbun:

                Intent intent = new Intent(this,signin.class);

                intent.putExtra("data1",usermap.get("username"));
                intent.putExtra("data2",usermap.get("password"));
                startActivity(intent);
                break;
        }
    }
}
