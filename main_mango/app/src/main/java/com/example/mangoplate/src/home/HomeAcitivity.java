package com.example.mangoplate.src.home;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import it.sephiroth.android.library.viewrevealanimator.ViewRevealAnimator;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.example.mangoplate.src.home.mystatus.MystatusFragment;
import com.example.mangoplate.src.home.news.DiscountFragment;
import com.example.mangoplate.src.home.news.NewsFragment;
import com.example.mangoplate.src.home.search_restaurant.SearchRestaurantFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class HomeAcitivity extends BaseActivity {

    private FragmentManager mfragmentManager = getSupportFragmentManager();
    ViewRevealAnimator mViewAnimator;
    private SearchRestaurantFragment mFragmentSearch_restaurant = new SearchRestaurantFragment();
    private DiscountFragment mFragmentDiscount = new DiscountFragment();
    private NewsFragment mFragmentNews = new NewsFragment();
    private MystatusFragment mystatus = new MystatusFragment();

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.
        setContentView(R.layout.acitivity_home);
        mViewAnimator = (ViewRevealAnimator) findViewById(R.id.animator);
         mFloatingActionButton=findViewById(R.id.home_floatingActionButton);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = mfragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, mFragmentSearch_restaurant).commitAllowingStateLoss();
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mViewAnimator.showNext();

                anim();


//                mViewAnimator.showNext();
            }



        });

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

    public void anim() {

        if (isFabOpen) {

            Log.e("어디지?",""+(int)mFloatingActionButton.getY());
            mViewAnimator.setDisplayedChild(mViewAnimator.getDisplayedChild()+1, false, new Point((int)mFloatingActionButton.getX(), (int)mFloatingActionButton.getY()));

            final OvershootInterpolator interpolator = new OvershootInterpolator();
            ViewCompat.animate(mFloatingActionButton).
                    rotation(0.0F).
                    withLayer().
                    setDuration(300).
                    setInterpolator(interpolator).
                    start();

            isFabOpen = false;

        } else {
//            mViewAnimator.setDisplayedChild(mViewAnimator.getDisplayedChild()+1, true, new Point((int)mFloatingActionButton.getX(), 500));
            // 버튼 위치를 정확히 구해서 하면 되긴해 .

            mFloatingActionButton.setBackgroundColor(00000);
            final OvershootInterpolator interpolator = new OvershootInterpolator();
            ViewCompat.animate(mFloatingActionButton).
                    rotation(135f).
                    withLayer().
                    setDuration(300).
                    setInterpolator(interpolator).
                    start();
            isFabOpen = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Log.e("되긴되니?","ㅇ?");
                Toast.makeText(HomeAcitivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
//                                redirectLoginActivity();
            }
        });
    }
//     adapter의 값이 변경되었다는 것을 알려줍니다.
//        madapter.notifyDataSetChanged();
    }




