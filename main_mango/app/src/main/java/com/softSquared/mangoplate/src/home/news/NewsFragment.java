package com.softSquared.mangoplate.src.home.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.search_restaurant.RestaurantRecyclerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class NewsFragment extends Fragment {
    private TabLayout mTabLayout;
    private Context mContext;
    private ViewPager mViewPager;
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
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_news, container, false);
        setmRootView(); // rootView 세팅.

        return mRootView;
    }

 void setmRootView()
 {

     mAppToolbar = mRootView.findViewById(R.id.app_toolbar);
     mTabLayout = (TabLayout) mRootView.findViewById(R.id.news_layout_tab);
     mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM);
     mTabLayout.addTab(mTabLayout.newTab().setText("전체"));
     mTabLayout.addTab(mTabLayout.newTab().setText("팔로잉"));
     mTabLayout.addTab(mTabLayout.newTab().setText("홀릭"));
     mViewPager = (ViewPager) mRootView.findViewById(R.id.newspager_contents);
//        mViewPager.setOffscreenPageLimit(4);

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
//         ToolBar를 ActionBar로 설정해줘야 합니다.
     ((AppCompatActivity) getActivity()).setSupportActionBar(mAppToolbar);


 }

}
