package com.example.mangoplate.src.home.search_restaurant.searchTab_layout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import static com.facebook.FacebookSdk.getApplicationContext;

//https://ghj1001020.tistory.com/9 여기 확인해서 완성할 것 .
public class SearchTabLayout extends BaseActivity {
    private TabLayout mTabLayout;
    private Context mContext;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tab_layout);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.

        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_TOP);
        mTabLayout.addTab(mTabLayout.newTab().setText("최근지역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("내 주변"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강남"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강북"));
        mViewPager = (ViewPager) findViewById(R.id.pager_contents);
//        mViewPager.setOffscreenPageLimit(4);
        mContentPagerAdapter = new ContentsPagerAdapter(
                getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mContentPagerAdapter);
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

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
        finish();
        //액티비티 애니메이션 x
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            System.out.println("TOuch outside the dialog ******************** ");
            finish();
        }
        return false;
    }


}
//        getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) ) ;
////
////        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
////                WindowManager.LayoutParams.WRAP_CONTENT);
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().getAttributes().width = display.getWidth();
//        mViewPager = (ViewPager) findViewById(R.id.pager_contents);
//        mViewPager.setOffscreenPageLimit(4);
//        mContentPagerAdapter = new ContentsPagerAdapter(
//                getSupportFragmentManager(), mTabLayout.getTabCount());
//        mViewPager.setAdapter(mContentPagerAdapter);
//        mViewPager.addOnPageChangeListener(
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//
//    }
//}
