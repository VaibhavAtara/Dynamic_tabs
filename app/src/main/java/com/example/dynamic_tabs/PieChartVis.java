package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;

public class PieChartVis extends AppCompatActivity {

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = (PieChart)findViewById(R.id.PieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
       // pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.BLUE);
        pieChart.setTransparentCircleRadius(61f);


        //######################################################

        Database_test database_test = new Database_test(getApplicationContext());
        Cursor cursor = database_test.fetch_devices();

        while(cursor.moveToNext())
        {
           /* DeviceObject deviceObject = new DeviceObject();
            deviceObject.setId(cursor.getString(0));
            deviceObject.setType(cursor.getString(1));
            deviceObject.setTime(cursor.getString(2));
            deviceObject.setTopic(cursor.getString(3));
            deviceObject.setStart(cursor.getString(4));
            deviceObject.setClose(cursor.getString(5));
            deviceObject.setCommand(cursor.getString(6));
            deviceObject.setSource(cursor.getString(7));
            deviceObject.setWatt(cursor.getString(8));
            deviceObject.setDuty(cursor.getString(9));
            deviceObject.setThumbnail(cursor.getInt(10));*/


        }

        MongoCollection<Document> collection = Mqtt_class.getMongoDataBase();


       // MongoCollection<Document> collection = database.getCollection("devices");
        ArrayList<JSONObject> devices_list = new ArrayList<>();

        MongoCursor<Document> cur = collection.find().iterator();

        while (cur.hasNext()) {
            Document doc = cur.next();
            try {
                JSONObject jsonObject = new JSONObject(doc.toJson());
                devices_list.add(jsonObject);
            }catch(Exception e){}
        }
        //######################################################




        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"A"));

        yValues.add(new PieEntry(44f,"B"));
        yValues.add(new PieEntry(52f,"C"));
        yValues.add(new PieEntry(20f,"D"));
        yValues.add(new PieEntry(67f,"E"));

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCirc);
        Description description = new Description();
        description.setText("This is Description");
        description.setTextSize(15f);
        pieChart.setDescription(description);


        PieData data = new PieData(dataSet);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(10f);

        pieChart.setData(data);

    }
}
