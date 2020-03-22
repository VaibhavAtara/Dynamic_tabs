package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChart1 extends AppCompatActivity {
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart1);

        barChart = (BarChart)findViewById(R.id.BarChart1);
        barChart.getDescription().setEnabled(false);

        setdata(10);
        barChart.setFitBars(true);
    }

    private void setdata(int count)
    {
        ArrayList<BarEntry> yValues = new ArrayList<>();

        for(int i=0;i<count;i++)
        {
            float value = (float)(Math.random()*100);
            yValues.add(new BarEntry(i,(int)value));
        }

        BarDataSet barDataSet = new BarDataSet(yValues,"Data Set");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(true);
        BarData data = new BarData(barDataSet);

        barChart.setData(data);
        barChart.invalidate();
       // barChart.animateY(2500, Easing.EasingOption.EaseInBounce);
       // barChart.animateX(1500,Easing.EasingOption.EaseInBounce);
        barChart.animateXY(2500,4500,Easing.EasingOption.EaseInBounce,Easing.EasingOption.EaseInBounce);
    }
}
