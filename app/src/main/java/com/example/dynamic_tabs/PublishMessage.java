package com.example.dynamic_tabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublishMessage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String topic=intent.getStringExtra("topic");
        String message=intent.getStringExtra("message");
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
        MqttAndroidClient mqttAndroidClient=Mqtt_class.getClient();
        try{
        Thread.sleep(1000);}
        catch(Exception e){}
        try {
            if(mqttAndroidClient!=null)
                mqttAndroidClient.publish(topic, new MqttMessage(message.getBytes()));
        }
        catch(MqttException e){
            Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(context,topic+": "+message,Toast.LENGTH_SHORT).show();
    }
}
