package com.example.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangoplate.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

class PhotosAdapter extends PagerAdapter {


    public static final int mEatDeal=0;
    public static final int mVisamango=1;
    public static final int mgahuna=2;
    public static final int mMangoaward=3;
    Context mContext;
    FragmentManager mfragmentManager;
    public PhotosAdapter(Context mContext,FragmentManager fragmentManager) {

        this.mfragmentManager=mfragmentManager;
        this.mContext=mContext;
    }

    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.





    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.viewpager_custom, container, false);


            ImageView frame=view.findViewById(R.id.Fragment_searchRestaurant_ad);
            switch (position)
            {
                case mEatDeal:  frame.setImageResource(R.drawable.ad_eatdeal); break;
                case mVisamango: frame.setImageResource(R.drawable.ad_visamangoplate);break;
                case mgahuna: frame.setImageResource(R.drawable.ad_gahuna);break;
                case mMangoaward: frame.setImageResource(R.drawable.ad_mangoaward);break;

            }



        }

        // 뷰페이저에 추가.
        container.addView(view) ;

        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // 전체 페이지 수는 10개로 고정.
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}
