package com.example.TVK.View;

import androidx.fragment.app.Fragment;

import com.example.TVK.Model.Order;
import com.example.TVK.Model.User.Customer;

public interface IMainAdminActivity {
    void loadFragment(Fragment fragment);
    void gotoDetailFragment(Customer customer);
    void sendSms(String toPhoneNumber, String message);
    void gotoDetailFragment(Driver driver);
    void gotoDetailFragment(Order order);

}
