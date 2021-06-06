package com.example.TVK.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.TVK.Model.Order;
import com.example.TVK.R;

import java.text.DecimalFormat;
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
            holder.txtidcustomer = (TextView) convertView.findViewById(R.id.txtidcus_order);
            holder.txtpickup = (TextView) convertView.findViewById(R.id.txtpickup_order);
            holder.txtreceive= (TextView) convertView.findViewById(R.id.txtreceiveorder);
            holder.btndetail = (ImageButton) convertView.findViewById(R.id.btndetailorder);
            holder.txtfee = (TextView) convertView.findViewById(R.id.txtfee_order);
            holder.txttotal=(TextView) convertView.findViewById(R.id.txttoal_order);
            holder.btndelete = (ImageButton) convertView.findViewById(R.id.btndeleteorder);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        Order order = orderList.get(position);
        holder.txtidcustomer.setText("ID Customer: "+order.getIduser());
        holder.txtpickup.setText("Pick up Address: " +order.getPickupAddress());
        holder.txtreceive.setText("Receving Address: " +order.getReceiveAddress());
        holder.txtfee.setText("Fee: "+ formatter.format(order.getPostage()) +" VNĐ");
        holder.txttotal.setText("Total: "+ formatter.format(order.getTotal()) +" VNĐ");

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemOrder(order);
            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickDeleteOrder(String.valueOf(order.getId()),order.getState().toString().trim());
            }
        });

        return convertView;
    }
    private class ViewHolder{
        TextView txtidcustomer, txtpickup, txtreceive,txtfee, txttotal;
        ImageButton btndetail, btndelete;
    }

    public interface IClickItemListener {
        void onClickItemOrder(Order order);
        void onClickDeleteOrder(String id,String state);
    }
}
