package com.example.TVK.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TVK.R;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class OrderView extends Fragment implements Serializable {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;



    public OrderView() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order, container, false);

        tabLayout = mView.findViewById(R.id.tabLayout);
        viewPager = mView.findViewById(R.id.ViewPager);

        ViewPagerAdapterOrder adapterOrder = new ViewPagerAdapterOrder(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterOrder);

        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }
}