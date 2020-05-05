package com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout;

import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

//https://ghj1001020.tistory.com/9 여기 확인해서 완성할 것 .
public class LocalSearchTabLayout extends BaseActivity implements View.OnClickListener{
    private TabLayout mTabLayout;
    private Context mContext;
    private ViewPager mViewPager;
    private LocalSearchContentsPagerAdapter mContentPagerAdapter;
   public static int mAdjustmnetColorChanger = 0;// 하나라도 체크 되면 적용 주황색 표시
    public static ArrayList<String> outputDatas=new ArrayList<>();// 체크된 모든값 스트링 저장.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tab_layout);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.

        final TextView adjustment = findViewById(R.id.adjustment); // 클릭시 버튼 색깔 바꾸기
        adjustment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAdjustmnetColorChanger==0){
                    return;

                }else{
                    Intent intent = new Intent();
                    intent.putExtra("result", "3");
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }
        });
        final TextView cancelAllbutton =findViewById(R.id.cancel_allbutton);
        cancelAllbutton.setTextColor(getResources().getColor(R.color.offborder));
        adjustment.setBackgroundResource(R.drawable.adjustment_off_rouned_border_textview);
        adjustment.setTextColor(getResources().getColor(R.color.white));
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_TOP);
        mTabLayout.addTab(mTabLayout.newTab().setText("최근지역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("내 주변"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강남"));
        mTabLayout.addTab(mTabLayout.newTab().setText("서울-강북"));
        mViewPager = (ViewPager) findViewById(R.id.pager_contents);
//        mViewPager.setOffscreenPageLimit(4);
        mContentPagerAdapter = new LocalSearchContentsPagerAdapter(
                getSupportFragmentManager(), mTabLayout.getTabCount(),this);
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
        mAdjustmnetColorChanger = 0;
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


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.adjustment:
               if(mAdjustmnetColorChanger==0){
                   return;

               }else{
                   finish();


               }

            break;
        }
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
