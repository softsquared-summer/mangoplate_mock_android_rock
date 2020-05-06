package com.example.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.util.Log;

import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.interfaces.HomeActivityView;

import com.example.mangoplate.src.home.search_restaurant.interfaces.SearchRetrofitInterface;
import com.example.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.example.mangoplate.src.home.search_restaurant.models.RestaurantResultList;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mangoplate.src.ApplicationClass.getRetrofit;
import static com.example.mangoplate.src.home.search_restaurant.SearchRestaurantFragment.lat;
import static com.example.mangoplate.src.home.search_restaurant.SearchRestaurantFragment.lng;
import static com.example.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout.outputDatas;

public class SearchRestaurantService {
    private HomeActivityView mHomeActivityView;

    private HomeAcitivity mHomeAcitivity;
    private RestaurantResultList mRestaurantResultList;
    private RecyclerView searchRestaurantRecyclerView;
    private RestaurantRecyclerAdapter restaurantRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;
    private RestaurantRecyclerAdapter madapter;
    private Context mContext;
    private String area = ""; // 쿼리 ?area= .......;
    private ArrayList<RestaurantResult> listData = new ArrayList<>();

    SearchRestaurantService(final HomeAcitivity homeAcitivity, final Context context) {
        this.mHomeAcitivity = homeAcitivity;
        this.mContext = context;
    }


    void makeAreaString() // area 쿼리에 넣기 위한 작업
    {


        for (int i = 0; i < outputDatas.size(); i++) {
            if (i != outputDatas.size() - 1) {
                area += outputDatas.get(i);
                area += ",";
            } else {
                area += outputDatas.get(outputDatas.size() - 1);
            }
            Log.e("이거", area);
        }
        tryGetRestaurantsList();
    }

    void tryGetRestaurantsList() {

        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        Log.e("망고 lat", "" + lat);
        Log.e("망고 lng", "" + lat);
        Log.e("망고 main", "main");
        Log.e("망고 area", area);
        searchRetrofitInterface.toString();

        init();
        searchRetrofitInterface.getRestaurants(X_ACCESS_TOKEN, (float) lat, (float) lng, "main", area).enqueue(new Callback<RestaurantResultList>() {
            @Override
            public void onResponse(Call<RestaurantResultList> call, Response<RestaurantResultList> response) {
                mRestaurantResultList = response.body();

                if (mRestaurantResultList.getResult() != null && mRestaurantResultList.getResult().size() > 0) {
                    for (RestaurantResult result : mRestaurantResultList.getResult()) {
                        if (response.code() == 200) {

                            if (mRestaurantResultList.getResult() != null) {

                                Log.e("망고 식당이름", "" + result.getTitle());
                                Log.e("망고 지역", "" + result.getArea());
                                Log.e("망고 이미지url", "" + result.getImg());
                                Log.e("망고 rating", "" + result.getRating());
                                Log.e("망고 본 사람수", "" + result.getSeenNum());
                                madapter.addItem(result);


                            }

                        } else {
                            mHomeActivityView.validateFailure(null);
                            if (mRestaurantResultList.getResult() != null) {

                                Log.e("실패", "ㅎㅎㅎ");

                            }
                        }


                    }

                    madapter.notifyDataSetChanged();
                }

//            }


            }

            @Override
            public void onFailure(Call<RestaurantResultList> call, Throwable t) {

            }
        });

    }

    private void init() {
        int numberOfColumns = 2;// 한줄에 2개의 컬럼을 추가
        searchRestaurantRecyclerView = mHomeAcitivity.findViewById(R.id.fragment_recyclerView_searchRestaurant);
        mGridLayoutManager = new GridLayoutManager(mContext, numberOfColumns);
        searchRestaurantRecyclerView.setLayoutManager(mGridLayoutManager);
        madapter = new RestaurantRecyclerAdapter(mHomeAcitivity);
        searchRestaurantRecyclerView.setAdapter(madapter);
    }
}
