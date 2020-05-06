package com.softSquared.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.softSquared.mangoplate.R;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

class AdvertisementPhotosAdapter extends PagerAdapter {


    public static final int mEatDeal = 0;
    public static final int mVisamango = 1;
    public static final int mgahuna = 2;
    public static final int mMangoaward = 3;
    Context mContext;
    FragmentManager mfragmentManager;

    public AdvertisementPhotosAdapter(Context mContext) {

        this.mContext = mContext;
    }

    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.viewpager_custom, container, false);
            ImageView frame = view.findViewById(R.id.Fragment_searchRestaurant_ad);
            switch (position) {
                case mEatDeal:
                    Glide.with(mContext).load(R.drawable.ad_eatdeal).into(frame);
//                    frame.setImageResource(R.drawable.ad_eatdeal);
                    break;
                case mVisamango:
                    Glide.with(mContext).load(R.drawable.ad_visamangoplate).into(frame);
//                    frame.setImageResource(R.drawable.ad_visamangoplate);
                    break;
                case mgahuna:
                    Glide.with(mContext).load(R.drawable.ad_gahuna).into(frame);
//                    frame.setImageResource(R.drawable.ad_gahuna);
                    break;
                case mMangoaward:

                    Glide.with(mContext).load(R.drawable.ad_mangoaward).into(frame);
//                    frame.setImageResource(R.drawable.ad_mangoaward);
                    break;

            }


        }

        // 뷰페이저에 추가.
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() { //데이터 사이즈에 따라 다르게 반환 할 수 있다. 예를 들어 서버에서 온 숫자 만큼도 바꿀 수 있다.
        // 전체 페이지 수는 10개로 고정.
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}
