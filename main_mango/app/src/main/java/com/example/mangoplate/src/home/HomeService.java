package com.example.mangoplate.src.home;

import android.util.Log;

import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.home.interfaces.HomeActivityView;
import com.example.mangoplate.src.home.interfaces.HomeRetrofitInterface;
import com.example.mangoplate.src.home.models.HomeResponse;
import com.example.mangoplate.src.main.interfaces.MainActivityView;
import com.example.mangoplate.src.main.interfaces.MainRetrofitInterface;
import com.example.mangoplate.src.main.models.DefaultResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mangoplate.src.ApplicationClass.getRetrofit;

public class HomeService {
    private final HomeActivityView mHomeActivityView;

    HomeService(final HomeActivityView mHomeActivityView) {
        this.mHomeActivityView = mHomeActivityView;
    }


    void tryGet(){

        final HomeRetrofitInterface mainRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        Log.e("성공",""+"돌긴 도니 ");
//        Log.e("성공",""+ Base64.decode(requestBody.toString(),1));
        mainRetrofitInterface.toString();
        mainRetrofitInterface.GetEvent(X_ACCESS_TOKEN).enqueue(new Callback<HomeResponse>() {


            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                final HomeResponse homeResponse = response.body();

                Log.e("실패","응");

                if (response.code() == 200) {

                    if (homeResponse.getResult() != null ) {

               Log.e("홈리스폰스",""+homeResponse.getResult().getEventId());

                    }

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
