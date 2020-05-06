package com.example.mangoplate.src.home.advertisement;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;


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

        mImageUrl = Advertisement_getResponse.getExtras().getString("ImageUrl");
        advertisement_close = findViewById(R.id.advertisement_close);
        Advertisement_getResponse.getExtras().getInt("EventId");
        // Glide로 이미지 표시하기
        Glide.with(this).load(mImageUrl).placeholder(R.drawable.loading).into(mAdvertisemnetImage);


    }
}
