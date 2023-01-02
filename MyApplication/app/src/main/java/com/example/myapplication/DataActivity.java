package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    SensorDBHelper sensorDBHelper;
    private Button insertbtn;
    private Button clearbtn;
    private Button updbtn;
    private EditText sensorid;
    private EditText humityid;
    private EditText pmid;
    private EditText tempid;
    private EditText dateid;
    private Button firstbtn;
    private Button priorbtn;
    private Button nextbtn;
    Cursor cursor;
    private Button returnbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sensorDBHelper = new SensorDBHelper(this);
        cursor = sensorDBHelper.querySensor();
        initView();
        initDate();
    }

    private void initDate() {
        Intent intent=getIntent();
        int position=intent.getIntExtra("position",1);
        boolean flag=cursor.moveToPosition(position);
        if (flag){
            sensorid.setText(cursor.getString(1).toString());
            humityid.setText(cursor.getString(2).toString());
            pmid.setText(cursor.getString(3).toString());
            tempid.setText(cursor.getString(4).toString());
            dateid.setText(cursor.getString(5).toString());
        } else
            Toast.makeText(this, "数据初始化失败", Toast.LENGTH_SHORT).show();

    }

    private void initView() {
        insertbtn = (Button) findViewById(R.id.insertbtn);

        insertbtn.setOnClickListener(this);
        clearbtn = (Button) findViewById(R.id.clearbtn);
        clearbtn.setOnClickListener(this);
        updbtn = (Button) findViewById(R.id.updbtn);
        updbtn.setOnClickListener(this);
        sensorid = (EditText) findViewById(R.id.sensorid);
        sensorid.setOnClickListener(this);
        humityid = (EditText) findViewById(R.id.humityid);
        humityid.setOnClickListener(this);
        pmid = (EditText) findViewById(R.id.pmid);
        pmid.setOnClickListener(this);
        tempid = (EditText) findViewById(R.id.tempid);
        tempid.setOnClickListener(this);
        dateid = (EditText) findViewById(R.id.dateid);
        dateid.setOnClickListener(this);
        firstbtn = (Button) findViewById(R.id.firstbtn);
        firstbtn.setOnClickListener(this);
        priorbtn = (Button) findViewById(R.id.priorbtn);
        priorbtn.setOnClickListener(this);
        nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
        returnbtn = (Button) findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i;
        ContentValues contentValues;
        switch (v.getId()) {
            case R.id.insertbtn:
                contentValues = new ContentValues();
                contentValues.put("senor_id ", sensorid.getText().toString());
                contentValues.put("humidity  ", humityid.getText().toString());
                contentValues.put("pm ", pmid.getText().toString());
                contentValues.put("temperature ", tempid.getText().toString());
                contentValues.put("date ", dateid.getText().toString());

                i = sensorDBHelper.insertSensor(contentValues);
                if (i != 0)
                    Toast.makeText(this, "插入成功！", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "插入失败！", Toast.LENGTH_SHORT).show();
                break;


            case R.id.clearbtn:
                i = sensorDBHelper.deleteSesor();

                if (i != 0)
                    Toast.makeText(this, "清空成功！", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "清空失败！", Toast.LENGTH_SHORT).show();
                break;


            case R.id.updbtn:
                contentValues = new ContentValues();
                contentValues.put("senor_id ", sensorid.getText().toString());
                contentValues.put("humidity  ", humityid.getText().toString());
                contentValues.put("pm ", pmid.getText().toString());
                contentValues.put("temperature ", tempid.getText().toString());
                contentValues.put("date ", dateid.getText().toString());
                i = sensorDBHelper.updateSensor(sensorid.getText().toString(), contentValues);

                if (i != 0)
                    Toast.makeText(this, "校正成功！", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "校正失败！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.firstbtn:

                if (cursor.moveToFirst()) {
                    sensorid.setText(cursor.getString(1).toString());
                    humityid.setText(cursor.getString(2).toString());
                    pmid.setText(cursor.getString(3).toString());
                    tempid.setText(cursor.getString(4).toString());
                    dateid.setText(cursor.getString(5).toString());
                } else
                    Toast.makeText(this, "不存在数据", Toast.LENGTH_SHORT).show();
                break;
            case R.id.priorbtn:
                if (cursor.moveToPrevious()) {
                    sensorid.setText(cursor.getString(1).toString());
                    humityid.setText(cursor.getString(2).toString());
                    pmid.setText(cursor.getString(3).toString());
                    tempid.setText(cursor.getString(4).toString());
                    dateid.setText(cursor.getString(5).toString());
                } else
                    Toast.makeText(this, "不存在上一条数据", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nextbtn:
                if (cursor.moveToNext()) {
                    sensorid.setText(cursor.getString(1).toString());
                    humityid.setText(cursor.getString(2).toString());
                    pmid.setText(cursor.getString(3).toString());
                    tempid.setText(cursor.getString(4).toString());
                    dateid.setText(cursor.getString(5).toString());
                } else
                    Toast.makeText(this, "不存在下一条数据", Toast.LENGTH_SHORT).show();
                break;
            case R.id.returnbtn:
                startActivity(new Intent(DataActivity.this,DataSensorActivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String sensoridString = sensorid.getText().toString().trim();
        if (TextUtils.isEmpty(sensoridString)) {
            Toast.makeText(this, "编号", Toast.LENGTH_SHORT).show();
            return;
        }

        String humityidString = humityid.getText().toString().trim();
        if (TextUtils.isEmpty(humityidString)) {
            Toast.makeText(this, "湿度", Toast.LENGTH_SHORT).show();
            return;
        }

        String pmidString = pmid.getText().toString().trim();
        if (TextUtils.isEmpty(pmidString)) {
            Toast.makeText(this, "PM", Toast.LENGTH_SHORT).show();
            return;
        }

        String tempidString = tempid.getText().toString().trim();
        if (TextUtils.isEmpty(tempidString)) {
            Toast.makeText(this, "温度", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateidString = dateid.getText().toString().trim();
        if (TextUtils.isEmpty(dateidString)) {
            Toast.makeText(this, "日期", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
