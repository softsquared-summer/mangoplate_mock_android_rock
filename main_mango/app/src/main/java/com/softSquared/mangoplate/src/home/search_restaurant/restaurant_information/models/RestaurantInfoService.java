package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models;

import android.content.Context;
import android.util.Log;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.interfaces.HomeActivityView;
import com.softSquared.mangoplate.src.home.search_restaurant.RestaurantRecyclerAdapter;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.RestaurantInfoRecyclerAdapter;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.RetaurantInformationLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.interfaces.RestaurantInfoRetrofitInterface;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
    private RecyclerView mInfoRestaurantRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RestaurantInfoRecyclerAdapter madapter;
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

        init();
        restaurantInfoRetrofitInterface.getRestaurants(X_ACCESS_TOKEN, mRestaurantId).enqueue(new Callback<RestaurantInfoResult>() {
            @Override
            public void onResponse(Call<RestaurantInfoResult> call, Response<RestaurantInfoResult> response) {
                mRestaurantInfoResult = response.body();
                Log.e("망고 안되네", "area");
                if (mRestaurantInfoResult.getResult() != null) {

                        if (response.code() == 200) {

                            if (mRestaurantInfoResult.getResult() != null) {

                                Log.e("망고 식당이름!", "" + mRestaurantInfoResult.getResult().getName());
                                Log.e("망고 식당이름!", "" + mRestaurantInfoResult.getResult().getAddress());
                                madapter.addItem(mRestaurantInfoResult.getResult());


                            }

                        } else {
                            mHomeActivityView.validateFailure(null);

                        }


                    }

                    madapter.notifyDataSetChanged();
                }

            @Override
            public void onFailure(Call<RestaurantInfoResult> call, Throwable t) {

            }


        });



    }


    private void init() {
        int numberOfColumns = 1;// 한줄에 2개의 컬럼을 추가
        mInfoRestaurantRecyclerView = mRetaurantInformationLayout.findViewById(R.id.recyclerview_informaiton);
        mGridLayoutManager = new GridLayoutManager(mContext, numberOfColumns);
        mInfoRestaurantRecyclerView.setLayoutManager(mGridLayoutManager);
        madapter = new RestaurantInfoRecyclerAdapter(mRetaurantInformationLayout);
        mInfoRestaurantRecyclerView.setAdapter(madapter);RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = GridLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisible >= totalItemCount - 1) {
                    madapter.addItem(mRestaurantInfoResult.getResult());
                }
            }
        };
        mInfoRestaurantRecyclerView.addOnScrollListener(onScrollListener);

    }
}

