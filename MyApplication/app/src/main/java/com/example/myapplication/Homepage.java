package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Homepage extends AppCompatActivity {

    private GridView gridview;
    List<HashMap<String,Object>> gridlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();

    }

    private void initView() {
        gridview = (GridView) findViewById(R.id.gridview);

        HashMap<String,Object> hashMap;
        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.ren);
        hashMap.put("title","用户中心");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.car);
        hashMap.put("title","数据查看");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.ditu);
        hashMap.put("title","交通管理");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.video);
        hashMap.put("title","视频播放");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.mes);
        hashMap.put("title","信息");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.music);
        hashMap.put("title","音乐播放");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.calling);
        hashMap.put("title","拨打电话");
        gridlist.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img",R.drawable.shuju);
        hashMap.put("title","每日天气");
        gridlist.add(hashMap);

        //定义适配器
        gridview=(GridView)findViewById(R.id.gridview);

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),
                gridlist,
                R.layout.gridview_item,
                new String[]{"img","title"},
                new int[]{R.id.img,R.id.title});
        gridview.setAdapter(simpleAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                switch (position){

                                                    case 0:
                                                        startActivity(new Intent(Homepage.this,UserAdministration.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入用户中心！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 1:
                                                        startActivity(new Intent(Homepage.this,charthomepage.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入数据查看功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 2:
                                                        startActivity(new Intent(Homepage.this,Sideslip.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入交通管理功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 3:
                                                        startActivity(new Intent(Homepage.this,video.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入视频播放功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 4:
                                                        startActivity(new Intent(Homepage.this,ContactActivity.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入信息发送功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 5:
                                                        startActivity(new Intent(Homepage.this,MusicActivity.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入音频播放功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 6:
                                                        startActivity(new Intent(Homepage.this,Calling.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入拨打电话功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 7:
                                                        startActivity(new Intent(Homepage.this,DataActivity.class));
                                                        Toast.makeText(getApplicationContext(),"您即将进入每日天气功能！", Toast.LENGTH_SHORT).show();
                                                        break;
                                                }

                                            }
                                        }
        );


    }

}
