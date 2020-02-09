package com.example.dynamic_tabs;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
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
import org.json.JSONObject;

public class Mqtt_class extends AsyncTask<Void,Void,Void> {

    String username;
    byte[] password;
    String link;
    Context context;
    DeviceObject deviceObject=new DeviceObject();

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
                        String message="{"+ "\"serial\"" +":"+ Build.SERIAL+","+
                            "\"manufacturer\""+ ":"+ Build.MANUFACTURER+","+ "\"type\"" +":"+ "5"+","+ "\"topic\"" +":"+"101"+","+
                            "start"+ ":"+"1581139241.9628208"+","+ "\"end\"" +":"+ "1581150998.1089"+","+ "\"message\"" +":"+ "ON"+","+ "\"from\""+":"+ "mobile"+","+
                                "\"Watt\""+":"+"10"+","+"\"duty_cycle\""+":"+"10}";
                        client.publish("conf", new MqttMessage(message.getBytes()));
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
                                JSONObject reader=new JSONObject(message.toString());

                                deviceObject.setId(reader.getString("_id"));
                                deviceObject.setType(reader.getString("type"));
                                deviceObject.setTime(reader.getString("time"));
                                deviceObject.setTopic(reader.getString("topic"));
                                deviceObject.setStart(reader.getString("start"));
                                deviceObject.setClose(reader.getString("end"));
                                deviceObject.setCommand(reader.getString("message"));
                                deviceObject.setSource(reader.getString("from"));
                                deviceObject.setWatt(reader.getString("Watt"));
                                deviceObject.setDuty(reader.getString("duty_cycle"));
                                deviceObject.setThumbnail(R.drawable.b1);
                                Database_test database_test=new Database_test(context);
                                boolean inserted=database_test.insert_devices(deviceObject);
                                database_test.close();


                                Toast.makeText(context,""+inserted,Toast.LENGTH_SHORT).show();


                                // enter into database here


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
