package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LineChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechartactivity);
        initView();
    }
    private void initView() {
        lineChart = (LineChart) findViewById(R.id.linechart);
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

}
