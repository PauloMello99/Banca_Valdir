package com.example.paulomello.banca_valdir.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private Context context;
    private ViewPager viewPager;

    public ViewPagerAdapter(FragmentManager fragmentManager, ViewPager viewPager,Context context) {
        super(fragmentManager);
        this.viewPager = viewPager;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) { return fragmentList.get(position); }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
        notifyDataSetChanged();
    }

    public void removeFragment(int position){
        destroyItem(null,position,fragmentList.get(position));
        fragmentList.remove(position);
        titleList.remove(position);
        notifyDataSetChanged();
        //viewPager.setCurrentItem(-1,false);
    }
}
