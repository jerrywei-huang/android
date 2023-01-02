package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class charthomepage extends AppCompatActivity implements View.OnClickListener {

    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;

    /*Author：HuangWei1824231127
      Date：2020.6.13
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_homepage);
        initView();
    }

    private void initView() {
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);

        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4 = (Button) findViewById(R.id.but4);
        but4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                startActivity(new Intent(this, LineChartActivity.class));
                break;
            case R.id.but2:
                startActivity(new Intent(this, PieChartActivity.class));
                break;
            case R.id.but3:
                startActivity(new Intent(this, Histogram.class));
                break;
            case R.id.but4:
                startActivity(new Intent(this, sumchar.class));
                break;
        }
    }
}
