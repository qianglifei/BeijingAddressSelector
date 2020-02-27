package com.bksx.mobile.myapplication;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> tabTitle;


    public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void setTabTitle(List<String> tabTitle) {
        this.tabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return new MyDialogFragment().tabTitle[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }
}
