package com.example.mangoplate.src.home.discount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.RestaurantRecyclerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class FragmentDiscount extends Fragment {
    private TabLayout mTabLayout;
    private Context mContext;
    private ViewPager mViewPager;
    private PagerAdapterDiscountContents mPagerAdapterDiscountContents;
    ViewGroup mRootView;
    HomeAcitivity mHomeAcitivity;
    Toolbar mAppToolbar;
    RecyclerView mRecyclerView;

    private GridLayoutManager mGridLayoutManager;

    private RestaurantRecyclerAdapter madapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mContext = context;
        mHomeAcitivity = (HomeAcitivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_discount, container, false);
        setmViewPager();


        return mRootView;
    }


    void setmViewPager(){ // EAT딜 , 망고픽 스토리 ,TOP리스트 표시하는 뷰 페이저

        mTabLayout = mRootView.findViewById(R.id.fragment_tab_discount);
        mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM);
        mTabLayout.addTab(mTabLayout.newTab().setText("EAT딜"));
        mTabLayout.addTab(mTabLayout.newTab().setText("망고픽 스토리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TOP 리스트"));
        mViewPager = mRootView.findViewById(R.id.viewPager_discount);
        mPagerAdapterDiscountContents = new PagerAdapterDiscountContents(
                mHomeAcitivity.getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapterDiscountContents);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
