package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataSensorActivity extends AppCompatActivity {
    ListView listView;
    List<HashMap<String,Object>> listmap=new ArrayList<>();
    SensorDBHelper sensorDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sensor);
        listView=findViewById(R.id.sensorlist);
        sensorDBHelper=new SensorDBHelper(this);
        Cursor c =sensorDBHelper.querySensor();
        HashMap<String,Object> hashMap;
        while (c.moveToNext()){
            hashMap=new HashMap<>();
            hashMap.put("sensorid",c.getString(1).toString());
            hashMap.put("humity",c.getString(2).toString());
            hashMap.put("pm",c.getString(3).toString());
            hashMap.put("temp",c.getString(4).toString());
            hashMap.put("date",c.getString(5).toString());
            listmap.add(hashMap);

        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,
                listmap,
                R.layout.sensorrow_item,
                new String[]{"sensorid","humity","pm","temp","date"},
                new int[]{R.id.sensorid,R.id.humityid,R.id.pmid,R.id.tempid,R.id.dateid}
        );
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
