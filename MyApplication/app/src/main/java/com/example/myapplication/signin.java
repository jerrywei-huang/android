package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signin extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btonk;
    private Button btnregist;
    String usernames = null;
    String passwords = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myandroid);
        initView();


    }



    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btonk = (Button) findViewById(R.id.btonk);
        btnregist = (Button) findViewById(R.id.btnregist);

        btonk.setOnClickListener(this);
        btnregist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        usernames = getIntent().getStringExtra("data1");

        passwords = getIntent().getStringExtra("data2");
//        Log.e("name",username);
        switch (v.getId()) {
            case R.id.btonk:
                btonk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (username.getText().toString().equals(usernames)&&password.getText().toString().equals(passwords)) {
                            startActivity(new Intent(signin.this,Homepage.class));//进入主页
                            Toast.makeText(getApplication(), "用户登录成功", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplication(),"用户登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btnregist:
                startActivity(new Intent(this,register.class));
                break;
        }
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
