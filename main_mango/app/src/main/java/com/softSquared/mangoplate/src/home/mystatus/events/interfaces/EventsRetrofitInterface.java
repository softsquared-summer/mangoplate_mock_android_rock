package com.softSquared.mangoplate.src.home.mystatus.events.interfaces;

import com.softSquared.mangoplate.src.home.mystatus.events.models.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EventsRetrofitInterface {

    @GET("/events")
    @Headers("Content-Type: application/json")
    Call<EventsResponse> GetEvents(@Query("type") String type, @Header("x-access-token") String acessToken);
}
