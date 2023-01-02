package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class sumchar extends AppCompatActivity {

    private BarChart barChart;
    private List<Float> data = new ArrayList<>();
    private LineChart lineChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        initView1();
        initView2();
        initView3();
        setData();
    }
    private void initView1() {
        barChart = (BarChart) findViewById(R.id.main_barchart);
    }

    private void setData(){
        for (int i=0;i<7;i++){
            data.add((float) (Math.random()*5));
        }
        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            entries.add(new BarEntry(i + 1,data.get(i)));
        }
        Legend legend  =barChart.getLegend(); // 得到图例
        legend.setForm(Legend.LegendForm.CIRCLE); // 图例样式

        BarDataSet dataSet = new BarDataSet(entries,"温度变化");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setDrawValueAboveBar(true);
        barChart.getXAxis().setAxisLineWidth(5);
        Description description1=new Description();
        description1.setText("图表描述");
        description1.setTextColor(Color.GREEN);
        barChart.setDescription(description1);
        barChart.invalidate();

    }

    private void initView2() {
        lineChart = (LineChart) findViewById(R.id.main_linechart);
        List<Entry> entries = new ArrayList<>();
        Entry entry;
        for(int i = 0; i < 12; i++) {
            //1
            entry=new Entry(i,new Random().nextInt(300));
            //2.
            entries.add(entry);
        }
        //3
        LineDataSet dataSet = new LineDataSet(entries,"测试数据");

        // 线条样式
        dataSet.setColor(Color.parseColor("#FF03A9F4")); // 线条颜色
        dataSet.setCircleColor(Color.parseColor("#FF03A9F4")); // 圆点颜色
        dataSet.setCircleRadius(3f); // 圆点半径
        dataSet.setLineWidth(2f); // 线条宽度
        dataSet.setValueTextColor(Color.parseColor("#000000")); // 数值颜色
        dataSet.setValueTextSize(15f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //5.
        LineData lineData = new LineData(dataSet);
        //6.
        lineChart.setData(lineData);
        lineChart.animateX(2500);
        lineChart.animateY(2500);


        // X轴和Y轴样式
        // Y轴
        YAxis leftYAxis = lineChart.getAxisLeft(); // 得到右边的Y轴
        leftYAxis.setEnabled(true); // 右边的Y轴

        leftYAxis.resetAxisMaximum();
        leftYAxis.resetAxisMinimum();
        // X轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#DE4242")); // 设置X轴文本颜色
        xAxis.setTextSize(15f);
        xAxis.setDrawAxisLine(true); // 绘制X轴轴线
        xAxis.setDrawGridLines(true); // 绘制与X轴文本对应的Y轴
        xAxis.setDrawLabels(true); // 显示X轴上的文本
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置x轴的显示位置

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return String.valueOf(v + 1).concat("月");
            }
        });
        xAxis.setEnabled(true);
        // 图例与描述
        Legend legend  =lineChart.getLegend(); // 得到图例
        legend.setForm(Legend.LegendForm.LINE); // 图例样式
        legend.setTextSize(15f);
        legend.setFormSize(15f);
        legend.setTextColor(Color.BLUE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        Description description = lineChart.getDescription();
        description.setEnabled(true); // 关闭描述
        description.setText("内容描述");
        lineChart.setTouchEnabled(true); // 关闭图表触摸反馈
        List<ILineDataSet> setsCubic = lineChart.getData().getDataSets();
        for (ILineDataSet iSet : setsCubic) {
            LineDataSet set = (LineDataSet) iSet;
            set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            set.setDrawFilled(true);
        }
        lineChart.setDragDecelerationEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.5f);
        lineChart.invalidate(); //刷新图表
    }
    private void initView3() {
        pieChart = (PieChart) findViewById(R.id.main_piechart);

        pieChart.setDrawHoleEnabled(true); // 显示饼图中的透明圆
        pieChart.setHoleRadius(40f); // 设置饼图中心圆的半径
        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("开销统计"); // 饼图中心的文本
        pieChart.setCenterTextSize(12f);
        pieChart.setCenterTextRadiusPercent(200f); //设置中心文本边框的矩形范围

        pieChart.setRotationEnabled(true); // 可手动旋转
        pieChart.setTouchEnabled(true); // 可触摸
        pieChart.animateXY(2000,2000);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setRotationAngle(90);
        pieChart.setUsePercentValues(true); // 将数值显示成百分比

        pieChart.setExtraOffsets(26, 5, 26, 5);
        // 图例与描述
        Description description2 = pieChart.getDescription(); //得到图表描述对象
        description2.setText("数据统计"); // 设置描述的文本
        description2.setTextSize(20f); // 设置描述文本的大小
        description2.setEnabled(true); // 开启描述

        Legend legend2 = pieChart.getLegend(); //得到图表图例对象

        legend2.setForm(Legend.LegendForm.CIRCLE); // 设置图例样式(圆形)
        legend2.setTextSize(10f); //设置图例文字大小
        legend2.setOrientation(Legend.LegendOrientation.HORIZONTAL); // 设置图例垂直显示
        legend2.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER); // 图例位置
        legend2.setXEntrySpace(10f);
        legend2.setYEntrySpace(5f);
        legend2.setTextColor(Color.GRAY);
        //// 和四周相隔一段距离,显示数据
        pieChart.setData(getPieData());
        // 点击事件
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                Toast.makeText(sumchar.this, "" + entry.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    private PieData getPieData() {
        ArrayList<PieEntry> pieEntries2 = new ArrayList<>();
        Legend legend  =pieChart.getLegend(); // 得到图例
        legend.setForm(Legend.LegendForm.SQUARE); // 图例样式


        //  饼图数据
        float data1 = 776787;
        float data2 = 884283;
        float data3 = 555734;
        float data4 = 248465;
        pieEntries2.add(new PieEntry(data1, "日用支出"));
        pieEntries2.add(new PieEntry(data2, "娱乐支出"));
        pieEntries2.add(new PieEntry(data3, "出行支出"));
        pieEntries2.add(new PieEntry(data4, "教育支出"));
        PieDataSet dataSet = new PieDataSet(pieEntries2, "日用支出");
        dataSet.setValueTextSize(12f); // 图表数值文字大小
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
