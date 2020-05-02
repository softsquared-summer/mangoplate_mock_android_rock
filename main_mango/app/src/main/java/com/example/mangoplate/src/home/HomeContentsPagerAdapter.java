package com.example.mangoplate.src.home;




import com.example.mangoplate.src.home.mystatus.MystatusFragment;
import com.example.mangoplate.src.home.news.DiscountFragment;
import com.example.mangoplate.src.home.news.NewsFragment;
import com.example.mangoplate.src.home.search_restaurant.SearchRestaurantFragment;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.mylocation_search.MyLocationSearch;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.recent_location.RecentLocation;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_north.SeoulNorth;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.SeoulSouth;

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

                DiscountFragment discountFragment = new DiscountFragment();
                return discountFragment;

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