package com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangoplate.R;
import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.interfaces.SearchTapRetrofitInterface;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.Result;
import com.example.mangoplate.src.home.search_restaurant.searchTab_layout.seoul_south.models.ResultList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeoulSouth extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_seoul_south, container, false);
        fetchDistricts(1);
        return view;
    }

    private void fetchDistricts(int districts) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApplicationClass.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        SearchTapRetrofitInterface searchTapRetrofitInterface = retrofit.create(SearchTapRetrofitInterface.class);
        searchTapRetrofitInterface.getDistrictsByRock().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                if (response.code() == 200) {
                    ResultList resultList = response.body();
                    if (resultList.result != null && resultList.result.size() > 0) {
                        for (Result result : resultList.result) {
                               Log.e("성공",""+result.id);
                               Log.e("성공",""+result.name);
                        }

                    }
                    if(resultList.result!=null)
                    {


                    }else{

                        Log.e("실패","응");
                    }

                }
            }



            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {

            }
        });
    }
}
