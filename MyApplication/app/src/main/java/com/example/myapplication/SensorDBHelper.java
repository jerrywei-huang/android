package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class SensorDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public SensorDBHelper(@Nullable Context context) {
        super(context,"sensordb.db", null, 1);
    }
    private static final String CREATE_SENSORTBL="create table sensortable" +
            "(_id integer primary key autoincrement," +
            "senor_id text," +
            "humidity text," +
            "pm text," +
            "temperature text ," +
            "date text )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        db.execSQL(CREATE_SENSORTBL);
    }
    //数据添加
    int insertSensor(ContentValues contentValues){
        int i=0;
        db = getWritableDatabase();
        i = (int) db.insert("sensortable",null,contentValues);
        return i;
    }

    //数据清空
    public  int deleteSesor(){
        int i =0;
        db=getWritableDatabase();
        i =(int) db.delete("sensortable", null, null);
        return i;

    }
    //根据输入的传感器编号，修改对应的湿度，温度，PM，日期。
    public int updateSensor(String sensorid,ContentValues contentValues){
        int i =0;
        db =getWritableDatabase();
        i=(int)db.update("sensortable",contentValues,"senor_id=?",new String[]{sensorid});
        return  i;
    }

    //数据的查询
    public Cursor querySensor(){
        Cursor cursor = null;
        db = getWritableDatabase();
        cursor = db.query("sensortable",null,null,null,null,null,null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
