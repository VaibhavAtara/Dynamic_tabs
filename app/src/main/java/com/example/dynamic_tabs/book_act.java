package com.example.dynamic_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class book_act extends AppCompatActivity {

    private TextView type,ack_val;
    ImageView imageView;
    Button button;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_act);

        Intent intent = getIntent();
        final DeviceObject deviceObject = intent.getParcelableExtra("DeviceObject");


    //######################################################################################
        type = (TextView)findViewById(R.id.booktitle);
        imageView = (ImageView)findViewById(R.id.bookimg);
        button = (Button)findViewById(R.id.publish);
        seekBar = (SeekBar)findViewById(R.id.SeekBar);
        ack_val = (TextView) findViewById(R.id.ack_val);


        type.setText(deviceObject.getType());
        imageView.setImageResource(deviceObject.getThumbnail());
    //####################################################################################
        button.setVisibility(View.GONE);
        ack_val.setVisibility(View.GONE);

        if(deviceObject.getCategory().equals("sensor")){
            button.setText("Send");
            seekBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            ack_val.setVisibility(View.VISIBLE);
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
                    mqttAndroidClient.publish(deviceObject.getTopic(),new MqttMessage("send".getBytes()));
                }catch (MqttException e){
                    Toast.makeText(getApplicationContext(),"Book:"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                try{
                    mqttAndroidClient.publish(deviceObject.getTopic(),new MqttMessage(String.valueOf(progress).getBytes()));
                }catch (MqttException e){
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
    }
}
