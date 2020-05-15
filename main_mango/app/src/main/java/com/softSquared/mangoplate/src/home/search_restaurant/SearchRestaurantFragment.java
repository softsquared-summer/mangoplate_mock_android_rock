package com.softSquared.mangoplate.src.home.search_restaurant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.ApplicationClass;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.search_restaurant.alignment_button.AlignmentButton;
import com.softSquared.mangoplate.src.home.search_restaurant.distance_selected_layout.DistanceSelectedLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.filter_button.FilterLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.interfaces.SearchRestaurantViewFragment;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.mylocation_search.MyLocationSearch;
import com.google.android.material.tabs.TabLayout;
import com.softSquared.mangoplate.src.home.naverMapActivity.naverMapAcitivty;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResultResponse;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import static com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout.outputDatas;

//정렬
public class SearchRestaurantFragment extends Fragment implements SearchRestaurantViewFragment { //스태

    //리퀘스트 코드


    HomeAcitivity mHomeAcitivity;
    LinearLayout mLocationClick; //LinearLayout의 범위:동대문구와 downArrow 합친게 클릭 됌.
    private final int NUM_PAGES = 4; // private ,public 함부로 바꾸면 안된다..
    int mCurrentPage = 0;
    ViewPager mPager;
    Handler mHandler;
    private Boolean blockClickFlag = true;// 위치를 클릭하면 다이얼로그가 나온다. 그 때 문제가 생기는게 블러처리된 위치에 버튼을 클릭하면 또 다이얼로그가 나온다. 이 플래그는 이를 막기 위함이다.
    Context mContext;
    ImageView mDistanceSelector;
    ViewGroup mRootView;
    Timer mTimer; // 광고 타이머
    RecyclerView recyclerViewSearchRestaurant;
    private GridLayoutManager mGridLayoutManager;
    private RestaurantRecyclerAdapter madapter;
    RecyclerView mRecyclerViewRestaurantInformation;
    private Runnable mUpdate;// 광고 핸들러 Runnable
    TextView alignmentButton;
    private LocationManager mLocationManager;
    private static final int REQUEST_CODE_LOCATION = 2;

    SearchRestaurantService mSearchRestaurantService;
    private ImageView mapButton;

    TextView mLocalName;
    TextView alignment_button; //추천순 ▼ 버튼
    private int DISTANCE_SELECTED_lAYOUT = 2;
    private int LOCALl_SEARCH_TABLAYOUT = 1;
    boolean isFirst = true;

    private SearchRestaurantService searchRestaurantService;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mContext = context;
        mHomeAcitivity = (HomeAcitivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_searchrestaurant, container, false);
        mPager = mRootView.findViewById(R.id.Fragment_searchRestaurant_photos_viewpager); //스네이크 케이스로 패키지도 클래스가 파스칼 id도 파스칼 .더 정확한건 안드로이드 가이드 .
        mLocalName = mRootView.findViewById(R.id.Localname);
        ImageView filter = mRootView.findViewById(R.id.filter);
        alignmentButton = mRootView.findViewById(R.id.alignment_button);
        recyclerViewSearchRestaurant = mRootView.findViewById(R.id.fragment_recyclerView_searchRestaurant);
        alignmentButton.setText(Html.fromHtml("<u>" + "추천순 ▾" + "</u>"));
//        alignmentButton.setTextColor(getResources().getColor(R.color.grey_200));
        mapButton = mRootView.findViewById(R.id.restaurant_map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, naverMapAcitivty.class);
//                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 3);

            }
        });
        alignmentButton.setOnClickListener(new View.OnClickListener() { // 정렬 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, AlignmentButton.class);
//                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 3);


            }
        });


        mLocalName.setText(Html.fromHtml("<u>" + "내 주변 " + "</u>"));
        setMyLocation(); // 내 위치 정보 받아오기 .
        setViewPager();// 광고 배너( 자동으로 넘어가는)


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, FilterLayout.class);
                startActivityForResult(intent, 3);
            }
        });
//...


        mDistanceSelector = mRootView.findViewById(R.id.distance_selector);
        mDistanceSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, DistanceSelectedLayout.class);
                startActivityForResult(intent, 2);
            }
        });
        mLocationClick = mRootView.findViewById(R.id.Fragment_searchRestaurant_location_click);

//        이건 동대문구와 downarrow 클릭시 벌어지는 이벤트 .
        mLocationClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blockClickFlag) {
                    Intent intent = new Intent(mHomeAcitivity, LocalSearchTabLayout.class);
                    startActivityForResult(intent, LOCALl_SEARCH_TABLAYOUT);
//                    blockClickFlag = false; // 얘를  다시 돌아오면 true로 바꾼다 .
                }
            }
        });
        init(); //리사이클러 뷰 세팅

        return mRootView;
    }

    void setMyLocation() //내 위치 세팅
    {

        //사용자의 위치 수신을 위한 세팅
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        //사용자의 현재 위치
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            double latitude = userLocation.getLatitude();
            double longitude = userLocation.getLongitude();
            Intent passData = new Intent(getActivity(), MyLocationSearch.class);
            ApplicationClass.lat = (float) latitude;
            ApplicationClass.lng = (float) longitude;
            passData.putExtra("lat", ApplicationClass.lat);
            passData.putExtra("lng", ApplicationClass.lng);
            System.out.println("////////////현재 내 위치값 : " + latitude + "," + longitude);
        }

    }

    void setViewPager() // 뷰 페이저 세팅
    {

        //광고 이미지 .
        PagerAdapter pagerAdapter = new AdvertisementPhotosAdapter(getContext());
        mPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = mRootView.findViewById(R.id.Fragment_searchRestaurant_tab_layout);
        tabLayout.setupWithViewPager(mPager, true);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LOCALl_SEARCH_TABLAYOUT) {
            if (resultCode == mHomeAcitivity.RESULT_OK) {
                for (int i = 0; i < outputDatas.size(); i++) {
                    Log.e("제발 되요", outputDatas.get(i));
                    outputDatas.get(i);
                }
                if (outputDatas.size() == 1) {
                    mLocalName.setText(Html.fromHtml("<u>" + outputDatas.get(outputDatas.size() - 1) + "</u>"));
                } else {
                    mLocalName.setText(Html.fromHtml("<u>" + outputDatas.get(outputDatas.size() - 1) + " 외 " + (outputDatas.size() - 1) + "곳 " + "</u>"));
                }
                searchRestaurantService = new SearchRestaurantService(this, mContext);
                searchRestaurantService.makeAreaString();
                outputDatas.clear();
            }

        }
        //t.setText(Html.fromHtml("<u>" + sitename + "</u>")); // 밑줄
        if (requestCode == DISTANCE_SELECTED_lAYOUT) {
            if (resultCode == mHomeAcitivity.RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");

                if (Integer.parseInt(result) == 3) {

                    mDistanceSelector.setImageResource(R.drawable.three_hundred_m);
                }

            }


        }
    }


    //사용자 위치 수신
    private Location getMyLocation() {
        Location currentLocation = null;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        } else {
            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            //마지막 위치만 받아오는 기능임 . 갱신하는 콜백을 받아오게 하는 코드가 존재해야한다.
            currentLocation = mLocationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (isFirst) {
            init();// 항상 초기화
            mSearchRestaurantService = new SearchRestaurantService(this, getContext());
            mSearchRestaurantService.tryStartRestaurantsList();


            isFirst = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        advertismentTimerStart(); //광고 핸들러 시작 여기다 둔 이유: onPause() -> onResume() 이 동작순서에 광고는 재시작 되어야한다.
    }

    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
        madapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();

        madapter.notifyDataSetChanged();  // 이 값이 뭐든간에 넘어 오면 fetch가 시작 되면 된다 .
    }

    @Override
    public void onDestroyView() {
        madapter.notifyDataSetChanged();
        super.onDestroyView();


    }


//코드만 봐도 바로 알 수 있게

    private void init() {
        int numberOfColumns = 2;// 한줄에 2개의 컬럼을 추가
        mRecyclerViewRestaurantInformation = mRootView.findViewById(R.id.fragment_recyclerView_searchRestaurant);
        mGridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewRestaurantInformation.setLayoutManager(mGridLayoutManager);
        madapter = new RestaurantRecyclerAdapter(this);
        mRecyclerViewRestaurantInformation.setAdapter(madapter);
    }

    public void advertismentTimerStart() {
        final long DELAY_MS = 500;
        final long PERIOD_MS = 3000;
        mHandler = new Handler();
        mUpdate = new Runnable() {
            public void run() {
                if (mCurrentPage == NUM_PAGES) {
                    mCurrentPage = 0;
                }
                mPager.setCurrentItem(mCurrentPage++, true);
            }
        };
        mTimer = new Timer(); // 쓰레드 시작.
        mTimer.schedule(new TimerTask() { // task to be scheduled


            @Override
            public void run() {
                mHandler.post(mUpdate);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void successUpdateRecyclerView(RestaurantResultResponse restaurantInfoResultList) {
        init();
        for (RestaurantResult result : restaurantInfoResultList.getResult()) {

            madapter.addItem(result);

        }

        madapter.notifyDataSetChanged();
 //여기다가 추가 뷰 컨트롤 서비스에서 하지말고 .
    }

}

