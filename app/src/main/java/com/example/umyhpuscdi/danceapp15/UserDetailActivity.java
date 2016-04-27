package com.example.umyhpuscdi.danceapp15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Vector;

/**
 * Created by umyharumdh on 2016-04-27.
 */
public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        //Adding Fragment java class here...
        List<Fragment> fragmentlist_tabview = new Vector<>();
        fragmentlist_tabview.add(Fragment.instantiate(this,Info_TabView.class.getName()));
        fragmentlist_tabview.add(Fragment.instantiate(this,Calender_TabView.class.getName()));

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),fragmentlist_tabview);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.user_detail_viewpager);
        viewPager.setAdapter(pageAdapter);

        // starts ActionBar Tablistener
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

        final ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        // ends ActionBar Tablistener

        // setting text to view in Tab
        actionBar.addTab(actionBar.newTab().setText("Info").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Kalender").setTabListener(tabListener));

        // starts ViewPager changeListener here....

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // ends ViewPager changeListener here....

    } // ends OnCreate
} //ends AdminDetailActivity
