package com.example.TVK.View.Fragment;

import com.example.TVK.Ultis.IViewUltis;

public interface IAddNewCustomer extends IViewUltis {
    void open_dialog(int gravity);
    void combackLoginActivity();
    void sendOTPMessage(String phone_number);
}
