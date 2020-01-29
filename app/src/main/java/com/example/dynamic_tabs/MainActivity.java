package com.example.dynamic_tabs;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dynamic_tabs.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    TabLayout tabs;
    ViewPager viewPager;
    int k=0;
    ArrayList<Integer> titles=new ArrayList<Integer>();

    SectionsPagerAdapter sectionsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles.add(R.string.tab_text_1);
        titles.add(R.string.tab_text_2);

        titles.add(R.string.tab_text_3);

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

       TabLayout.Tab a=tabs.newTab();
       titles.add(R.string.tab_text_4);

       sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),titles);
       viewPager.setAdapter(sectionsPagerAdapter);

       tabs.setupWithViewPager(viewPager);
       a.setText("Tab"+k++);
   }


}