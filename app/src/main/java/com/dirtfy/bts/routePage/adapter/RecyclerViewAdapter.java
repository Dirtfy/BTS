package com.dirtfy.bts.routePage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dirtfy.bts.Pay.Pay_procedure;
import com.dirtfy.bts.R;
import com.dirtfy.bts.volley.Route;
import com.dirtfy.bts.volley.Transit;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<Route> routeArrayList;
    Context context;

    public RecyclerViewAdapter(ArrayList<Route> routeArrayList, Context context){
        this.routeArrayList = routeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_page_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Route route = routeArrayList.get(position);
        ArrayList<Transit> transits = route.getTransitList();

        for(Transit t : transits){
            holder.dep_name_tv.setText(t.getDepartureStop());
            holder.dep_time_tv.setText(t.getDepartureTime());
            holder.arr_time_tv.setText(t.getArrivalTime());
            holder.type_tv.setText(t.getType());

            if(t.getType().equals("CITY_BUS") || t.getType().equals("BUS"))
                holder.icon_iv.setImageResource(R.drawable.ic_baseline_directions_bus_filled_24);
            else
                holder.icon_iv.setImageResource(R.drawable.ic_baseline_train_24);
        }
    }

    @Override
    public int getItemCount() {
        return routeArrayList.get(0).getTransitList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon_iv;
        TextView dep_name_tv;
        TextView dep_time_tv;
        TextView arr_time_tv;
        TextView type_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon_iv = itemView.findViewById(R.id.route_image);
            dep_name_tv = itemView.findViewById(R.id.route_dep_name_tv);
            dep_time_tv = itemView.findViewById(R.id.route_dep_time_tv);
            arr_time_tv = itemView.findViewById(R.id.route_arr_time_tv);
            type_tv = itemView.findViewById(R.id.route_type_tv);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Intent it = new Intent(context, Pay_procedure.class);
                it.putExtra("number", position);
                context.startActivity(it);
            });
        }
    }
}
