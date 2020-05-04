package com.example.mangoplate.src.home.search_restaurant.searchTab_layout;




import android.content.Context;

import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.mylocation_search.MyLocationSearch;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.recent_location.RecentLocation;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_north.SeoulNorth;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.SeoulSouth;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class SeoulSouthContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ViewPager mViewPager;
    private SeoulSouthContentsPagerAdapter mContentPagerAdapter;
    private Context mContext;
    public SeoulSouthContentsPagerAdapter(FragmentManager fm, int pageCount, Context context) {

        super(fm);
        mContext=context;
        this.mPageCount = pageCount;

    }


    @Override

    public Fragment getItem(int position) {
        switch (position) {

            case 0:

            RecentLocation recentLocation = new RecentLocation();
            return recentLocation;

            case 1:

                MyLocationSearch myLocationSearch = new MyLocationSearch();
                return myLocationSearch;


            case 2:
                SeoulSouth seoulSouth = new SeoulSouth((SearchTabLayout) mContext);
                return seoulSouth;

            case 3:
                SeoulNorth seoulNorth = new SeoulNorth();
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