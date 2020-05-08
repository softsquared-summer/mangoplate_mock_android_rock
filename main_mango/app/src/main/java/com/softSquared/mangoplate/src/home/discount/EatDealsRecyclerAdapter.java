package com.softSquared.mangoplate.src.home.discount;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softSquared.mangoplate.R;
import com.softSquared.mangoplate.src.home.HomeAcitivity;
import com.softSquared.mangoplate.src.home.discount.models.EatDealsResult;
import com.softSquared.mangoplate.src.home.mystatus.events.EventsActivity;
import com.softSquared.mangoplate.src.home.mystatus.events.models.EventsResult;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class EatDealsRecyclerAdapter extends RecyclerView.Adapter<EatDealsRecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<EatDealsResult> listData = new ArrayList<>();

    private HomeAcitivity homeAcitivity;

    EatDealsRecyclerAdapter(HomeAcitivity homeAcitivity) {
        this.homeAcitivity = homeAcitivity;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_eatdeals, parent, false);
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

    public void addItem(EatDealsResult data) {
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {


        ImageView imageUrl;
        TextView status;
        TextView percent;
        TextView originalPrice;
        TextView salePrice;
        TextView title;
        TextView item;
        TextView description;

        ItemViewHolder(View itemView) {
            super(itemView);

            imageUrl = itemView.findViewById(R.id.imageUrl_eatdeals);

            status = itemView.findViewById(R.id.status_eatdeals);
            percent = itemView.findViewById(R.id.percent_eatdeals);
            originalPrice = itemView.findViewById(R.id.originalPrice_eatDeals);
            salePrice = itemView.findViewById(R.id.salePrice_eatDeals);
            title = itemView.findViewById(R.id.title_eatdeals);
            item = itemView.findViewById(R.id.item_eatdeals);
            description = itemView.findViewById(R.id.description_eatdeals);


        }

        void onBind(EatDealsResult data) {
            if (data.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(data.getImageUrl()).placeholder(R.drawable.loading)
                        .into(imageUrl);

                Log.e("뭐가 문제요", "" + data.getImageUrl());
            }


            if (data.getStatus() == null) {
                status.setVisibility(View.INVISIBLE);
                percent.setVisibility(View.INVISIBLE);

            }
            if (data.getStatus() != null &&data.getStatus().equals("재입고")) {
                status.setText(data.getStatus());
                status.setTextSize(10);
                status.setBackgroundResource(R.drawable.btn_shape_yellow_eatdeals);
             status.setTextColor(homeAcitivity.getResources().getColor(R.color.black));
            }
            if (data.getStatus() != null && data.getStatus().equals("HOT")) {
                status.setText(data.getStatus());
                status.setBackgroundResource(R.drawable.btn_shape_red_eatdeals);
            }


            if(data.getStatus() != null && data.getStatus().equals("NEW"))
            {
                status.setText(data.getStatus());
            }


            if (data.getPercent() == null) {
                percent.setVisibility(View.INVISIBLE);
                salePrice.setVisibility(View.INVISIBLE);
                originalPrice.setVisibility(View.INVISIBLE);
                description.setText("판매가 종료되었습니다.");
            } else {

                percent.setText(data.getPercent());
                if (data.getDescription() == null) {
                    description.setText(data.getDescription());

                } else {

                    description.setVisibility(View.INVISIBLE);
                }
                salePrice.setText("₩ " + data.getSalePrice());

            }
            originalPrice.setText("₩ " + data.getOriginalPrice());
            originalPrice.setPaintFlags(originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //취소선


            title.setText(data.getTitle());
            item.setText(data.getItem());


        }
    }
}