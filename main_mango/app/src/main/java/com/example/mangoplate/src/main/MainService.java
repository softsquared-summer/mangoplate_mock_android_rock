package com.example.mangoplate.src.main;

import android.util.Log;

import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.main.interfaces.MainActivityView;
import com.example.mangoplate.src.main.interfaces.MainRetrofitInterface;
import com.example.mangoplate.src.main.models.DefaultResponse;
import com.example.mangoplate.src.main.models.Result;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mangoplate.src.ApplicationClass.getRetrofit;

class MainService { //여기서는 서비스 컨트롤
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

//    void getTest() {
//        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
//        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mMainActivityView.validateFailure(null);
//                    return;
//                }
//
//                mMainActivityView.validateSuccess(defaultResponse.getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//                mMainActivityView.validateFailure(null);
//            }
//        });
//    }

    void tryPost(String type,String mainJsonString){

        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        Log.e("성공",""+"돌긴 도니 ");
        RequestBody requestBody= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),mainJsonString);
//        Log.e("성공",""+ Base64.decode(requestBody.toString(),1));
        mainRetrofitInterface.toString();
        mainRetrofitInterface.postTest(type,requestBody).enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mMainActivityView.validateFailure(null);
//                    return;
//                }
                Log.e("실패","응");

                if (response.code() == 200) {
                    DefaultResponse resultList = response.body();
                    if (resultList.getResult() != null ) {

                        ApplicationClass.X_ACCESS_TOKEN=resultList.getResult().getJwt();

                                Log.e("성공",""+ resultList.getResult().getJwt());

                    }
                    if(resultList.getResult()!=null)
                    {


                    }else{
                        mMainActivityView.validateFailure(null);
                        Log.e("실패","응");
                    }

                }else{
                    Log.e("실패","응");

                }


//                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });



    }
}
