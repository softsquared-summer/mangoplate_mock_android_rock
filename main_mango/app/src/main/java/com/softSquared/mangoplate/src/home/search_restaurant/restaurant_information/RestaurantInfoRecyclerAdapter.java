package com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoResult;
import com.softSquared.mangoplate.src.home.search_restaurant.restaurant_information.models.RestaurantInfoResultList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantInfoRecyclerAdapter extends RecyclerView.Adapter<RestaurantInfoRecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<RestaurantInfoResultList> listData = new ArrayList<>();

    private RetaurantInformationLayout mRetaurantInformationLayout;

    public RestaurantInfoRecyclerAdapter(RetaurantInformationLayout retaurantInformationLayout) {
        this.mRetaurantInformationLayout = retaurantInformationLayout;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_restaurntinformation, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.


        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(RestaurantInfoResultList data) {
        // 외부에서 item을 추가시킬 함수입니다.
//        listData.add(data);
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private List<RestaurantInfoResultList.images> images = null;

        private Double lat;
        private Double lng;
        private TextView name;
        private TextView seenNum;
        private TextView reviewNum;
        private TextView starNum;
        private TextView rating;
        private TextView ratingColor;
        private TextView userStar;
        private TextView address;
        private TextView oldAddress;
        private TextView phone;
        private int userId;
        private TextView userName;
        private TextView userProfileUrl;
        private TextView infoUpdate;
        private TextView infoTime;
        private TextView infoHoliday;
        private TextView infoPrice;
        private TextView infoDescription;

        private TextView infoKind;
        private TextView infoParking;
        private TextView infoSite;

        ImageView imageView_one,imageView_two,imageView_three,imageView_four;
        ItemViewHolder(final View itemView) {
            super(itemView);
             imageView_one=itemView.findViewById(R.id.img_one);
             imageView_two=itemView.findViewById(R.id.img_two);
             imageView_three=itemView.findViewById(R.id.img_three);
             imageView_four=itemView.findViewById(R.id.img_four);

              name=itemView.findViewById(R.id.name_information);
              seenNum=itemView.findViewById(R.id.seen_num_information);
              reviewNum=itemView.findViewById(R.id.review_num_information);
              starNum=itemView.findViewById(R.id.star_num_information);
              rating=itemView.findViewById(R.id.rating_information);
//              ratingColor=itemView.findViewById(R.id.rating_information);
//              userStar=itemView.findViewById(R.id.star_num_information);
              address=itemView.findViewById(R.id.address);
              oldAddress=itemView.findViewById(R.id.old_address);
             int userId;
              infoUpdate=itemView.findViewById(R.id.infoUpdate);
              infoTime=itemView.findViewById(R.id.info_time);
              infoHoliday=itemView.findViewById(R.id.info_holiday);
              infoPrice=itemView.findViewById(R.id.info_price);
//             TextView infoParking=itemView.findViewById(R.id.info);
//             TextView infoSite=itemView.findViewById();
//


        }

        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        void onBind(RestaurantInfoResultList data) {
            if (data.getImages().get(0)!= null) {
//                Glide.with(mHomeAcitivity).load(img).into(img_res);


                Log.e("망고 식당이름!", "" + data.getImages().get(0)
                );
                Glide.with(itemView.getContext())
                        .load(data.getImages().get(0).getImageUrl()).placeholder(R.drawable.loading)
                        .into(imageView_one);
            }
            if (data.getImages().get(1)!= null){
                Glide.with(itemView.getContext())
                        .load(data.getImages().get(1).getImageUrl()).placeholder(R.drawable.loading)
                        .into(imageView_two);
            }
            if (data.getImages().get(2)!= null){
                Glide.with(itemView.getContext())
                        .load(data.getImages().get(2).getImageUrl()).placeholder(R.drawable.loading)
                        .into(imageView_three);
            }
            if (data.getImages().get(3)!= null){
                Glide.with(itemView.getContext())
                        .load(data.getImages().get(3).getImageUrl()).placeholder(R.drawable.loading)
                        .into(imageView_four);
            }

            Log.e("망고 식당이름!", "" + data.getName());
              name.setText(""+data.getName());
              seenNum.setText(""+data.getSeenNum());
              reviewNum.setText(""+data.getReviewNum());
              starNum.setText(""+data.getStarNum());
              rating.setText(""+data.getRating());
//              userStar.setText(""+data.getUserStar());
              address.setText(""+data.getAddress());
              oldAddress.setText(""+data.getOldAddress());
//              phone.setText(""+data.getPhone());
//              userName.setText(""+data.getUserName());
            if (data.getRatingColor().equals("gray")) {
                rating.setTextColor(Color.parseColor("#757575"));

            } else if (data.getRatingColor().equals("orange")) {
                rating.setTextColor(Color.parseColor("#FF7101"));

            }
              infoUpdate.setText(""+data.getInfoUpdate());
              infoTime.setText(""+data.getInfoTime());
              infoHoliday.setText(""+data.getInfoHoliday());
              infoPrice.setText(""+data.getInfoPrice());

        }
    }
}