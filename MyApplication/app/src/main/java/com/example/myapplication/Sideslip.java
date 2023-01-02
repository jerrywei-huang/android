package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sideslip extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    List<HashMap<String, Object>> menulist = new ArrayList<>();
    private Button categroy;
    private Button settingbtn;
    private LinearLayout mainlayou;
    private LinearLayout homesubmen;
    private DrawerLayout homelayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sideslip);
        initView();
        initmenuData();
        initMenuView();
    }

    private void initView() {
        categroy = (Button) findViewById(R.id.categroy);
        settingbtn = (Button) findViewById(R.id.settingbtn);
        mainlayou = (LinearLayout) findViewById(R.id.mainlayou);
        homesubmen = (LinearLayout) findViewById(R.id.homesubmen);
        homelayout = (DrawerLayout) findViewById(R.id.homelayout);

        categroy.setOnClickListener(this);
        settingbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.categroy:
                homelayout.openDrawer(homesubmen);
                break;
            case R.id.settingbtn:

                break;
        }
    }
    private void initMenuView() {

        listView = findViewById(R.id.menulist);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                menulist,
                R.layout.mylinerlayout,
                new String[]{"img", "title"},
                new int[]{R.id.home_item_img, R.id.home_item_name});

        listView.setAdapter(simpleAdapter);

    }

    private void initmenuData() {
        HashMap<String, Object> hashMap;
        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.one);
        hashMap.put("title", "用户管理");
        menulist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.two);
        hashMap.put("title", "车间管理");
        menulist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.three);
        hashMap.put("title", "设备管理");
        menulist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.four);
        hashMap.put("title", "系统管理");
        menulist.add(hashMap);
    }



}
