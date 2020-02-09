package com.example.dynamic_tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class Devices extends Fragment {

    List<Book> devices;
    View view;
    RecyclerView recyclerView;
    DeviceRecyclerViewAdapter deviceRecyclerViewAdapter;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activity_devices,container,false);
       recyclerView = (RecyclerView)view.findViewById(R.id.device_recyclerView);
       devices = new ArrayList<>();

        devices.add(new Book("2","2","2",R.drawable.b2));
        devices.add(new Book("3","3","3",R.drawable.b3));
        devices.add(new Book("4","4","4",R.drawable.b4));
        devices.add(new Book("5","5","5",R.drawable.b5));
        devices.add(new Book("6","6","6",R.drawable.b6));
        devices.add(new Book("7","7","7",R.drawable.b7));
        devices.add(new Book("8","8","8",R.drawable.b8));
        devices.add(new Book("9","9","9",R.drawable.b9));
        Mqtt_class mqtt_class = new Mqtt_class(view.getContext());
        final MqttAndroidClient client = mqtt_class.get_connection();






       deviceRecyclerViewAdapter = new DeviceRecyclerViewAdapter(view.getContext(),devices);
       recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
       recyclerView.setAdapter(deviceRecyclerViewAdapter);

        return view;
    }
}
