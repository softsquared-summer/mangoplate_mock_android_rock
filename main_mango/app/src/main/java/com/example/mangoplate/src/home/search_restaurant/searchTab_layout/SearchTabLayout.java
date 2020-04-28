package com.example.mangoplate.src.home.search_restaurant.searchTab_layout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.google.android.material.tabs.TabLayout;

public class SearchTabLayout extends BaseActivity {
    private TabLayout mTabLayout;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tab_layout);
        mContext = getApplicationContext();
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_TOP);
        mTabLayout.addTab(mTabLayout.newTab().setText("최근지역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("내 주변"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강남"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강북"));


    }
}
