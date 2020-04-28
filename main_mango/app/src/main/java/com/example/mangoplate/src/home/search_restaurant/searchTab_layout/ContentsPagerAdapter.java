package com.example.mangoplate.src.home.search_restaurant.searchTab_layout;




import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.mylocation_search.MyLocationSearch;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.recent_location.RecentLocation;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_north.SeoulNorth;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.SeoulSouth;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;
    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {

        super(fm);

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
                SeoulNorth seoulNorth = new SeoulNorth();
                return seoulNorth;


            case 3:
                SeoulSouth seoulSouth = new SeoulSouth();
                return seoulSouth;
                default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mPageCount;
    }

}