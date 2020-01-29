package com.example.dynamic_tabs.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_tabs.R;
import com.example.dynamic_tabs.Book;
import com.example.dynamic_tabs.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    List<Book> mydata;
    Button add;
    RecyclerViewAdapter recyclerViewAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

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
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }


        //**************************


        //********************





        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        /*final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        //**********************
        add = (Button)root.findViewById(R.id.add);

        mydata = new ArrayList<>();
        mydata.add(new Book("1","1","1",R.drawable.b1));
        mydata.add(new Book("2","2","2",R.drawable.b2));
        mydata.add(new Book("3","3","3",R.drawable.b3));
        mydata.add(new Book("4","4","4",R.drawable.b4));
        mydata.add(new Book("5","5","5",R.drawable.b5));
        mydata.add(new Book("6","6","6",R.drawable.b6));
        mydata.add(new Book("7","7","7",R.drawable.b7));
        mydata.add(new Book("8","8","8",R.drawable.b8));
        mydata.add(new Book("9","9","9",R.drawable.b9));
        mydata.add(new Book("10","10","10",R.drawable.b10));

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






//*****************************





        return root;
    }
}