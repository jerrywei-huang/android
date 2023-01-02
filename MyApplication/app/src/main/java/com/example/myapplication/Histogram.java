package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
public class Histogram extends AppCompatActivity {
    private BarChart barChart;
    private List<Float> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histogram_activity);
        initView();
        setData();
    }
    private void initView() {
        barChart = (BarChart) findViewById(R.id.hisChart);
    }
    private void setData(){
        for (int i=0;i<7;i++){
            data.add((float) (Math.random()*5));
        }
        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            entries.add(new BarEntry(i + 1,data.get(i)));
        }
        BarDataSet dataSet = new BarDataSet(entries,"温度变化");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setDrawValueAboveBar(true);
        barChart.getXAxis().setAxisLineWidth(5);
        Description description=new Description();
        description.setText("图表描述");
        description.setTextColor(Color.GREEN);
        barChart.setDescription(description);
        barChart.invalidate();

    }

}

