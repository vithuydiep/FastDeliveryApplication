package com.example.TVK.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.TVK.Model.Order;
import com.example.TVK.R;

import java.util.List;

public class OrderAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Order> orderList;
    private IClickItemListener iClickItemListener;


    @Override
    public int getCount() {
        return orderList.size();
    }

    public interface IClickItemListener
    {
        void onClickItemOrder(Order order);
    }



    public OrderAdapter(Context context, int layout, List<Order> orderList, IClickItemListener iClickItemListener) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
        this.iClickItemListener = iClickItemListener;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtreceivername = (TextView) convertView.findViewById(R.id.txtreceivername);
            holder.txtaddress1 = (TextView) convertView.findViewById(R.id.txtaddress1);
            holder.txtaddress2 = (TextView) convertView.findViewById(R.id.txtaddress2);
            holder.time = (TextView) convertView.findViewById(R.id.txttimestart);
            holder.imageView= (ImageView ) convertView.findViewById(R.id.picture);
            holder.btndetailcus = (ImageView) convertView.findViewById(R.id.btndetailcus);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Order order = orderList.get(position);
        holder.txtreceivername.setText("Receiver: "+ order.getReceiverName());
        holder.txtaddress1.setText("Pickup: " + order.getPickupAddress());
        holder.txtaddress2.setText("Delivery: " + order.getDeliveryAddress());
        if (order.getState().equals("DAGIAO")) {
            holder.time.setText("TimeEnd: " + order.getEndTime());
        }
        else
            holder.time.setText("TimeStart: " + order.getStartTime());


        holder.btndetailcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemOrder(order);
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txtreceivername, txtaddress1, txtaddress2, time;
        ImageView imageView, btndetailcus;
    }

}
