package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.View.OrderAdapter;

public interface IManageOrderController {
    OrderAdapter loadadapter(Context context,String type);
}
