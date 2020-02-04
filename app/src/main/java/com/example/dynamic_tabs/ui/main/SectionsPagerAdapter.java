package com.example.dynamic_tabs.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dynamic_tabs.MainActivity;
import com.example.dynamic_tabs.R;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static int k=0;

    private  ArrayList<String> TAB_TITLES=new ArrayList<>();
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, ArrayList<String> titles) {
        super(fm);
        mContext = context;
        if(titles!=null) {
            for (int i = 0; i < titles.size(); i++) {
                TAB_TITLES.add(titles.get(i));

            }
        }


    }







    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        if(TAB_TITLES==null)
            return 0;
        return TAB_TITLES.size();
    }
    public void add_tab(String tab_title)
    {
        if(TAB_TITLES!=null)
        {
            TAB_TITLES.add(tab_title);
            notifyDataSetChanged();
        }
    }
}