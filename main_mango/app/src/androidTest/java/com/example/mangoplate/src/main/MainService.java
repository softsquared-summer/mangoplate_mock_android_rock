package com.example.mangoplate.src.main;

import com.example.mangoplate.src.main.interfaces.MainRetrofitInterface;
import com.example.mangoplate.src.main.models.DefaultResponse;
import com.example.mangoplate.src.main.interfaces.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mangoplate.src.ApplicationClass.getRetrofit;

class MainService { //여기서는 서비스 컨트롤
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
