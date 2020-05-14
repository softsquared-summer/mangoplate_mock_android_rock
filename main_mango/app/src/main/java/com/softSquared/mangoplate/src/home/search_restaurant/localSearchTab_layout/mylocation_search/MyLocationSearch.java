package com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.mylocation_search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.ApplicationClass;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.LocalSearchTabLayout;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.mylocation_search.interfaces.MylocationSearchRetrofitInterface;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.Result;
import com.softSquared.mangoplate.src.home.search_restaurant.localSearchTab_layout.seoul_south.models.ResultList;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softSquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;

public class MyLocationSearch extends Fragment {
    LocalSearchTabLayout localSearchTabLayout;
    ViewGroup mView;
    TabLayout mTabLayout;


    int i = 0;
    int j = 0;
    ArrayList<Integer> textViewIdValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_seoul_south, container, false);
        fetchDistricts(1);


        return mView;
    }

    public MyLocationSearch(LocalSearchTabLayout localSearchTabLayout) {
        this.localSearchTabLayout = localSearchTabLayout;
    }

    private void fetchDistricts(int districts) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApplicationClass.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        MylocationSearchRetrofitInterface searchTapRetrofitInterface = retrofit.create(MylocationSearchRetrofitInterface.class);

        Log.e("내 근처 위도,경도 ", "lat " +  ApplicationClass.lat + "lng" +  ApplicationClass. lng);
        searchTapRetrofitInterface.getDistrictsByRock(X_ACCESS_TOKEN,  ApplicationClass.lat,  ApplicationClass.lng).enqueue(new Callback<ResultList>() {
            int checkNumber = 0;// 얘는 포문 돌아가는 숫자를 세기 위해서 존재.


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                if (response.code() == 200) {
                    final ResultList resultList = response.body();
                    LayoutInflater inflater = (LayoutInflater) localSearchTabLayout.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final GridLayout gridLayout = mView.findViewById(R.id.seoulSouth_dynamic_layout);
                    if (resultList.result != null && resultList.result.size() > 0) {
                        for (Result result : resultList.result) {
                            Log.e("성공", "" + result.id);
                            Log.e("성공", "" + result.name);


                            final boolean[] textViewFlag = new boolean[resultList.result.size() + 1];// 버튼 온 오프 체크
//                            TextView[] textView=new TextView[resultList.result.size()+1];
                            for (int i = 0; i < resultList.result.size() + 1; i++) {
                                textViewFlag[i] = true;

                            }


                            final TextView textView = new TextView(getContext());
                            final FrameLayout frameLayout = new FrameLayout(getContext());
                            textView.setText(result.name);
                            FrameLayout.LayoutParams paramsOne = new FrameLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                            paramsOne.setMargins(5, 10, 5, 10);
                            textView.setLayoutParams(paramsOne);
                            textView.setForegroundGravity(Gravity.END);
                            textView.setGravity(Gravity.CENTER);

                            int inputId = View.generateViewId(); // 고정으로 해야돼
                            textView.setId(inputId);
                            textViewIdValue = new ArrayList<>();
                            textViewIdValue.add(textView.getId());
                            Log.e("id값좀 보자", "" + inputId);
                            Log.e("TextViewid값좀 보자", "" + textView.getId());
                            ArrayList<Boolean> checkOnOffTextView = new ArrayList<>();

                            textView.setWidth(310);
                            textView.setHeight(90);
                            textView.setTextColor(getResources().getColor(R.color.offborder));
                            textView.setBackgroundResource(R.drawable.off_rouned_border_textview);
                            final ImageView imageView = new ImageView(getContext());
                            imageView.setImageResource(R.drawable.checkimage);
                            imageView.setId(View.generateViewId());
                            int imagviewId = imageView.getId();
//                            params.setMarginStart(100);
//                            imageView.setForegroundGravity(Gravity.END| Gravity.TOP);


                            @SuppressLint("RtlHardcoded") FrameLayout.LayoutParams paramsTwo = new FrameLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.TOP | Gravity.RIGHT);
//                           레이아웃을 맞춰줘야 한다.
//                           https://stackoverflow.com/questions/8005526/setting-of-imageviews-gravity-to-the-center-in-android-programmatically 리니어인 경우 이 경우 참조
                            paramsTwo.setMargins(0, 9, 9, 0);
                            imageView.setLayoutParams(paramsTwo);
                            imageView.getLayoutParams().height = 30;
                            imageView.getLayoutParams().width = 30;
                            imageView.requestLayout();
                            frameLayout.setForegroundGravity(Gravity.CENTER);
                            frameLayout.addView(textView);


                            final TextView adjustment = localSearchTabLayout.findViewById(R.id.adjustment); // 클릭시 버튼 색깔 바꾸기
                            final TextView cancelAllbutton = localSearchTabLayout.findViewById(R.id.cancel_allbutton);

                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (textViewFlag[checkNumber]) {

                                        LocalSearchTabLayout.mAdjustmnetColorChanger++;
                                        adjustment.setBackgroundResource(R.drawable.adjustment_on_rouned_border_textview);
                                        adjustment.setTextColor(getResources().getColor(R.color.white));
                                        textView.setBackgroundResource(R.drawable.on_rouned_border_textview);
                                        cancelAllbutton.setTextColor(getResources().getColor(R.color.Mangoplate_orange));
                                        frameLayout.addView(imageView);
                                        textView.setTextColor(getResources().getColor(R.color.Mangoplate_orange));
                                        textViewFlag[checkNumber] = false;
                                        LocalSearchTabLayout.outputDatas.add(textView.getText().toString());
                                    } else {
                                        LocalSearchTabLayout.mAdjustmnetColorChanger--;
                                        if (LocalSearchTabLayout.mAdjustmnetColorChanger == 0) {
                                            adjustment.setBackgroundResource(R.drawable.adjustment_off_rouned_border_textview);
                                            adjustment.setTextColor(getResources().getColor(R.color.white));
                                            cancelAllbutton.setTextColor(getResources().getColor(R.color.offborder));
                                        }
                                        textView.setBackgroundResource(R.drawable.off_rouned_border_textview);
                                        ((ViewGroup) imageView.getParent()).removeView(imageView);
                                        textView.setTextColor(getResources().getColor(R.color.offborder));
                                        textViewFlag[checkNumber] = true;
                                        LocalSearchTabLayout.outputDatas.remove(textView.getText().toString());
                                    }
                                }

                            });
                            gridLayout.setRowCount(resultList.result.size());
                            gridLayout.setColumnCount(2);
                            gridLayout.setOrientation(GridLayout.VERTICAL);

                            if (j == 2) {
                                j = 0;
                                i++;
                                gridLayout.addView(frameLayout, new GridLayout.LayoutParams(
                                        GridLayout.spec(i, GridLayout.CENTER, 1f),
                                        GridLayout.spec(j, GridLayout.CENTER, 1f)));


                            } else {

                                gridLayout.addView(frameLayout, new GridLayout.LayoutParams(
                                        GridLayout.spec(i, GridLayout.CENTER, 1f),
                                        GridLayout.spec(j, GridLayout.CENTER, 1f)));
                                // weight를 줘야함
                                Log.e("뭐하는 거야 당신", "" + i + j);

                                j++;

                            }
                            checkNumber++;


                        }

                    }
                    if (resultList.result != null) {


                    } else {

                        Log.e("실패", "응");
                    }

                }


            }


            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {

            }
        });
    }
}
