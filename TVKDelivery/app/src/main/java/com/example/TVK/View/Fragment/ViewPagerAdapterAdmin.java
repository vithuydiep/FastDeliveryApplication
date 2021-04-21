package com.example.TVK.View.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapterAdmin extends FragmentStatePagerAdapter{
    public ViewPagerAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeAdmin();
            case 1:
                return new Notification();
            case 2:
                return new Category();
            default:
                return new HomeCustomer();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
