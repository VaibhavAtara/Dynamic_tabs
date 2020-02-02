package com.example.dynamic_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dynamic_tabs.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    TabLayout tabs;
    ViewPager viewPager;
    int k=0;
    String tab_name;
    ArrayList<String> titles=new ArrayList<String>();

    SectionsPagerAdapter sectionsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles.add("Tab1");
        titles.add("Tab2");

        titles.add("Tab3");

        k=titles.size()+1;
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),titles);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

   public void create_tabs(View view)
   {
       //**************************************************************
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Context context=getApplicationContext();
        builder.setTitle("Title");
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText("hello");
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            tab_name= input.getText().toString();
            TabLayout.Tab new_tab=tabs.newTab();
            titles.add(tab_name);
            Toast.makeText(getApplicationContext(),""+tab_name,Toast.LENGTH_LONG).show();

            sectionsPagerAdapter = new SectionsPagerAdapter(context, getSupportFragmentManager(),titles);

            viewPager.setAdapter(sectionsPagerAdapter);

            tabs.setupWithViewPager(viewPager);
            new_tab.setText(""+tab_name);

            Database_test data_test=new Database_test(getApplicationContext());
            data_test.create_room(tab_name);

        }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });

    builder.show();
    //Toast.makeText(getApplicationContext(),""+tab_name,Toast.LENGTH_LONG).show();


   }


}