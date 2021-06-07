package com.example.TVK.Controller.Driver;

import android.content.Context;

import com.example.TVK.View.Adapter.OrderAdapter;

import java.util.Date;

public interface IOrderController {
    OrderAdapter loadOrderadapter(Context context, String state);
    void updateStateOrder(Context context, String state, int id);
    void updateIddriverOrder(Context context, int iddriver, int id);
    void updateEndtimeOrder(Context context, Date endtime, int id);
}
