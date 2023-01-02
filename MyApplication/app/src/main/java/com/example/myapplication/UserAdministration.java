package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserAdministration extends AppCompatActivity implements View.OnClickListener {

    private Button categroy_user;
    private Button settingbtn_user;
    private ImageView img;
    private Button user_evaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_administration);
        initView();

    }

    private void initView() {
        categroy_user = (Button) findViewById(R.id.categroy_user);
        settingbtn_user = (Button) findViewById(R.id.settingbtn_user);


        categroy_user.setOnClickListener(this);
        settingbtn_user.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);

        user_evaluate = (Button) findViewById(R.id.user_evaluate);
        user_evaluate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.categroy_user:
                startActivity(new Intent(UserAdministration.this, Homepage.class));
                break;
            case R.id.settingbtn_user:
                exitDialog();

                break;
            case R.id.user_evaluate:
                userEvaluate();
                break;
        }
    }

    private void userEvaluate() {
        LayoutInflater inflater = getLayoutInflater();
        View myDialogView = inflater.inflate(R.layout.userevaluate, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserAdministration.this);
        builder.setTitle("输入现在的想法")
                .setIcon(R.drawable.logo)
                .setView(myDialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "提交成功", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        }).show();

    }

    private void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserAdministration.this);

        builder.setTitle("退出登录提示")
                .setMessage("确定退出？")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(UserAdministration.this, signin.class));
                        Toast.makeText(getApplication(), "退出成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                }).show();
    }
}
