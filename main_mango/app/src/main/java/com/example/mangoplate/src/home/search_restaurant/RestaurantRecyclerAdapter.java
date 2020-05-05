package com.example.mangoplate.src.home.search_restaurant;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mangoplate.R;
import com.example.mangoplate.src.home.HomeAcitivity;
import com.example.mangoplate.src.home.search_restaurant.models.RecyclerRestaurantData;
import com.example.mangoplate.src.home.search_restaurant.models.RestaurantResult;
import com.example.mangoplate.src.home.search_restaurant.models.RestaurantResultList;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<RestaurantResult> listData = new ArrayList<>();

    private HomeAcitivity mHomeAcitivity;
    RestaurantRecyclerAdapter(HomeAcitivity homeAcitivity)
    {
        this.mHomeAcitivity=homeAcitivity;

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_restaurantlist, parent, false);
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

    public void addItem(RestaurantResult data) {
        // 외부에서 item을 추가시킬 함수입니다.
//        listData.add(data);
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private String area;
        private String img;
        private String title;
        private String distance;
        private String seenNum;
        private String reviewNum;
        private float rating;
        private String ratingColor;

        ImageView img_res;
        TextView  title_res;
        TextView area_res;
        TextView seenNum_res;
        TextView reviewNum_res;
        TextView rating_res;


        ItemViewHolder(View itemView) {
            super(itemView);

            title_res=itemView.findViewById(R.id.title_restarant);
            img_res=itemView.findViewById(R.id.img_restarant);
            area_res=itemView.findViewById(R.id.area_restaurant); // 얘는 settext 할 때 거리를 붙여야함
            seenNum_res=itemView.findViewById(R.id.seen_num_restarant);
            reviewNum_res=itemView.findViewById(R.id.review_num_restarant);
            rating_res=itemView.findViewById(R.id.rating_restarant);
//            textView1 = itemView.findViewById(R.id.textView1);
//            textView2 = itemView.findViewById(R.id.textView2);
//            imageView = itemView.findViewById(R.id.imageView);
        }

        @SuppressLint("ResourceAsColor")
        void onBind(RestaurantResult data) {
            if(data.getImg()!=null)
            {
//                Glide.with(mHomeAcitivity).load(img).into(img_res);
                Glide.with(itemView.getContext())
                        .load(data.getImg())
                        .into(img_res);

                Log.e("뭐가 문제요",""+data.getImg());
            }
            title_res.setText(""+data.getTitle());
            area_res.setText(""+data.getArea()+"-"+data.getDistance());
            seenNum_res.setText(""+data.getSeenNum());
            reviewNum_res.setText(""+data.getReviewNum());
            rating_res.setText(""+data.getRating());
            if(data.getRatingColor().equals("gray"))
            {
                rating_res.setTextColor(R.color.grey_200);

            }else if(data.getRatingColor().equals("orange"))
            {
                rating_res.setTextColor(R.color.Mangoplate_orange);

            }else{


            }
//            textView1.setText(data.getTitle());
//            textView2.setText(data.getContent());
//            imageView.setImageResource(data.getResId());
        }
    }
}