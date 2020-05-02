package com.example.mangoplate.src.home.interfaces;

import com.example.mangoplate.src.home.models.HomeResponse;
import com.example.mangoplate.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {

    @GET("/event")
    @Headers("Content-Type: application/json")
    Call<HomeResponse> GetEvent(@Header ("x-access-token")String acessToken);
}
