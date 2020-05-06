package com.softSquared.mangoplate.src.home;

import android.content.Intent;
import android.util.Log;

import com.softSquared.mangoplate.src.home.advertisement.ActivityAdvertisement;
import com.softSquared.mangoplate.src.home.interfaces.HomeActivityView;
import com.softSquared.mangoplate.src.home.interfaces.HomeRetrofitInterface;
import com.softSquared.mangoplate.src.home.models.HomeEventResponse;
import com.softSquared.mangoplate.src.home.models.HomeEventsResponse;
import com.softSquared.mangoplate.src.home.models.HomeResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softSquared.mangoplate.src.ApplicationClass.getRetrofit;

public class HomeService {
    private HomeActivityView mHomeActivityView;

    private HomeAcitivity mHomeAcitivity;
    private HashMap<String, String> mEventresponse;

    HomeService(final HomeAcitivity homeAcitivity) {
        this.mHomeAcitivity = homeAcitivity;
    }


    void tryEventGet() {

        final HomeRetrofitInterface mainRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        Log.e("성공", "" + "돌긴 도니 ");
        mainRetrofitInterface.toString();
        mainRetrofitInterface.GetEvent(X_ACCESS_TOKEN).enqueue(new Callback<HomeEventResponse>() {
            @Override
            public void onResponse(Call<HomeEventResponse> call, Response<HomeEventResponse> response) {
                final HomeEventResponse homeEventResponse = response.body();


                if (response.code() == 200) {

                    if (homeEventResponse.getResult() != null) {

                        mEventresponse = new HashMap<>();
                        Log.e("홈리스폰스", "" + homeEventResponse.getResult().getEventId());

                        Log.e("Jwt토큰", "" + X_ACCESS_TOKEN);
                        Log.e("이미지URL", "" + homeEventResponse.getResult().getImageUrl());
                        Intent advestismentActivitytoMove = new Intent(mHomeAcitivity, ActivityAdvertisement.class);
                        advestismentActivitytoMove.putExtra("ImageUrl", homeEventResponse.getResult().getImageUrl());
                        advestismentActivitytoMove.putExtra("EventId", Integer.toString(homeEventResponse.getResult().getEventId()));
                        mHomeAcitivity.startActivity(advestismentActivitytoMove);
                    }

                } else {
                    mHomeActivityView.validateFailure(null);
                }


            }

            @Override
            public void onFailure(Call<HomeEventResponse> call, Throwable t) {

            }


        });

    }

    void tryEventsGet() {

        final HomeRetrofitInterface mainRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        Log.e("성공", "" + "돌긴 도니 ");
        mainRetrofitInterface.toString();
        mainRetrofitInterface.GetEvents("main", X_ACCESS_TOKEN).enqueue(new Callback<HomeEventsResponse>() {
            @Override
            public void onResponse(Call<HomeEventsResponse> call, Response<HomeEventsResponse> response) {
                final HomeEventsResponse homeEventsResponse = response.body();


                if (homeEventsResponse.getResult() != null && homeEventsResponse.getResult().size() > 0) {
                    for (HomeResult homeResult : homeEventsResponse.getResult()) {

                        if (response.code() == 200) {

                            if (homeEventsResponse.getResult() != null) {

                                Log.e("광고 홈리스폰스", "" + homeResult.getImageUrl());

                                Log.e("광고 이미지URL", "" + homeResult.getImageUrl());

                            }

                        } else {
                            mHomeActivityView.validateFailure(null);
                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<HomeEventsResponse> call, Throwable t) {

            }
        });
    }
}
