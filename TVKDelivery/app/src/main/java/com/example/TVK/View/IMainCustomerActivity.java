package com.example.TVK.View;

public interface IMainCustomerActivity {
    void getView();
    void loadFragment(Fragment fragment);
    void gotoDetailFragment(Customer customer);
    void sendSms(String toPhoneNumber, String message);
    void gotoDetailFragment(Driver driver);
    void changeFragment(int count);

}
