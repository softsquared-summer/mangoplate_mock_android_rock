package com.softSquared.mangoplate.src.home.search_restaurant;

import android.content.Context;
import android.util.Log;

import com.softSquared.mangoplate.src.ApplicationClass;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.interfaces.HomeActivityView;

import com.softSquared.mangoplate.src.home.search_restaurant.interfaces.SearchRestaurantViewFragment;
import com.softSquared.mangoplate.src.home.search_restaurant.interfaces.SearchRetrofitInterface;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.softSquared.mangoplate.src.home.search_restaurant.models.RestaurantResultResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softSquared.mangoplate.src.ApplicationClass.getRetrofit;
import static com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout.outputDatas;

public class SearchRestaurantService {
    private HomeActivityView mHomeActivityView;

    private HomeAcitivity mHomeAcitivity;
    private RestaurantResultResponse mRestaurantResultResponse;
    private RecyclerView searchRestaurantRecyclerView;
    private RestaurantRecyclerAdapter restaurantRecyclerAdapter;
    private SearchRestaurantViewFragment mSearchRestaurantViewFragment;
    private GridLayoutManager mGridLayoutManager;
    private RestaurantRecyclerAdapter madapter;
    private Context mContext;
    private String area =""; // 쿼리 ?area= .......;
    private ArrayList<RestaurantResult> listData = new ArrayList<>();

    SearchRestaurantService( SearchRestaurantViewFragment searchRestaurantViewFragment, final Context context) {
        this.mSearchRestaurantViewFragment = searchRestaurantViewFragment;
        this.mContext = context;
    }

    // area 쿼리에 넣기 위한 작업
    void makeAreaString() {


        // outputDatas 널 문제 체크 . 싱글톤으로
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
        Log.e("망고 lat", "" + ApplicationClass.lat);
        Log.e("망고 lng", "" + ApplicationClass.lng);
        Log.e("망고 main", "main");
        Log.e("망고 area", area);

        searchRetrofitInterface.getRestaurants(X_ACCESS_TOKEN,  ApplicationClass.lat,   ApplicationClass.lng, "main", "금천구").enqueue(new Callback<RestaurantResultResponse>() {
            @Override
            public void onResponse(Call<RestaurantResultResponse> call, Response<RestaurantResultResponse> response) {
                mRestaurantResultResponse = response.body();

                if (mRestaurantResultResponse.getResult() != null && mRestaurantResultResponse.getResult().size() > 0) {

                        if (response.code() == 200) {
                            mSearchRestaurantViewFragment.successUpdateRecyclerView(mRestaurantResultResponse);
                            Log.e("왜 안나와?", "ㅇ?" );

                        } else {
                            mHomeActivityView.validateFailure(null);
                            if (mRestaurantResultResponse.getResult() != null) {

                                Log.e("실패", "ㅎㅎㅎ");

                            }
                        }


                    }

                }





            @Override
            public void onFailure(Call<RestaurantResultResponse> call, Throwable t) {

            }
        });

    }

    void tryStartRestaurantsList() {

        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        Log.e("망고 lat", "" + ApplicationClass.lat);
        Log.e("망고 lng", "" + ApplicationClass.lng);
        Log.e("망고 main", "main");
        Log.e("망고 area", area);
        searchRetrofitInterface.toString();


        searchRetrofitInterface.getStartRestaurants(X_ACCESS_TOKEN, (float) ApplicationClass.lat, (float) ApplicationClass.lng, "main").enqueue(new Callback<RestaurantResultResponse>() {
            @Override
            public void onResponse(Call<RestaurantResultResponse> call, Response<RestaurantResultResponse> response) {
                mRestaurantResultResponse = response.body();

                if (mRestaurantResultResponse.getResult() != null && mRestaurantResultResponse.getResult().size() > 0) {

                        if (response.code() == 200) {

                            if (mRestaurantResultResponse.getResult() != null) {

                                mSearchRestaurantViewFragment.successUpdateRecyclerView(mRestaurantResultResponse);
//                                Log.e("망고 식당이름", "" + result.getTitle());
//                                Log.e("망고 지역", "" + result.getArea());
//                                Log.e("망고 이미지url", "" + result.getImg());
//                                Log.e("망고 rating", "" + result.getRating());
//                                Log.e("망고 본 사람수", "" + result.getSeenNum());


                            }

                        } else {
                            mHomeActivityView.validateFailure(null);
                            if (mRestaurantResultResponse.getResult() != null) {

                                Log.e("실패", "ㅎㅎㅎ");

                            }
                        }




                }

//            }


            }

            @Override
            public void onFailure(Call<RestaurantResultResponse> call, Throwable t) {

            }
        });

    }


}
