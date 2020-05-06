package com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout;


import android.content.Context;

import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.mylocation_search.MyLocationSearch;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.recent_location.RecentLocation;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_north.SeoulNorth;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.SeoulSouth;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class LocalSearchContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ViewPager mViewPager;
    private LocalSearchContentsPagerAdapter mContentPagerAdapter;
    private Context mContext;

    public LocalSearchContentsPagerAdapter(FragmentManager fm, int pageCount, Context context) {

        super(fm);
        mContext = context;
        this.mPageCount = pageCount;

    }


    @Override

    public Fragment getItem(int position) {
        switch (position) {

            case 0:

                RecentLocation recentLocation = new RecentLocation();
                return recentLocation;

            case 1:

                MyLocationSearch myLocationSearch = new MyLocationSearch((LocalSearchTabLayout) mContext);
                return myLocationSearch;


            case 2:
                SeoulSouth seoulSouth = new SeoulSouth((LocalSearchTabLayout) mContext);
                return seoulSouth;

            case 3:
                SeoulNorth seoulNorth = new SeoulNorth((LocalSearchTabLayout) mContext);
                return seoulNorth;

            default:

                return null;
        }
    }


    @Override
    public int getCount() {
        return mPageCount;
    }

}