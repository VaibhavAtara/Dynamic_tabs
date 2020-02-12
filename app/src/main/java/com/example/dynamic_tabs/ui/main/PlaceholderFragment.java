package com.example.dynamic_tabs.ui.main;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_tabs.Database_test;
import com.example.dynamic_tabs.DeviceObject;
import com.example.dynamic_tabs.R;
import com.example.dynamic_tabs.Book;
import com.example.dynamic_tabs.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class PlaceholderFragment extends Fragment {
    List<DeviceObject> mydata;
    Button add;
    RecyclerViewAdapter recyclerViewAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private  int tab_index;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            tab_index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        add = (Button)root.findViewById(R.id.add);

        Database_test data_test=new Database_test(root.getContext());
        String room_name="";
        Cursor fetch_room_name=data_test.fetch_room_name(tab_index);
        if(fetch_room_name.moveToNext())
            room_name=fetch_room_name.getString(1);

        Cursor cursor = data_test.get_room_data(room_name);

        mydata = new ArrayList<>();

        while(cursor.moveToNext())
        {
            DeviceObject deviceObject=new DeviceObject();
            Cursor cursor1 =data_test.fetch_device(cursor.getString(0));
            cursor1.moveToFirst();
            deviceObject.setId(cursor1.getString(0));
            deviceObject.setType(cursor1.getString(1));
            deviceObject.setTime(cursor1.getString(2));
            deviceObject.setTopic(cursor1.getString(3));
            deviceObject.setStart(cursor1.getString(4));
            deviceObject.setClose(cursor1.getString(5));
            deviceObject.setCommand(cursor1.getString(6));
            deviceObject.setSource(cursor1.getString(7));
            deviceObject.setWatt(cursor1.getString(8));
            deviceObject.setDuty(cursor1.getString(9));
            deviceObject.setThumbnail(cursor1.getInt(10));


            mydata.add(deviceObject);
        }


        final RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(root.getContext(),mydata);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        recyclerView.setAdapter(recyclerViewAdapter);


        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydata.add(new Book("10","10","10",R.drawable.b10));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });*/
        return root;
    }

    @Override
    public void onResume() {

        recyclerViewAdapter.notifyDataSetChanged();
        super.onResume();
    }
}