package com.dirtfy.bts.routePage;

import static com.dirtfy.bts.Home.MainActivity.routeArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dirtfy.bts.R;
import com.dirtfy.bts.routePage.adapter.RecyclerViewAdapter;
import com.dirtfy.bts.volley.Route;

import java.util.ArrayList;

public class RoutePageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_page);

        recyclerView = findViewById(R.id.route_page_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(routeArrayList);
        recyclerView.setAdapter(adapter);
    }
}