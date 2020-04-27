package com.example.mangoplate.src.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.discount.Fragment_discount;
import com.example.mangoplate.src.home.mystatus.Fragment_mystatus;
import com.example.mangoplate.src.home.news.Fragment_news;
import com.example.mangoplate.src.home.search_restaurant.Fragment_searchRestaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAcitivity extends AppCompatActivity {

    private FragmentManager mfragmentManager = getSupportFragmentManager();

    private Fragment_searchRestaurant mFragmentSearch_restaurant = new Fragment_searchRestaurant();
    private Fragment_discount mFragmentDiscount = new Fragment_discount();
    private Fragment_news mFragmentNews = new Fragment_news();
    private Fragment_mystatus mystatus = new Fragment_mystatus();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.
        setContentView(R.layout.home_acitivity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = mfragmentManager.beginTransaction();

//        출처: https://mc10sw.tistory.com/16 [Make it possible]
        transaction.replace(R.id.frame_layout, mFragmentSearch_restaurant).commitAllowingStateLoss();


        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = mfragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.search_restaurant: {
                        transaction.replace(R.id.frame_layout, mFragmentSearch_restaurant).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.discount: {
                        transaction.replace(R.id.frame_layout, mFragmentDiscount).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.news: {
                        transaction.replace(R.id.frame_layout, mFragmentNews).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.mystatus: {
                        transaction.replace(R.id.frame_layout, mystatus).commitAllowingStateLoss();
                        break;
                    }

                }

                return true;
            }
        });


    }
}
