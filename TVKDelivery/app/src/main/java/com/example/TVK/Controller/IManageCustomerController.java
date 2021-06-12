package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.View.Adapter.CustomerAdapter;

public interface IManageCustomerController {
    CustomerAdapter loadadapter(Context context);
    void OnCheckDataAndSendOTP(EditText txtUsername, EditText txtPassword, EditText txtConfirmPassword, EditText txtPhone, Context context);
    void OnAddNewCustomer(EditText txtUsername, EditText txtPassword, EditText txtPhone,String otp,Context context);

}
