package com.example.mangoplate.src.home.advertisement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;

import java.util.Base64;

public class Advertisement extends BaseActivity {

    ImageView mAdvertisemnetImage;
    String mImageUrl;
    int mEventId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        mAdvertisemnetImage = findViewById(R.id.advertisement_image);

        Intent Advertisement_getResponse = getIntent();
        mImageUrl= Advertisement_getResponse.getExtras().getString("ImageUrl");

        Advertisement_getResponse.getExtras().getInt("EventId");
        // Glide로 이미지 표시하기

        Glide.with(this).load(mImageUrl).into(mAdvertisemnetImage);


//        출처: https://jizard.tistory.com/179 [GEUMSON]
    }
}
