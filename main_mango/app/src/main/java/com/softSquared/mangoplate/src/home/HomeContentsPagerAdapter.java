package com.softSquared.mangoplate.src.home;


import com.softSquared.mangoplate.src.home.mystatus.MystatusFragment;
import com.softSquared.mangoplate.src.home.discount.FragmentDiscount;
import com.softSquared.mangoplate.src.home.news.NewsFragment;
import com.softSquared.mangoplate.src.home.search_restaurant.SearchRestaurantFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class HomeContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ViewPager mViewPager;
    private HomeContentsPagerAdapter mContentPagerAdapter;

    public HomeContentsPagerAdapter(FragmentManager fm, int pageCount) {

        super(fm);

        this.mPageCount = pageCount;

    }


    @Override

    public Fragment getItem(int position) {
        switch (position) {

            case 0:

                SearchRestaurantFragment searchRestaurantFragment = new SearchRestaurantFragment();
                return searchRestaurantFragment;

            case 1:

                FragmentDiscount fragmentDiscount = new FragmentDiscount();
                return fragmentDiscount;

            case 2:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;


            case 3:
                MystatusFragment mystatusFragment = new MystatusFragment();
                return mystatusFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mPageCount;
    }

}