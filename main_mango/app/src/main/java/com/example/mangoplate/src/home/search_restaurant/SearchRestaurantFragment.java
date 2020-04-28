package com.example.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.SearchTabLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

//정렬
public class SearchRestaurantFragment extends Fragment { //스태

    HomeAcitivity mHomeAcitivity;
    LinearLayout mLocationClick; //LinearLayout의 범위:동대문구와 downArrow 합친게 클릭 됌.
    private final int NUM_PAGES = 4; // private ,public 함부로 바꾸면 안된다..
    int mCurrentPage = 0;
    ViewPager mPager;
    Handler mHandler;
    boolean mHandlerFlag = true; //주석을 달던가 변수ㅁ명을 isFirst...

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mHomeAcitivity = (HomeAcitivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.searchrestaurant_fragment, container, false);
        mPager = (ViewPager) rootView.findViewById(R.id.Fragment_searchRestaurant_photos_viewpager); //스네이크 케이스로 패키지도 클래스가 파스칼 id도 파스칼 .더 정확한건 안드로이드 가이드 .
        // 코드에서 그 사람의 얼굴이 보인다 .
        PagerAdapter adapter = new PhotosAdapter(getContext(), getChildFragmentManager());

//...

//안드로이드 drwa
        Timer timer;
        final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.


        mPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.Fragment_searchRestaurant_tab_layout);
        tabLayout.setupWithViewPager(mPager, true);

        /*After setting the adapter use the timer *///주석 신경.
        if (mHandlerFlag) //들여쓰기를 통일감 있게 , 회사의 룰에 맞게 .
        {
            mHandler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {

                    if (mCurrentPage == NUM_PAGES) {
                        mCurrentPage = 0;
                    }
                    mPager.setCurrentItem(mCurrentPage++, true);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer.schedule(new TimerTask() { // task to be scheduled


                @Override
                public void run() {
                    mHandler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);

        }


        mLocationClick = rootView.findViewById(R.id.Fragment_searchRestaurant_location_click);
//        이건 동대문구와 downarrow 클릭시 벌어지는 이벤트 .
        mLocationClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, SearchTabLayout.class);
//                intent.putExtra("data", "Test Popup");

               startActivity(intent);
//                startActivityForResult(intent, 1);



            }
        });
        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
        mHandlerFlag = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandlerFlag = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
        mHandlerFlag = false;
    }
//코드만 봐도 바로 알 수 있게
    //    언더라인 그리기 .
//    String sitename = "MobilePlace";
//    TextView t = (TextView)findViewById(R.id.text);
//t.setText(Html.fromHtml("<u>" + sitename + "</u>")); // 밑줄
//
//    출처: https://twinw.tistory.com/23 [흰고래의꿈]
    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu1, container, false);
////        View view = inflater.inflate(R.layout.fragment_menu1, null); // Fragment로 불러올 xml파일을 view로 가져옵니다.
//        Button lat = rootView.findViewById(R.id.latest);
//
//
//        lat.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.latest:
//                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager(); // 프래그먼트 매니저는 액티비티를 받아야 쓸 수 있다.
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.add(R.id.frame_layout, late).addToBackStack(null).commit(); // 프래그먼트 스택을 쌓자.
////                        ((MainActivity)getActivity()).
////                                replaceFragment(LATEST.newInstance());// 새로 불러올 Fragment의 Instance를 Main으로 전달
//                        break;
//                }
//            }
//        });
//        return rootView;
//    }


}
