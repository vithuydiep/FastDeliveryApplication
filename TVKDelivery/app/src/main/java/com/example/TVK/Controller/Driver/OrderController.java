package com.example.TVK.Controller.Driver;

import android.content.Context;
import com.example.TVK.Model.IOrder;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.View.Fragment.IDelivered;
import com.example.TVK.View.Fragment.IDelivering;
import com.example.TVK.View.Adapter.OrderAdapter;
import com.example.TVK.View.Fragment.IProcessing;
import com.example.TVK.View.IMainDriverActivity;

import java.util.ArrayList;
import java.util.Date;

public class OrderController implements IOrderController {
    IMainDriverActivity iMainDriverActivity;
    IOrder iOrder = new Order();
    IDelivering iDelivering;
    IDelivered iDelivered;
    IProcessing iProcessing;
    public OrderController(IDelivering iDelivering) {
        this.iDelivering = iDelivering;
    }
    public OrderController(IDelivered iDelivered) {
        this.iDelivered = iDelivered;
    }
    public OrderController(IProcessing iProcessing) {
        this.iProcessing = iProcessing;
    }

    public OrderController() {

    }

    public OrderAdapter loadOrderadapter(Context context, String state) {

        OrderAdapter orderAdapter;
        ArrayList<Order> orderArrayList;
        orderArrayList = new ArrayList<>();
        orderAdapter = new OrderAdapter(context, R.layout.layout_order, orderArrayList, new OrderAdapter.IClickItemListener() {
            @Override
            public void onClickItemOrder(Order order) {
                iMainDriverActivity = (IMainDriverActivity) context;
                iMainDriverActivity.gotoDetailFragment(order);
            }
        });
        iOrder.getAllDataOrder(context, orderArrayList, orderAdapter, state);
        return orderAdapter;
    }
    public void updateStateOrder(Context context, String state, int id) {
        iOrder.updateStateOrder(context,state,id);
    }
    public void updateIddriverOrder(Context context, int iddriver, int id) {
        iOrder.updateIddriverOrder(context,iddriver,id);
    }

    @Override
    public void updateEndtimeOrder(Context context, Date endtime, int id) {
        iOrder.updateEndtimeOrder(context,endtime,id);
    }
}
