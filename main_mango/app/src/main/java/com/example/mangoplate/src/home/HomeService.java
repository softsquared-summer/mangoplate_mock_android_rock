package com.example.mangoplate.src.home;

import android.content.Intent;
import android.util.Log;

import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.home.advertisement.Advertisement;
import com.example.mangoplate.src.home.interfaces.HomeActivityView;
import com.example.mangoplate.src.home.interfaces.HomeRetrofitInterface;
import com.example.mangoplate.src.home.models.HomeResponse;
import com.example.mangoplate.src.main.interfaces.MainActivityView;
import com.example.mangoplate.src.main.interfaces.MainRetrofitInterface;
import com.example.mangoplate.src.main.models.DefaultResponse;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mangoplate.src.ApplicationClass.getRetrofit;

public class HomeService {
    private  HomeActivityView mHomeActivityView;

    private  HomeAcitivity mHomeAcitivity;
    private HashMap<String,String> mEventresponse;

    HomeService(final HomeAcitivity homeAcitivity)
    {
        this.mHomeAcitivity=homeAcitivity;
    }


    void tryGet(){

        final HomeRetrofitInterface mainRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        Log.e("성공",""+"돌긴 도니 ");
        mainRetrofitInterface.toString();
        mainRetrofitInterface.GetEvent(X_ACCESS_TOKEN).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                final HomeResponse homeResponse = response.body();

                Log.e("실패","응");

                if (response.code() == 200) {

                    if (homeResponse.getResult() != null ) {

                   mEventresponse=new HashMap<String,String>();
               Log.e("홈리스폰스",""+homeResponse.getResult().getEventId());

               Log.e("이미지URL",""+homeResponse.getResult().getImageUrl());
                    }

                    mEventresponse.put("EventId",Integer.toString(homeResponse.getResult().getEventId()));
                    mEventresponse.put("ImageUrl",homeResponse.getResult().getImageUrl());
                    Intent advestismentActivitytoMove=new Intent(mHomeAcitivity, Advertisement.class);
                    advestismentActivitytoMove.putExtra("ImageUrl",mEventresponse.get("ImageUrl"));
                    advestismentActivitytoMove.putExtra("EventId",mEventresponse.get("EventId"));
                    mHomeAcitivity.startActivity(advestismentActivitytoMove);
                }else{
                    mHomeActivityView.validateFailure(null);
                }


            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                mHomeActivityView.validateFailure(null);
            }
        });



    }
}
