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
import com.example.dynamic_tabs.R;
import com.example.dynamic_tabs.Book;
import com.example.dynamic_tabs.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class PlaceholderFragment extends Fragment {
    List<Book> mydata;
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

        Cursor room_details = data_test.get_room_data(room_name);

        mydata = new ArrayList<>();

        while(room_details.moveToNext())
        {
            mydata.add(new Book(room_details.getString(0),
                    room_details.getString(1),room_details.getString(2)
                    ,room_details.getInt(3)));
        }
       /* mydata.add(new Book(room_name,"tab_index: "+tab_index,"1",R.drawable.b1));
        mydata.add(new Book("2","2","2",R.drawable.b2));
        mydata.add(new Book("3","3","3",R.drawable.b3));
        mydata.add(new Book("4","4","4",R.drawable.b4));
        mydata.add(new Book("5","5","5",R.drawable.b5));
        mydata.add(new Book("6","6","6",R.drawable.b6));
        mydata.add(new Book("7","7","7",R.drawable.b7));
        mydata.add(new Book("8","8","8",R.drawable.b8));
        mydata.add(new Book("9","9","9",R.drawable.b9));
        mydata.add(new Book("10","10","10",R.drawable.b10));*/

        final RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(root.getContext(),mydata);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        recyclerView.setAdapter(recyclerViewAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydata.add(new Book("10","10","10",R.drawable.b10));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
        return root;
    }

    @Override
    public void onResume() {

        recyclerViewAdapter.notifyDataSetChanged();
        super.onResume();
    }
}