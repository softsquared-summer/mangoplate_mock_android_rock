package com.example.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Fragment_searchRestaurant extends Fragment {

    HomeAcitivity mhomeAcitivity;

    LinearLayout mlocation_click;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mhomeAcitivity = (HomeAcitivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.searchrestaurant_fragment,container,false);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.Fragment_searchRestaurant_photos_viewpager);
        PagerAdapter adapter = new PhotosAdapter(getContext(),getChildFragmentManager());
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.Fragment_searchRestaurant_tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        mlocation_click=rootView.findViewById(R.id.Fragment_searchRestaurant_location_click);
//        이건 동대문구와 downarrow 클릭시 벌어지는 이벤트 .
        mlocation_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return rootView;
    }

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
