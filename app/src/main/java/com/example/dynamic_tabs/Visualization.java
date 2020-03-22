package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Visualization extends AppCompatActivity {

    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);

        lineChart = (LineChart)findViewById(R.id.Linechart);



        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(1,60f));
        yValues.add(new Entry(2,50f));
        yValues.add(new Entry(3,70f));
        yValues.add(new Entry(4,30f));
        yValues.add(new Entry(5,80f));
        yValues.add(new Entry(6,40f));
        yValues.add(new Entry(7,50f));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set1");

        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataset = new ArrayList<>();
        dataset.add(set1);

        LineData data = new LineData(dataset);
        lineChart.setData(data);
    }


}
