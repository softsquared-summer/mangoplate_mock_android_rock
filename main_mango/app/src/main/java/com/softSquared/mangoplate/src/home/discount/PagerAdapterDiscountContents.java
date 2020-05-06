package com.softSquared.mangoplate.src.home.discount;


import android.content.Context;

import com.softSquared.mangoplate.src.home.discount.eat_deal.FragmentEatDeal;
import com.softSquared.mangoplate.src.home.discount.mangopickstory.FragmentMangopickStory;
import com.softSquared.mangoplate.src.home.discount.toplist.FragmentTopList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class PagerAdapterDiscountContents extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ViewPager mViewPager;
    private PagerAdapterDiscountContents mContentPagerAdapter;
    private Context mContext;

    public PagerAdapterDiscountContents(FragmentManager fm, int pageCount) {

        super(fm);
        this.mPageCount = pageCount;

    }


    @Override

    public Fragment getItem(int position) {
        switch (position) {

            case 0:

                FragmentEatDeal fragmentEatDeal = new FragmentEatDeal();
                return fragmentEatDeal;

            case 1:

                FragmentMangopickStory fragmentMangopickStory = new FragmentMangopickStory();
                return fragmentMangopickStory;

            case 2:
                FragmentTopList fragmentTopList = new FragmentTopList();
                return fragmentTopList;


            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mPageCount;
    }

}