package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private RadioButton man;
    private RadioButton woman;
    private ToggleButton marraged;
    private CheckBox reading;
    private CheckBox swimming;
    private CheckBox running;
    private Button btncancle;
    private Button btnregist;
    private Spinner position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        marraged = (ToggleButton) findViewById(R.id.marraged);
        reading = (CheckBox) findViewById(R.id.reading);
        swimming = (CheckBox) findViewById(R.id.swimming);
        running = (CheckBox) findViewById(R.id.running);
        btncancle = (Button) findViewById(R.id.btncancle);
        btnregist = (Button) findViewById(R.id.btnregist);




        marraged.setOnClickListener(this);
        btncancle.setOnClickListener(this);
        btnregist.setOnClickListener(this);
        position = (Spinner) findViewById(R.id.position);


        String array[] = new String[]{"ceo", "cfo", "pm", "pe"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(), android.R.layout.simple_spinner_item, array);
        position.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.marraged:
                break;
            case R.id.btncancle:
                break;
            case R.id.btnregist:
                Bundle bundle = new Bundle();
                String name = username.getText().toString();
                bundle.putString("username",name );
                bundle.putString("password", password.getText().toString());
                String ssex= "";
                if (man.isChecked())
                    ssex = "男";
                else
                    ssex = "女";
                bundle.putString("ssex", ssex);
                String hobby = "";
                if (reading.isChecked())
                    hobby = "阅读";
                if (running.isChecked())
                    hobby =  "跑步";
                if (swimming.isChecked())
                    hobby ="游泳";
                bundle.putString("hobby", hobby);
                String marriage = "婚否：";
                if (marriage.isEmpty())
                    marriage = "已婚";
                else
                    marriage = "未婚";
                bundle.putString("marriage", marriage);

                bundle.putString("position",position.getSelectedItem().toString());
                User u =new User();
                u.setUsername(bundle.getString("username"));
                u.setPassword(bundle.getString("password"));
                u.setGender(bundle.getString("ssex"));
                u.setHobby(bundle.getString("hobby"));
                u.setMarrige(bundle.getString("marriage"));
                u.setPosition(bundle.getString("position"));
                boolean flag =new DataService().add(u);

                if(flag){
                    Intent intent = new Intent(this, ResultUserInfo.class);
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                    Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(getApplication(),bundle.getString("username"),Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplication(), "即将跳转到另一页面", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


}
