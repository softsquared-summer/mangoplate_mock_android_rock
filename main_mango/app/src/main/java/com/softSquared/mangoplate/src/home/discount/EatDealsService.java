package com.softSquared.mangoplate.src.home.discount;

import android.util.Log;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.discount.interfaces.EatDealsRetrofitInterface;
import com.softSquared.mangoplate.src.home.discount.models.EatDealsResponse;
import com.softSquared.mangoplate.src.home.discount.models.EatDealsResult;
import com.softSquared.mangoplate.src.home.interfaces.HomeActivityView;
import com.softSquared.mangoplate.src.home.mystatus.events.EventsActivity;
import com.softSquared.mangoplate.src.home.mystatus.events.models.EventsResponse;

import java.util.HashMap;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softSquared.mangoplate.src.ApplicationClass.getRetrofit;

public class EatDealsService {
    private HomeAcitivity mHomeActivity;


    private HashMap<String, String> mEventresponse;
    private EatDealsRecyclerAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    RecyclerView mRecyclerViewEatDeals;
    EatDealsService(HomeAcitivity mHomeActivity) {
        this.mHomeActivity = mHomeActivity;
    }



    void tryGetEdeals() {

        final EatDealsRetrofitInterface eatDealsRetrofitInterface = getRetrofit().create(EatDealsRetrofitInterface.class);
        Log.e("성공", "" + "돌긴 도니 ");
        eatDealsRetrofitInterface.toString();

        eatDealsRetrofitInterface.GetEdeals( X_ACCESS_TOKEN).enqueue(new Callback<EatDealsResponse>() {
            @Override
            public void onResponse(Call<EatDealsResponse> call, Response<EatDealsResponse> response) {
                final EatDealsResponse eventsResponse = response.body();

init();
                if (eventsResponse.getResult() != null && eventsResponse.getResult().size() > 0) {
                    for (EatDealsResult eatDealsResult : eventsResponse.getResult()) {

                        if (response.code() == 200) {

                            if (eventsResponse.getResult() != null) {

                                Log.e("Eat딜 홈리스폰스", "" + eatDealsResult.getImageUrl());

                                Log.e("이벤트 광고 이미지URL", "" + eatDealsResult.getStatus());
                                mAdapter.addItem(eatDealsResult);
                            }

                        }


                    }
                    mAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<EatDealsResponse> call, Throwable t) {

            }


        });
    }
    private void init() {
        int numberOfColumns = 1;// 한줄에 2개의 컬럼을 추가
        mRecyclerViewEatDeals = mHomeActivity.findViewById(R.id.recyclerview_eatdeals);
        mGridLayoutManager = new GridLayoutManager(mHomeActivity, numberOfColumns);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewEatDeals.setLayoutManager(mGridLayoutManager);
        mAdapter = new EatDealsRecyclerAdapter(mHomeActivity);
        mRecyclerViewEatDeals.setAdapter(mAdapter);
    }
}
