package com.softSquared.mangoplate.src.home.mystatus.events;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.softSquared.mangoplate.src.home.mystatus.events.models.EventsResult;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<EventsResult> listData = new ArrayList<>();

    private EventsActivity mEventsActivity;
    EventsRecyclerAdapter(EventsActivity eventsActivity)
    {
        this.mEventsActivity=eventsActivity;

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_eventslist, parent, false);
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

    public void addItem(EventsResult data) {
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {


        ImageView img_res;
        TextView  title_res;
        TextView date_res;
        ConstraintLayout backgruond_recyclerview;

        ItemViewHolder(View itemView) {
            super(itemView);

            title_res=itemView.findViewById(R.id.title_events);
            img_res=itemView.findViewById(R.id.image_events);
            backgruond_recyclerview=itemView.findViewById(R.id.ConstraintLayout_recyclerview_events);
            date_res=itemView.findViewById(R.id.dates_events);

        }

        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        void onBind(EventsResult data) {
            String end="종료";
            if(data.getImageUrl()!=null)
            {
//                Glide.with(mHomeAcitivity).load(img).into(img_res);
                Glide.with(itemView.getContext())
                        .load(data.getImageUrl()).placeholder(R.drawable.loading)
                        .into(img_res);

                Log.e("뭐가 문제요",""+data.getImageUrl());
            }
            if(data.getStatus() ==null)
            {
                title_res.setText(" "+data.getTitle());
                date_res.setText(" "+data.getDate());

            }else{
                title_res.setText(" "+data.getTitle());


                end=" 종료 "+data.getDate();
                SpannableStringBuilder endInput = new SpannableStringBuilder(end);
                endInput.setSpan(new ForegroundColorSpan(Color.parseColor("#FF7101")), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                date_res.setText(endInput);

                backgruond_recyclerview.setBackgroundResource(R.color.grey_500);

            }

        }
    }
}