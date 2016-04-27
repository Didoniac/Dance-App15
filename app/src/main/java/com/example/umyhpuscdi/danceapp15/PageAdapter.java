package com.example.umyhpuscdi.danceapp15;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by umyharumdh on 2016-04-27.
 */

// PageAdapter to view the fragments in a TabView (participant fragment, information fragment and calender fragment)....

    // PageAdapter is used in UserDetailActivity.java and AdminDetailActivity.java files.

public class PageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public PageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
