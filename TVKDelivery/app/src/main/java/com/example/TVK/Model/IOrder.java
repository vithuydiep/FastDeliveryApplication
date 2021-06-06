package com.example.TVK.Model;

import android.content.Context;

import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.OrderAdapter;

import java.util.ArrayList;

public interface IOrder {
    void getAllDataOrder(Context context, ArrayList<Order> orderArrayList, OrderAdapter adapter,String type);
    public void deleteOrder(Context context,String id);
    void findCustomerbyID(Context context, CallBack callBack, String id, Order order);
}
