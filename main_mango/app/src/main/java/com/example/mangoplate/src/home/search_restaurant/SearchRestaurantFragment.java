package com.example.mangoplate.src.home.search_restaurant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.alignment_button.AlignmentButton;
import com.example.mangoplate.src.home.search_restaurant.distance_selected_layout.DistanceSelectedLayout;
import com.example.mangoplate.src.home.search_restaurant.filter_button.FilterLayout;
import com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout;
import com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.models.RecyclerRestaurantData;
import com.google.android.material.tabs.TabLayout;

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

//정렬
public class SearchRestaurantFragment extends Fragment { //스태

    //리퀘스트 코드


    HomeAcitivity mHomeAcitivity;
    LinearLayout mLocationClick; //LinearLayout의 범위:동대문구와 downArrow 합친게 클릭 됌.
    private final int NUM_PAGES = 4; // private ,public 함부로 바꾸면 안된다..
    int mCurrentPage = 0;
    ViewPager mPager;
    Handler mHandler;
    boolean mHandlerFlag = true; //주석을 달던가 변수명을 isFirst...
    private Boolean blockClickFlag = true;// 위치를 클릭하면 다이얼로그가 나온다. 그 때 문제가 생기는게 블러처리된 위치에 버튼을 클릭하면 또 다이얼로그가 나온다. 이 플래그는 이를 막기 위함이다.
    Context mContext;
    ImageView mDistanceSelector;
    ViewGroup mRootView;
    private GridLayoutManager mGridLayoutManager;
    Timer mTimer;
    RecyclerView recyclerViewSearchRestaurant;
    private RestaurantRecyclerAdapter madapter;
    private Runnable mUpdate;// 광고 핸들러 Runnable
    TextView alignmentButton;
    private LocationManager mLocationManager;
    private static final int REQUEST_CODE_LOCATION = 2;

    public static double lat;
    public static double  lng;

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
        mPager = (ViewPager) mRootView.findViewById(R.id.Fragment_searchRestaurant_photos_viewpager); //스네이크 케이스로 패키지도 클래스가 파스칼 id도 파스칼 .더 정확한건 안드로이드 가이드 .
        // 코드에서 그 사람의 얼굴이 보인다 .
        ImageView filter = mRootView.findViewById(R.id.filter);
        alignmentButton = mRootView.findViewById(R.id.alignment_button);
        recyclerViewSearchRestaurant = mRootView.findViewById(R.id.fragment_recyclerView_searchRestaurant);
        recyclerViewSearchRestaurant.setNestedScrollingEnabled(false);
        alignmentButton.setOnClickListener(new View.OnClickListener() { // 정렬 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, AlignmentButton.class);
//                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 3);
            }
        });
        //광고 이미지 .
        PagerAdapter adapter = new AdvertisementPhotosAdapter(getContext());

//사용자의 위치 수신을 위한 세팅
        mLocationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
//사용자의 현재 위치
        Location userLocation = getMyLocation();
        if( userLocation != null ) {
            double latitude = userLocation.getLatitude();
            double longitude = userLocation.getLongitude();
            lat =latitude;
            lng= longitude;
            System.out.println("////////////현재 내 위치값 : "+latitude+","+longitude);
        }




        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, FilterLayout.class);
                startActivityForResult(intent, 3);
            }
        });
//...


        mPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) mRootView.findViewById(R.id.Fragment_searchRestaurant_tab_layout);
        tabLayout.setupWithViewPager(mPager, true);
        ///주석 신경.

        mDistanceSelector = mRootView.findViewById(R.id.distance_selector);
        mDistanceSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, DistanceSelectedLayout.class);
                startActivityForResult(intent, 2); }
        });
        mLocationClick = mRootView.findViewById(R.id.Fragment_searchRestaurant_location_click);

//        이건 동대문구와 downarrow 클릭시 벌어지는 이벤트 .
        mLocationClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blockClickFlag) {
                    Intent intent = new Intent(mHomeAcitivity, LocalSearchTabLayout.class);
//                intent.putExtra("data", "Test Popup");
                    startActivityForResult(intent, 1);
//                    startActivity(intent);


//                    dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
//                    blockClickFlag = false; // 얘를  다시 돌아오면 true로 바꾼다 .


                }
            }
        });
        init();
        getData();
//        madapter.notifyDataSetChanged();

        return mRootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            if (resultCode == mHomeAcitivity.RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");

                if (Integer.parseInt(result) == 3) {

                    mDistanceSelector.setImageResource(R.drawable.three_hundred_m);
                }

            }


        }
    }
    /**
     * 사용자의 위치를 수신
     */
    private Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("////////////사용자에게 권한을 요청해야함");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        }
        else {
            System.out.println("////////////권한요청 안해도됨");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
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

    }

    @Override
    public void onResume() {
        super.onResume();

        advertismentTimerStart(); //광고 핸들러 시작
    }

    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
//        mHandler.removeCallbacksAndMessages(null);

//        mHandler.removeCallbacks(mUpdate);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

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


    private void init() {
        int numberOfColumns = 2;// 한줄에 2개의 컬럼을 추가
        RecyclerView recyclerView = mRootView.findViewById(R.id.fragment_recyclerView_searchRestaurant);
        mGridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mGridLayoutManager);


        madapter = new RestaurantRecyclerAdapter();
        recyclerView.setAdapter(madapter);
    }

    private void getData() {
//         임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립",
//                "국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립");
//        List<String> listContent = Arrays.asList(
//                "이 꽃은 국화입니다.",
//                "여기는 사막입니다.",
//                "이 꽃은 수국입니다.",
//                "이 동물은 해파리입니다.",
//                "이 동물은 코알라입니다.",
//                "이것은 등대입니다.",
//                "이 동물은 펭귄입니다.",
//                "이 꽃은 튤립입니다.",
//                "이 꽃은 국화입니다.",
//                "여기는 사막입니다.",
//                "이 꽃은 수국입니다.",
//                "이 동물은 해파리입니다.",
//                "이 동물은 코알라입니다.",
//                "이것은 등대입니다.",
//                "이 동물은 펭귄입니다.",
//                "이 꽃은 튤립입니다."
//        );
//        List<Integer> listResId = Arrays.asList(
//                R.drawable.chrysanthemum,
//                R.drawable.desert,
//                R.drawable.hydrangeas,
//                R.drawable.jellyfish,
//                R.drawable.koala,
//                R.drawable.lighthouse,
//                R.drawable.penguins,
//                R.drawable.tulips,
//                R.drawable.chrysanthemum,
//                R.drawable.desert,
//                R.drawable.hydrangeas,
//                R.drawable.jellyfish,
//                R.drawable.koala,
//                R.drawable.lighthouse,
//                R.drawable.penguins,
//                R.drawable.tulips
//        );
//        for (int i = 0; i < listTitle.size(); i++) {
//         각 List의 값들을 data 객체에 set 해줍니다.
        RecyclerRestaurantData data = new RecyclerRestaurantData();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setResId(listResId.get(i));

        // 각 값이 들어간 data를 adapter에 추가합니다.
        madapter.addItem(data);
        madapter.addItem(data);
        madapter.addItem(data);
        madapter.addItem(data);


    }

    public void advertismentTimerStart()
    {
        final long DELAY_MS = 500;
        final long PERIOD_MS = 3000;
        mHandler = new Handler();
            mUpdate = new Runnable() {
                public void run() {
                    if (mCurrentPage == NUM_PAGES) {
                        mCurrentPage = 0; }
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
    }


