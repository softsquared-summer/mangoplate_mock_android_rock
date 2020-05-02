package com.example.mangoplate.src.home.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.RestaurantRecyclerAdapter;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.ContentsPagerAdapter;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.models.RecyclerRestaurantData;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class DiscountFragment extends Fragment {
    private TabLayout mTabLayout;
    private Context mContext;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;
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
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_discount, container, false);
        mAppToolbar = mRootView.findViewById(R.id.app_toolbar_discount_discount);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.fragment_tab_discount);
        mTabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM);
        mTabLayout.addTab(mTabLayout.newTab().setText("EAT딜"));
        mTabLayout.addTab(mTabLayout.newTab().setText("망고픽 스토리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TOP 리스트"));
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewPager_discount);
//        mViewPager.setOffscreenPageLimit(4);
        mContentPagerAdapter = new ContentsPagerAdapter(
                mHomeAcitivity.getSupportFragmentManager(), mTabLayout.getTabCount());
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
//         ToolBar를 ActionBar로 설정해줘야 합니다.
        ((AppCompatActivity) getActivity()).setSupportActionBar(mAppToolbar);

//        init();
//        getData();


        return mRootView;
    }


//    private void init() {
//        int numberOfColumns = 2;// 한줄에 2개의 컬럼을 추가
//        RecyclerView recyclerView = mRootView.findViewById(R.id.recyclerview_newspager_contents);
////        mGridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//
//        madapter = new RestaurantRecyclerAdapter();
//        recyclerView.setAdapter(madapter);
//        recyclerView.setNestedScrollingEnabled(true);
//    }
//
//    private void getData() {
////         임의의 데이터입니다.
////        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립",
////                "국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립");
////        List<String> listContent = Arrays.asList(
////                "이 꽃은 국화입니다.",
////                "여기는 사막입니다.",
////                "이 꽃은 수국입니다.",
////                "이 동물은 해파리입니다.",
////                "이 동물은 코알라입니다.",
////                "이것은 등대입니다.",
////                "이 동물은 펭귄입니다.",
////                "이 꽃은 튤립입니다.",
////                "이 꽃은 국화입니다.",
////                "여기는 사막입니다.",
////                "이 꽃은 수국입니다.",
////                "이 동물은 해파리입니다.",
////                "이 동물은 코알라입니다.",
////                "이것은 등대입니다.",
////                "이 동물은 펭귄입니다.",
////                "이 꽃은 튤립입니다."
////        );
////        List<Integer> listResId = Arrays.asList(
////                R.drawable.chrysanthemum,
////                R.drawable.desert,
////                R.drawable.hydrangeas,
////                R.drawable.jellyfish,
////                R.drawable.koala,
////                R.drawable.lighthouse,
////                R.drawable.penguins,
////                R.drawable.tulips,
////                R.drawable.chrysanthemum,
////                R.drawable.desert,
////                R.drawable.hydrangeas,
////                R.drawable.jellyfish,
////                R.drawable.koala,
////                R.drawable.lighthouse,
////                R.drawable.penguins,
////                R.drawable.tulips
////        );
////        for (int i = 0; i < listTitle.size(); i++) {
////         각 List의 값들을 data 객체에 set 해줍니다.
//        RecyclerRestaurantData data = new RecyclerRestaurantData();
////            data.setTitle(listTitle.get(i));
////            data.setContent(listContent.get(i));
////            data.setResId(listResId.get(i));
//
//        // 각 값이 들어간 data를 adapter에 추가합니다.
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//        madapter.addItem(data);
//
//
//    }
}
