package com.example.dynamic_tabs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

public class book_act extends AppCompatActivity{

    int seconds;
    private TextView type,ack_val;
    ImageView imageView;
    Button button;
    SeekBar seekBar;


    TimePicker timePicker;
    Button setAlarm;

    DeviceObject deviceObject;
    JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_act);
        //###########################################
        Intent intent = getIntent();
         deviceObject = intent.getParcelableExtra("DeviceObject");
         object=new JSONObject();
        try {
            object.put("_id", deviceObject.getId());
            object.put("type", deviceObject.getType());
            object.put("time", deviceObject.getTime());
            object.put("topic", deviceObject.getTopic());
            object.put("start", deviceObject.getStart());
            object.put("end", deviceObject.getClose());
            object.put("message", deviceObject.getCommand());
            object.put("from", deviceObject.getSource());
            object.put("Watt", deviceObject.getWatt());
            object.put("duty_cycle", deviceObject.getDuty());
            object.put("category", deviceObject.getCategory());
            object.put("ack_val", deviceObject.getAck_val());

        }catch(JSONException e){}

        if(Integer.parseInt(deviceObject.getClose())<Integer.parseInt(deviceObject.getStart()))
            seconds = Integer.parseInt(deviceObject.getStart());
         else
             seconds = Integer.parseInt(deviceObject.getClose())-Integer.parseInt(deviceObject.getStart());
        int hours = (seconds%86400)/3600;
        int minutes = (((seconds%86400))%3600)/60;
    //######################################################################################
        type = (TextView)findViewById(R.id.booktitle);
        imageView = (ImageView)findViewById(R.id.bookimg);
        button = (Button)findViewById(R.id.publish);
        seekBar = (SeekBar)findViewById(R.id.SeekBar);
        ack_val = (TextView) findViewById(R.id.ack_val);


        timePicker = (TimePicker)findViewById(R.id.timepicker);
        setAlarm = (Button)findViewById(R.id.setAlarm);

        if(Integer.parseInt(deviceObject.getClose())>Integer.parseInt(deviceObject.getStart()))
            type.setText(deviceObject.getType()+"\nRunTime:"+hours+"hr "+minutes+" min");
        else
            type.setText(deviceObject.getType()+"\nDevice On:"+hours+"hr "+minutes+" min");
        imageView.setImageResource(deviceObject.getThumbnail());

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout)findViewById(R.id.glare);
        ShimmerFrameLayout shimmerFrameLayout1 = (ShimmerFrameLayout)findViewById(R.id.imageglare);
        shimmerFrameLayout.startShimmerAnimation();
        shimmerFrameLayout1.startShimmerAnimation();

    //####################################################################################
        button.setVisibility(View.GONE);
        ack_val.setVisibility(View.GONE);

        if(deviceObject.getCategory().equals("sensor")){
            button.setText("Send");
            seekBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            ack_val.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.GONE);
            setAlarm.setVisibility(View.GONE);
        }

        if(deviceObject.getType().equals("fan"))
        { seekBar.setMax(5);}
        //######################################################################################

        final MqttAndroidClient mqttAndroidClient  = Mqtt_class.getClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"in publish",Toast.LENGTH_SHORT).show();
                try{
                    object.put("message","send");
                    mqttAndroidClient.publish(deviceObject.getCategory(),new MqttMessage(String.valueOf(object).getBytes()));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Book:"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                try{

                    object.put("message",progress);
                    mqttAndroidClient.publish(deviceObject.getCategory(),new MqttMessage(String.valueOf(object).getBytes()));

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Book:"+e.toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        try {
            mqttAndroidClient.subscribe("mobile", 1);
        }catch (MqttException e){}
        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                JSONObject reader=new JSONObject(message.toString());
                //Toast.makeText(getApplicationContext(),"indihjggk",Toast.LENGTH_SHORT).show();
                if(deviceObject.getId().equals(reader.getString("_id")))
                {
                    ack_val.setText("Value: "+reader.getString("ack_val"));
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

    //###########################################################################################################################################################

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0);
                }
                else{
                    calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0);
                }

                SetAlarm(calendar.getTimeInMillis());

            }
        });
        //###############################################################################################################################################
    }



    private void SetAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,PublishMessage.class);
        try {
            if(deviceObject.getType().equals("fan"))
                object.put("message","5");
            else
                object.put("message", "1024");

        }catch (Exception e){}
        intent.putExtra("topic",deviceObject.getCategory());
        intent.putExtra("message",String.valueOf(object));


        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(getApplicationContext(),String.valueOf(object),Toast.LENGTH_SHORT).show();
    }


}
