package com.dirtfy.bts.Pay;

import static com.dirtfy.bts.Home.MainActivity.routeArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dirtfy.bts.Pay.Adapter.RecyclerViewAdapter;
import com.dirtfy.bts.R;

public class Pay_procedure extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        context = this;
        recyclerView = findViewById(R.id.activity_my_post1_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Intent it = getIntent();
        int Number = it.getIntExtra("number",0);
        adapter = new RecyclerViewAdapter(routeArrayList.get(Number),context);
        recyclerView.setAdapter(adapter);
    }
}
