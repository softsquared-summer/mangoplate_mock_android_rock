package com.softSquared.mangoplate.src.home.advertisement;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.BaseActivity;


public class ActivityAdvertisement extends BaseActivity {

    ImageView mAdvertisemnetImage;
    String mImageUrl;
    int mEventId;
    TextView advertisement_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        setView(); //View 세팅
        advertisement_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        //액티비티 종료시 애니메이션 주지 말기 .
    }

    void setView()
    {   mAdvertisemnetImage = findViewById(R.id.advertisement_image);
        Intent Advertisement_getResponse = getIntent();
        //널 값 체크 . 잘 넘어 왔는지 . 남을 믿지 말라 .
        mImageUrl = Advertisement_getResponse.getExtras().getString("ImageUrl");
        advertisement_close = findViewById(R.id.advertisement_close);
        Advertisement_getResponse.getExtras().getInt("EventId");
        // Glide로 이미지 표시하기
        Glide.with(this).load(mImageUrl).placeholder(R.drawable.loading).into(mAdvertisemnetImage);


    }
}
