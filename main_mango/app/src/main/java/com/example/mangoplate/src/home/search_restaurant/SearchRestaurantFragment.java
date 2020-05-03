package com.example.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.content.Intent;
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
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.SearchTabLayout;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.models.RecyclerRestaurantData;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    boolean mHandlerFlag = true; //주석을 달던가 변수ㅁ명을 isFirst...
    private Boolean blockClickFlag = true;// 위치를 클릭하면 다이얼로그가 나온다. 그 때 문제가 생기는게 블러처리된 위치에 버튼을 클릭하면 또 다이얼로그가 나온다. 이 플래그는 이를 막기 위함이다.
    Context mContext;
    ImageView mDistanceSelector;
    ViewGroup mRootView;
    private GridLayoutManager mGridLayoutManager;

    private RestaurantRecyclerAdapter madapter;

    TextView alignmentButton;

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

        alignmentButton.setOnClickListener(new View.OnClickListener() { // 정렬 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, AlignmentButton.class);
//                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 3);
            }
        });
        PagerAdapter adapter = new PhotosAdapter(getContext());
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mHomeAcitivity, FilterLayout.class);
//                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 3);
            }
        });
//...
        Timer timer;
        final long DELAY_MS = 500;
        final long PERIOD_MS = 3000;
        mPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) mRootView.findViewById(R.id.Fragment_searchRestaurant_tab_layout);
        tabLayout.setupWithViewPager(mPager, true);
        ///주석 신경.
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

            timer = new Timer(); // 쓰레드 시작.
            timer.schedule(new TimerTask() { // task to be scheduled


                @Override
                public void run() {
                    mHandler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);

        }
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
                    Intent intent = new Intent(mHomeAcitivity, SearchTabLayout.class);
//                intent.putExtra("data", "Test Popup");
                    startActivityForResult(intent, 1);
//                    startActivity(intent);
                    blockClickFlag = false; // 얘를  다시 돌아오면 true로 바꾼다 .


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

    @Override
    public void onPause() {
        super.onPause();
        mHandlerFlag = false;
        mHandler.removeMessages(0);
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


    private void init() {
        int numberOfColumns=2;// 한줄에 2개의 컬럼을 추가
        RecyclerView recyclerView = mRootView.findViewById(R.id.recyclerView);
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
        madapter.addItem(data);
        madapter.addItem(data);

    }


}
