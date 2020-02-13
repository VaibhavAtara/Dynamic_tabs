package com.example.dynamic_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class book_act extends AppCompatActivity {

    private TextView title,des,cat;
    ImageView imageView;
    Button button;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_act);

        Intent intent = getIntent();
        final DeviceObject deviceObject = intent.getParcelableExtra("DeviceObject");
        String Title = deviceObject.getType();
        String Des = deviceObject.getCommand();
        final String Cat = deviceObject.getTopic();
        int Thumbnail = deviceObject.getThumbnail();

        title = (TextView)findViewById(R.id.booktitle);
        des = (TextView)findViewById(R.id.des);
        cat = (TextView)findViewById(R.id.cat);
        imageView = (ImageView)findViewById(R.id.bookimg);
        button = (Button)findViewById(R.id.publish);
        seekBar = (SeekBar)findViewById(R.id.SeekBar);

        if(deviceObject.getType().equals("fan"))
        { // seekBar.setMin(0);

            seekBar.setMax(5);}
        Toast.makeText(getApplicationContext(),deviceObject.getType(),Toast.LENGTH_LONG).show();

        title.setText(Title);
        des.setText(Des);
        cat.setText(Cat);
        imageView.setImageResource(Thumbnail);
        //Mqtt_class mqtt_class = new Mqtt_class(getApplicationContext());
        final MqttAndroidClient mqttAndroidClient  = Mqtt_class.getClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"in publish",Toast.LENGTH_SHORT).show();
                try{
                    mqttAndroidClient.publish(title.getText().toString(),new MqttMessage(cat.getText().toString().getBytes()));
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




    }
}
