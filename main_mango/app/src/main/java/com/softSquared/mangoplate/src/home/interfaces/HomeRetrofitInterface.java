package com.softSquared.mangoplate.src.home.interfaces;

import com.softSquared.mangoplate.src.home.models.HomeEventResponse;
import com.softSquared.mangoplate.src.home.models.HomeEventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {

    @GET("/event")
    @Headers("Content-Type: application/json")
    Call<HomeEventResponse> GetEvent(@Header ("x-access-token")String acessToken);

    @GET("/events")
    @Headers("Content-Type: application/json")
    Call<HomeEventsResponse> GetEvents(@Query("type") String type , @Header ("x-access-token")String acessToken);
}
