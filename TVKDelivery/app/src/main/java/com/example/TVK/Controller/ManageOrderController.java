package com.example.TVK.Controller;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TVK.Model.IOrder;
import com.example.TVK.Model.Order;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.Fragment.IMainOrder;
import com.example.TVK.View.IMainAdminActivity;
import com.example.TVK.View.MainAdminActivity;
import com.example.TVK.View.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageOrderController implements IManageOrderController, CallBack {
    IMainOrder iMainOrder;
    IOrder order = new Order();
    IMainAdminActivity iMainAdminActivity;
    Dialog dialog;
    TextView textView;
    Button ok ;
    Button cancel;

    public ManageOrderController(IMainOrder iMainOrder) {
        this.iMainOrder = iMainOrder;
    }
    public ManageOrderController() {
    }

    @Override
    public OrderAdapter loadadapter(Context context,String type) {
        OrderAdapter orderAdapter;
        ArrayList<Order> orderArrayList;
        orderArrayList = new ArrayList<>();
        orderAdapter = new OrderAdapter(context, R.layout.layout_custom_order, orderArrayList, new OrderAdapter.IClickItemListener() {
            @Override
            public void onClickItemOrder(Order order) {
                iMainAdminActivity = (IMainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(order);
            }
            @Override
            public void onClickDeleteOrder(String id,String state) {
                if(state.equals("DANGXULY"))
                {
                    initDialog(context,id);
                    textView.setText("Are you sure you want to delete order?");
                    dialog.show();
                }else
                {
                    Toast.makeText(context,"It is being delivered so you can't delete it",Toast.LENGTH_LONG).show();
                }
            }
        });
        order.getAllDataOrder(context, orderArrayList, orderAdapter,type);
        return orderAdapter;
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {
        if (jsonObject != null && string_response.equals("customer"))
        {
            try {
                Customer a = new Customer(jsonObject.getInt("ID"),
                        jsonObject.getString("Name"),
                        jsonObject.getString("Gender"),
                        jsonObject.getString("Phone"),
                        jsonObject.getString("Address"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Username"),
                        jsonObject.getString("Password"),
                        jsonObject.getString("ActivationCode"),
                        jsonObject.getString("State"),
                        jsonObject.getString("ResetPasswordCode"),
                        jsonObject.getString("TypeUser"));
                iMainAdminActivity = (MainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(a);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if(jsonObject != null && string_response.equals("driver"))
        {
            try {
                Driver a = new Driver(jsonObject.getInt("ID"),
                        jsonObject.getString("Name"),
                        jsonObject.getString("Gender"),
                        jsonObject.getString("Phone"),
                        jsonObject.getString("Address"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Username"),
                        jsonObject.getString("Password"),
                        jsonObject.getString("ActivationCode"),
                        jsonObject.getString("State"),
                        jsonObject.getString("ResetPasswordCode"),
                        jsonObject.getString("TypeUser"),
                        jsonObject.getString("DriverLicenseNumber"),
                        jsonObject.getString("IdNumber"));
                iMainAdminActivity = (MainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
    public void initDialog(Context context,String id)
    {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_confirm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations =R.style.animation;
        textView = dialog.findViewById(R.id.txtquestion);
        ok = dialog.findViewById(R.id.dialog_ok);
        cancel = dialog.findViewById(R.id.dialog_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                order.deleteOrder(context,id);


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}

