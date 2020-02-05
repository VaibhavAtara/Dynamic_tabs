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
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<String> TitleList=new ArrayList<>();
    private List<Fragment> FragmentList = new ArrayList<>();
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TitleList.get(position);
    }

    @Override
    public int getCount() {
        return TitleList.size();
    }

    public void AddFragment(Fragment fragment,String title){
        FragmentList.add(fragment);
        TitleList.add(title);
        notifyDataSetChanged();
    }
}