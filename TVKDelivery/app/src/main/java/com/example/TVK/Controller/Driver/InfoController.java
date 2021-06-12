package com.example.TVK.Controller.Driver;

import android.content.Context;
import android.view.View;

import com.example.TVK.Model.IOrder;
import com.example.TVK.Model.Order;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.Model.User.IDriver;

public class InfoController implements IInfoController{
    View view;

    IDriver iDriver = new Driver();
    @Override
    public void updateInforDriver(Context context, String name, String gender, String phone, String email, String address) {
        iDriver.updateInfoDriver(context, name, gender, phone, email, address);
    }
}
