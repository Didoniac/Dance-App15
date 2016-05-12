package com.example.umyhpuscdi.danceapp15;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by umyharumdh on 2016-04-27.
 */
public class AdminDetailActivity extends AppCompatActivity {

    protected Course course;
    protected Info_TabView info_tabView;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);

        id = getIntent().getIntExtra("id",-1);

        if (id != -1) {

            AsyncCourse asyncCourse = new AsyncCourse(this, null, 0);
            asyncCourse.execute("GET", "lists/" + "2" + "/tasks/", "" + id);
        }

        //Adding Fragment java class here...
        List<Fragment> fragmentlist_tabview = new Vector<>();
        info_tabView = (Info_TabView) Fragment.instantiate(this,Info_TabView.class.getName());
        fragmentlist_tabview.add(info_tabView);
        fragmentlist_tabview.add(Fragment.instantiate(this,Participant_TabView.class.getName()));

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),fragmentlist_tabview);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.admin_detail_viewpager);
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
             actionBar.addTab(actionBar.newTab().setText("Deltagare").setTabListener(tabListener));


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
