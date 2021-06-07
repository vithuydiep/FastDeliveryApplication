package com.example.TVK.Controller.Driver;

import android.content.Context;

import com.example.TVK.Model.User.Driver;

public interface IInfoController {
    void updateInforDriver(Context context, String name, String gender, String phone, String email, String address);
}
