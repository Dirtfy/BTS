package com.dirtfy.bts.Home.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dirtfy.bts.Home.Adapter.HowtogoPageAdapter;
import com.dirtfy.bts.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class Home_Buyticket extends Fragment {
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int currentTabIndex = 0;
    private ArrayList<Fragment> fragments;
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HowtogoPageAdapter pageAdater;
    private Context context;
    private RequestManager mGlideRequestManager;
    public Home_Buyticket() {
        // Required empty public constructor
    }

    public static Home_Buyticket newInstance(String param1, String param2) {
        Home_Buyticket fragment = new Home_Buyticket();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = getContext();
        mGlideRequestManager = Glide.with(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buyticket, container, false);
        tabLayout = view.findViewById(R.id.buyticket_tabLayout);
        viewPager = view.findViewById(R.id.viewPage);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_buyticket_tabname_one_way));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_buyticket_tabname_round_trip));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabIndex = tab.getPosition();
                viewPager.setCurrentItem(currentTabIndex);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragments = new ArrayList<>();
        fragments.add(new Home_one_way());
        fragments.add(new Home_round_trip());
        pageAdater = new HowtogoPageAdapter(getChildFragmentManager(), fragments, tabLayout.getTabCount());
        viewPager.setAdapter(pageAdater);
        viewPager.setCurrentItem(currentTabIndex);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return view;
    }
}
