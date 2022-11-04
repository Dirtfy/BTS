package com.dirtfy.bts.Pay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.dirtfy.bts.R;
import com.dirtfy.bts.volley.Route;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Pay_ViewHolder>{
    private Route route;
    private Context context;

    public RecyclerViewAdapter(Route route, Context context){
        this.route = route;
        this.context = context;
    }

    @NonNull
    @Override
    public Pay_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_recycler_item, parent, false);
        return new Pay_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pay_ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return route.getTransitList().size();
    }

    class Pay_ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_startstation;
        TextView tv_starttime;
        TextView tv_arrivetime;
        TextView tv_cost;
        TextView tv_type;

        public Pay_ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_startstation = itemView.findViewById(R.id.tv_departstation);
            tv_starttime = itemView.findViewById(R.id.start_time);
            tv_arrivetime = itemView.findViewById(R.id.arrive_time);
            tv_cost = itemView.findViewById(R.id.tv_cost);
            tv_type = itemView.findViewById(R.id.tv_type);

            itemView.setOnClickListener(view -> {
                String postNumber = String.valueOf(route.getTransitList().get(getAdapterPosition()));
            });
        }
    }
}

