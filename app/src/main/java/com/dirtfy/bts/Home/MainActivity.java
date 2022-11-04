package com.dirtfy.bts.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.dirtfy.bts.Home.Fragment.Home_Buyticket;
import com.dirtfy.bts.Home.Fragment.Home_Checkticket;
import com.dirtfy.bts.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView main_bottom;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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