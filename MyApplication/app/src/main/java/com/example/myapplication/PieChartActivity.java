package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class PieChartActivity extends Activity {
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechar_activity);
        initView();
    }

    private void initView() {
        pieChart = (PieChart) findViewById(R.id.pieCharts);

        pieChart.setDrawHoleEnabled(true); // 显示饼图中的透明圆
        pieChart.setHoleRadius(40f); // 设置饼图中心圆的半径
        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("2019年季度支出"); // 饼图中心的文本
        pieChart.setCenterTextSize(15f);
        pieChart.setCenterTextRadiusPercent(200f); //设置中心文本边框的矩形范围

        pieChart.setRotationEnabled(true); // 可手动旋转
        pieChart.setTouchEnabled(true); // 可触摸
        pieChart.animateXY(2000,2000);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setRotationAngle(90);
        pieChart.setUsePercentValues(true); // 将数值显示成百分比

        pieChart.setExtraOffsets(26, 5, 26, 5);
        // 图例与描述
        Description description = pieChart.getDescription(); //得到图表描述对象
        description.setText("数据统计"); // 设置描述的文本
        description.setTextSize(20f); // 设置描述文本的大小
        description.setEnabled(true); // 开启描述

        Legend legend = pieChart.getLegend(); //得到图表图例对象

        legend.setForm(Legend.LegendForm.CIRCLE); // 设置图例样式(圆形)
        legend.setTextSize(10f); //设置图例文字大小
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // 设置图例垂直显示
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER); // 图例位置
        legend.setXEntrySpace(10f);
        legend.setYEntrySpace(5f);
        legend.setTextColor(Color.GRAY);
        //// 和四周相隔一段距离,显示数据
        pieChart.setData(getPieData());
        // 点击事件
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                Toast.makeText(PieChartActivity.this, "" + entry.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    private PieData getPieData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        //  饼图数据
        float data1 = 456787;
        float data2 = 534283;
        float data3 = 345734;
        float data4 = 658465;
        pieEntries.add(new PieEntry(data1, "第1季度"));
        pieEntries.add(new PieEntry(data2, "第2季度"));
        pieEntries.add(new PieEntry(data3, "第3季度"));
        pieEntries.add(new PieEntry(data4, "第4季度"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "2019年季度收入");
        dataSet.setValueTextSize(15f); // 图表数值文字大小
        dataSet.setSliceSpace(5f); // 饼图每块数据的间隔
        dataSet.setSliceSpace(0f);
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        return new PieData(dataSet);
    }

}