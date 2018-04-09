package com.a5am.team.buglobalmusicfestival;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by sophiahon on 4/3/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String title[] = {"Main Calendar", "Personal Planner"};

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new CalanderFragment();
        }
        else if (position ==1){
            return TabFragment.getInstance(position);
        }
        return TabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
