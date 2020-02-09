package com.example.dynamic_tabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.MessageQueue;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class BottomNavigation extends AppCompatActivity {
    //############################################################################################

    Toolbar toolbar ;
    MqttAndroidClient client;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            //selectedFragment=new home_fragment();
                            selectedFragment = new MainActivity();
                            break;
                        case R.id.nav_profile:
                            selectedFragment=new Devices();
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new MainActivity();
                            subscribe(client);
                            break;


                    }
                    /*
                    * Now I am going to see how to refresh fragment
                    * add to back stack allows us to use back button to go to previous fragment
                     */

                   //getSupportFragmentManager().beginTransaction().detach(selectedFragment);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null)
                            .commit();
                    return true;
                }
            };

//###########################################################################################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainActivity())
                .commit();
    //####################################################################

      Mqtt_class mqtt_class = new Mqtt_class(getApplicationContext());

        mqtt_class.execute(new Void[0]);

      client = mqtt_class.get_connection();



        //subscribe(client);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.home:
                Toast.makeText(getApplicationContext(),"HOME",Toast.LENGTH_SHORT).show();
                Database_test database_test=new Database_test(getApplicationContext());
                Cursor cursor=database_test.fetch_devices();
                while(cursor.moveToNext())
                {
                    Toast.makeText(getApplicationContext(),"id: "+cursor.getString(0)+"topic:"+
                            cursor.getString(3),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.settings:
                try{
                    client.publish("vaibhav",new MqttMessage("settings".getBytes()));
                }
                catch (MqttException e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"HOME",Toast.LENGTH_SHORT).show();
                break;

            default:   return super.onOptionsItemSelected(item);
        }

        return  true;

    }

    public void subscribe(MqttAndroidClient client){

        try {
           IMqttToken token = client.subscribe("vaibhav", 1);
           token.setActionCallback(new IMqttActionListener() {
               @Override
               public void onSuccess(IMqttToken asyncActionToken) {
                   Toast.makeText(BottomNavigation.this,"subscribed successfully",Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                   Toast.makeText(BottomNavigation.this,"not subscribed successfully",Toast.LENGTH_SHORT).show();

               }
           });

        }catch (MqttException e){
            Toast.makeText(BottomNavigation.this,e.toString(),Toast.LENGTH_SHORT).show();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Toast.makeText(BottomNavigation.this,message+":"+topic,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });


    }
}
