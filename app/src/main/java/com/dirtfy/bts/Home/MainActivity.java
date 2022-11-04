package com.dirtfy.bts.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.dirtfy.bts.Home.Fragment.Home_Buyticket;
import com.dirtfy.bts.Home.Fragment.Home_Checkticket;
import com.dirtfy.bts.R;
import com.dirtfy.bts.directionsApi.RouteHelper;
import com.dirtfy.bts.volley.Cmm;
import com.dirtfy.bts.volley.Route;
import com.dirtfy.bts.volley.Transit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView main_bottom;
    FragmentTransaction fragmentTransaction;
    RouteHelper routeHelper;

    JSONObject result;

    public static ArrayList<Route> routeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        routeHelper = new RouteHelper();
        try {
            result = new JSONObject(routeHelper.sendClick("동대구역", "철원"));
            routeArrayList = Cmm.getRoutes(result);
            for(int i = 0;i < routeArrayList.size();i++){
                Route route = routeArrayList.get(i);
                Log.d("Dirtfy_test", "\n#"+i+"\n"+route.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        main_bottom = findViewById(R.id.bottomNavigationView);
        main_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                setFragment(item.getItemId());
                return true;
            }
        });
        setFragment(R.id.bottom_buyticket);
    }

    public void setFragment(int n) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (n) {
            case R.id.bottom_buyticket:
                Home_Buyticket Buyticket = new Home_Buyticket();
                fragmentTransaction.replace(R.id.main_layout, Buyticket).commitAllowingStateLoss();
                break;
            case R.id.bottom_checkticket:
                Home_Checkticket Checkticket = new Home_Checkticket();
                fragmentTransaction.replace(R.id.main_layout,Checkticket).commit();
                break;
        }
    }
}