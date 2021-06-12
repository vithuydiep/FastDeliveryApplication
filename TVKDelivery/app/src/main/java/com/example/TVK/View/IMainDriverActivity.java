package com.example.TVK.View;

import androidx.fragment.app.Fragment;

import com.example.TVK.Model.Order;


public interface IMainDriverActivity {
    void loadFragment(Fragment fragment);
    void gotoDetailFragment(Order order);
}
