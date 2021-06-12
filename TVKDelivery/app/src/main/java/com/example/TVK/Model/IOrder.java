package com.example.TVK.Model;

import android.content.Context;

import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.OrderAdapter;

import java.util.ArrayList;

import android.content.Context;

import com.example.TVK.View.Adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.Date;

public interface IOrder {
    void getAllDataOrder(Context context, ArrayList<Order> orderArrayList, OrderAdapter adapter,String type);
    public void deleteOrder(Context context,String id);
    void findCustomerbyID(Context context, CallBack callBack, String id, Order order);
    void getAllDataOrder(Context context, ArrayList<Order> orderArrayList, OrderAdapter orderAdapter, String state);
    void updateStateOrder(Context context, String state, int id);
    void updateIddriverOrder(Context context, int iddriver, int id);
    void updateEndtimeOrder(Context context, Date endtime, int id);
}
