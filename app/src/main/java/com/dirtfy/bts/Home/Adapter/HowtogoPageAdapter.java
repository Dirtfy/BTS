package com.dirtfy.bts.Home.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.dirtfy.bts.Home.Fragment.Home_one_way;
import com.dirtfy.bts.Home.Fragment.Home_round_trip;

import java.util.ArrayList;

public class HowtogoPageAdapter extends FragmentStatePagerAdapter {
    private int numberOfFragment;

    private ArrayList<Fragment> fragments;

    public HowtogoPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments, int numberOfFragment){
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.numberOfFragment = numberOfFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return numberOfFragment;
    }

    public Home_one_way getPost1_fragment() {
        return (Home_one_way) fragments.get(0);
    }

    public Home_round_trip getPost2_fragment() {
        return (Home_round_trip) fragments.get(1);
    }
}
