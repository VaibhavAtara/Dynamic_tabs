package com.example.dynamic_tabs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Mqtt_class extends AsyncTask<Void,Void,Void> {

    String username;
    byte[] password;
    String link;
    Context context;

    public Mqtt_class(Context context) {
        this.context = context;
    }

    public Mqtt_class(String username, byte[] password, String link, Context context) {
        this.username = username;
        this.password = password;
        this.link = link;
        this.context = context;
    }

    public MqttAndroidClient get_connection() {
        final MqttAndroidClient client = new MqttAndroidClient(context
                , "tcp://tailor.cloudmqtt.com:13968", MqttClient.generateClientId());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        options.setUserName("ghleymma");
        options.setPassword("jmvoCCetDGiy".toCharArray());

        try {
            IMqttToken token = client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    try {
                        client.publish("conf", new MqttMessage("{}".getBytes()));
                    } catch (MqttException e) {

                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });


        } catch (MqttException e) {

        }

        return client;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final MqttAndroidClient client = new MqttAndroidClient(context
                , "tcp://tailor.cloudmqtt.com:13968", MqttClient.generateClientId());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        options.setUserName("ghleymma");
        options.setPassword("jmvoCCetDGiy".toCharArray());

        try {
            IMqttToken token = client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    try {

                        client.subscribe("mobile",1);
                        client.setCallback(new MqttCallback() {
                            @Override
                            public void connectionLost(Throwable cause) {

                            }

                            @Override
                            public void messageArrived(String topic, MqttMessage message) throws Exception {

                                Toast.makeText(context,message.toString(),Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void deliveryComplete(IMqttDeliveryToken token) {

                            }
                        });



                    } catch (MqttException e) {

                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });


        } catch (MqttException e) {

        }




        return null;
    }
}
