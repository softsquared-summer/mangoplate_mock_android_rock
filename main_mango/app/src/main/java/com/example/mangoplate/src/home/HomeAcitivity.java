package com.example.mangoplate.src.home;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
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
import com.example.mangoplate.src.home.interfaces.HomeActivityView;
import com.example.mangoplate.src.home.mystatus.MystatusFragment;
import com.example.mangoplate.src.home.news.DiscountFragment;
import com.example.mangoplate.src.home.news.NewsFragment;
import com.example.mangoplate.src.home.search_restaurant.SearchRestaurantFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class HomeAcitivity extends BaseActivity implements HomeActivityView {

    private FragmentManager mfragmentManager = getSupportFragmentManager();
    private ViewRevealAnimator mViewAnimator;
    private SearchRestaurantFragment mFragmentSearch_restaurant = new SearchRestaurantFragment();
    private DiscountFragment mFragmentDiscount = new DiscountFragment();
    private NewsFragment mFragmentNews = new NewsFragment();
    private MystatusFragment mystatus = new MystatusFragment();

    private Animation mFab_open, mFab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton mFloatingActionButton;
    private HomeService mhomeService;
    private ViewPager mHomeViewPager;
    HomeContentsPagerAdapter mHomeContentsPagerAdapter;
    BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide(); // 이 두줄을 쓰면 타이틀 바를 없앨 수가 있습니다.
        setContentView(R.layout.acitivity_home);


        mViewAnimator = findViewById(R.id.animator);
         mFloatingActionButton=findViewById(R.id.home_floatingActionButton);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
//        FragmentTransaction transaction = mfragmentManager.beginTransaction();
//        transaction.replace(R.id.frame_layout, mFragmentSearch_restaurant).commitAllowingStateLoss();
        mFab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        mFab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mViewAnimator.showNext();

                anim();


//                mViewAnimator.showNext();
            }



        });
        mHomeViewPager = (ViewPager) findViewById(R.id.viewpager_home);
//        mViewPager.setOffscreenPageLimit(4);
        mHomeContentsPagerAdapter = new HomeContentsPagerAdapter(
                getSupportFragmentManager(), 4);
        mHomeViewPager.setAdapter(mHomeContentsPagerAdapter);
        mhomeService=new HomeService(this);
        mhomeService.tryGet();
        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = mfragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.search_restaurant: {
//                        transaction.replace(R.id.frame_layout, mFragmentSearch_restaurant).commitAllowingStateLoss();
                        mHomeViewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.discount: {
                        mHomeViewPager.setCurrentItem(1);
//                        transaction.replace(R.id.frame_layout, mFragmentDiscount).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.news: {
//                        transaction.replace(R.id.frame_layout, mFragmentNews).commitAllowingStateLoss();
                        mHomeViewPager.setCurrentItem(2);
                        break;
                    }
                    case R.id.mystatus: {
//                        transaction.replace(R.id.frame_layout, mystatus).commitAllowingStateLoss();
                        mHomeViewPager.setCurrentItem(3);
                        break;
                    }

                }

                return true;
            }
        });
        mHomeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mBottomNavigationView.getMenu().findItem(R.id.search_restaurant).setChecked(true);
//                    mBottomNavigationView.setSelectedItemId(R.id.search_restaurant);
                }else if (position == 1) {
                    mBottomNavigationView.getMenu().findItem(R.id.discount).setChecked(true);
//                    mBottomNavigationView.setSelectedItemId(R.id.discount);
                }else if (position == 2) {
                    mBottomNavigationView.getMenu().findItem(R.id.news).setChecked(true);
//                    mBottomNavigationView.setSelectedItemId(R.id.news);
                }else if (position == 3) {
                    mBottomNavigationView.getMenu().findItem(R.id.mystatus).setChecked(true);
//                    mBottomNavigationView.setSelectedItemId(R.id.mystatus);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
//     adapter의 값이 변경되었다는 것을 알려줍니다.
//        madapter.notifyDataSetChanged();
    }




