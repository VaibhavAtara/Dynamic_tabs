package com.example.dynamic_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dynamic_tabs.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.dynamic_tabs.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends Fragment {
    TabLayout tabs;
    ViewPager viewPager;
    String tab_name;
    SectionsPagerAdapter sectionsPagerAdapter;
    Toolbar toolbar;



//##########################################################################################################
   public boolean create_tabs(View view)
   {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Title");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            tab_name= input.getText().toString();
            Database_test data_test=new Database_test(getContext());
            data_test.create_room(tab_name,sectionsPagerAdapter.getCount()+1);
            sectionsPagerAdapter.AddFragment(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount()+1),tab_name);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(sectionsPagerAdapter.getCount()-1);
            Toast.makeText(getContext(),"selected tab"+(sectionsPagerAdapter.getCount()-1),
                    Toast.LENGTH_SHORT).show();
        }});
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
             public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
         });
        builder.show();

       return true;
   }
   //#####################################################################################################







    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);

        //######################################################################

        sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getActivity().getSupportFragmentManager());
        viewPager = view.findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Database_test data_test=new Database_test(getContext());
        Cursor all_rooms=data_test.fetch_all_rooms();
        while(all_rooms.moveToNext())
        {
            tab_name= all_rooms.getString(1);
            sectionsPagerAdapter.AddFragment(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount()+1),tab_name);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs.setupWithViewPager(viewPager);

        }


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean added = create_tabs(view);
                Snackbar snackbar = Snackbar.make(view, "ROOM ADDED SUCCESSFULLY", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                snackbar.getView().setBackgroundColor(Color.parseColor("#fcde32"));

                if(added)
                    snackbar.show();

            }
        });
        return view;
    }
}



