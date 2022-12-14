package com.dirtfy.bts.Pay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
        Pay_ViewHolder pay_viewHolder = new Pay_ViewHolder(view);
        return pay_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Pay_ViewHolder holder, int position) {
        holder.tv_startstation.setText(route.getTransitList().get(position).getDepartureStop());
        holder.tv_starttime.setText(route.getTransitList().get(position).getDepartureTime());
        holder.tv_arrivetime.setText(route.getTransitList().get(position).getArrivalTime());
        holder.tv_cost.setText(route.getTransitList().get(position).getCost());
        holder.tv_type.setText(route.getTransitList().get(position).getType());

        if(route.getTransitList().get(position).getType().equals("CITY_BUS")
                || route.getTransitList().get(position).getType().equals("BUS"))
            holder.iv.setImageResource(R.drawable.ic_baseline_directions_bus_filled_24);
        else
            holder.iv.setImageResource(R.drawable.ic_baseline_train_24);
    }

    @Override
    public int getItemCount() {
        return route.getTransitList().size();
    }

    class Pay_ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_startstation;
        public TextView tv_starttime;
        public TextView tv_arrivetime;
        public TextView tv_cost;
        public TextView tv_type;
        ImageView iv;

        public Pay_ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_line);
            tv_startstation = itemView.findViewById(R.id.tv_departstation);
            tv_starttime = itemView.findViewById(R.id.start_time);
            tv_arrivetime = itemView.findViewById(R.id.arrive_time);
            tv_cost = itemView.findViewById(R.id.tv_cost);
            tv_type = itemView.findViewById(R.id.tv_type);
        }
    }
}

