package com.example.dynamic_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import com.example.dynamic_tabs.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
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
    String tab_name;
    SectionsPagerAdapter sectionsPagerAdapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //#################################################################
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //######################################################################


        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Database_test data_test=new Database_test(getApplicationContext());
        Cursor all_rooms=data_test.fetch_all_rooms();
        while(all_rooms.moveToNext())
        {
            tab_name= all_rooms.getString(1);
            sectionsPagerAdapter.AddFragment(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount()+1),tab_name);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs.setupWithViewPager(viewPager);

            if(tab_name.equals("hall"))
              data_test.insert_in_room("Anirudh","Good","Educational",R.drawable.b1,tab_name);
            if(tab_name.equals("bedroom"))
                data_test.insert_in_room("Vaibhav","Bad","Porn",R.drawable.b2,tab_name);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//##########################################################################################################
   public void create_tabs()
   {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Context context=getApplicationContext();
        builder.setTitle("Title");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            tab_name= input.getText().toString();
            Database_test data_test=new Database_test(getApplicationContext());
            data_test.create_room(tab_name,sectionsPagerAdapter.getCount()+1);
            sectionsPagerAdapter.AddFragment(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount()+1),tab_name);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs.setupWithViewPager(viewPager);

            viewPager.setCurrentItem(sectionsPagerAdapter.getCount()-1);

            Toast.makeText(getApplicationContext(),"selected tab"+(sectionsPagerAdapter.getCount()-1),
                    Toast.LENGTH_SHORT).show();
        }});
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
             public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
         });
        builder.show();
   }
   //#####################################################################################################


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.add_device)
            create_tabs();

        if(id==R.id.settings)
            Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();

        return true;
    }
}



