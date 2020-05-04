package com.example.mangoplate.src.home.advertisement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;

import java.util.Base64;

public class Advertisement extends BaseActivity {

    ImageView mAdvertisemnetImage;
    String mImageUrl;
    int mEventId;
    TextView advertisement_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        mAdvertisemnetImage = findViewById(R.id.advertisement_image);

        Intent Advertisement_getResponse = getIntent();
        mImageUrl= Advertisement_getResponse.getExtras().getString("ImageUrl");

        advertisement_close=findViewById(R.id.advertisement_close);
        Advertisement_getResponse.getExtras().getInt("EventId");
        // Glide로 이미지 표시하기

        Glide.with(this).load(mImageUrl).into(mAdvertisemnetImage);


        advertisement_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        출처: https://jizard.tistory.com/179 [GEUMSON]
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);

        //액티비티 애니메이션 x
    }


//    출처: https://puzzleleaf.tistory.com/62 [퍼즐잎의 기술블로그]
}
