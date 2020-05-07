package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models;

import android.content.Context;
import android.util.Log;

import com.softSquared.mangoplate.src.home.interfaces.HomeActivityView;
import com.softSquared.mangoplate.src.home.search_restaurant.RestaurantRecyclerAdapter;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.RetaurantInformationLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.interfaces.RestaurantInfoRetrofitInterface;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softSquared.mangoplate.src.ApplicationClass.getRetrofit;
import static com.softSquared.mangoplate.src.home.search_restaurant.SearchRestaurantFragment.lat;
import static com.softSquared.mangoplate.src.home.search_restaurant.SearchRestaurantFragment.lng;
import static com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout.outputDatas;

public class RestaurantInfoService {
    private HomeActivityView mHomeActivityView;

    private RetaurantInformationLayout mRetaurantInformationLayout;
    private RestaurantInfoResult mRestaurantInfoResult;
    private RecyclerView searchRestaurantRecyclerView;
    private RestaurantRecyclerAdapter restaurantRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;
    private RestaurantRecyclerAdapter madapter;
    private Context mContext;
    private String area = ""; // 쿼리 ?area= .......;
    private ArrayList<RestaurantResult> listData = new ArrayList<>();
    int mRestaurantId;

    public RestaurantInfoService(final RetaurantInformationLayout mRetaurantInformationLayout,int restaurantId) {
        this.mRetaurantInformationLayout = mRetaurantInformationLayout;
        this.mRestaurantId =restaurantId;
    }



    public void tryGetRestaurantInfoList() {

        final RestaurantInfoRetrofitInterface restaurantInfoRetrofitInterface = getRetrofit().create(RestaurantInfoRetrofitInterface.class);

        restaurantInfoRetrofitInterface.toString();

//        init();
        restaurantInfoRetrofitInterface.getRestaurants(X_ACCESS_TOKEN, 1).enqueue(new Callback<RestaurantInfoResult>() {
            @Override
            public void onResponse(Call<RestaurantInfoResult> call, Response<RestaurantInfoResult> response) {
                mRestaurantInfoResult = response.body();
                Log.e("망고 안되네", "area");
                if (mRestaurantInfoResult.getResult() != null) {

                        if (response.code() == 200) {

                            if (mRestaurantInfoResult.getResult() != null) {

                                Log.e("망고 식당이름!", "" + mRestaurantInfoResult.getResult().getName());
                                Log.e("망고 식당이름!", "" + mRestaurantInfoResult.getResult().getAddress());
//                                madapter.addItem(result);


                            }

                        } else {
                            mHomeActivityView.validateFailure(null);

                        }


                    }

//                    madapter.notifyDataSetChanged();
                }

            @Override
            public void onFailure(Call<RestaurantInfoResult> call, Throwable t) {

            }


        });



    }
}

//    private void init() {
//        int numberOfColumns = 2;// 한줄에 2개의 컬럼을 추가
//        searchRestaurantRecyclerView = mHomeAcitivity.findViewById(R.id.fragment_recyclerView_searchRestaurant);
//        mGridLayoutManager = new GridLayoutManager(mContext, numberOfColumns);
//        searchRestaurantRecyclerView.setLayoutManager(mGridLayoutManager);
//        madapter = new RestaurantRecyclerAdapter(mHomeAcitivity);
//        searchRestaurantRecyclerView.setAdapter(madapter);
//    }

