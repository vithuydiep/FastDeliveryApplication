package com.example.TVK.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TVK.Controller.Driver.IOrderController;
import com.example.TVK.Controller.Driver.OrderController;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.IMainDriverActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class DetailOrder extends Fragment {
    IOrderController iOrderController;
    View view;
    IMainDriverActivity iMainDriverActivity;
    Button btComplete, btBack;
    EditText editPickup, edtReceive, edtMass, edtReceiverName, edtReceiverPhone, edtPostage, edtDescription;
    TextView tvTotal;
    OrderController orderController;
    private String CheckState;
    private int id;
    private int CheckIddriver;
    private Date ETime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_detail_order, container, false);
        mappingInformationOrder(view);

        return view;
    }
    private void mappingInformationOrder(View view)
    {
        btComplete = view.findViewById(R.id.btComplete);
        btBack = view.findViewById(R.id.btBack);
        editPickup = view.findViewById(R.id.edtPickup);
        edtReceive = view.findViewById(R.id.edtReceive);
        edtReceiverName = view.findViewById(R.id.edtReceiverName);
        edtReceiverPhone = view.findViewById(R.id.edtReceiverPhone);
        edtMass = view.findViewById(R.id.edtMass);
        edtPostage = view.findViewById(R.id.edtPostage);
        edtDescription = view.findViewById(R.id.edtDescription);
        tvTotal = view.findViewById(R.id.tvTotal);
        setVisibleFalse();

        Bundle bundle = this.getArguments();
        if (bundle!=null){
            Order order = (Order)bundle.getSerializable("object_order");
            if (order!=null){
                editPickup.setText(order.getPickupAddress());
                edtReceive.setText(order.getDeliveryAddress());
                edtReceiverName.setText(order.getReceiverName());
                edtReceiverPhone.setText(order.getReceiverPhone());
                edtMass.setText(String.valueOf(order.getMass()));
                edtPostage.setText(String.valueOf(order.getPostage()));
                edtDescription.setText(order.getDescription());
                tvTotal.setText(String.valueOf(order.getTotal()));

                CheckState = order.getState();
                id = order.getIdOrder();
            }
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager()!=null)
                {
                    getFragmentManager().popBackStack();
                }
            }
        });
        JSONObject logined_user = GlobalUser.getInstances().get("logined_user");
        if (CheckState.equals("DANGXULY")){
            btComplete.setText("Select");
            btComplete.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                CheckState = "DANGGIAO";
                try {
                    CheckIddriver = logined_user.getInt("ID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                iOrderController = new OrderController();
                iOrderController.updateStateOrder(getContext(), CheckState, id);
                iOrderController.updateIddriverOrder(getContext(), CheckIddriver, id);
            });
        }
        else if (CheckState.equals("DANGGIAO")) {
            btComplete.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                CheckState = "DAGIAO";
                ETime = new Date();
                iOrderController = new OrderController();
                iOrderController.updateEndtimeOrder(getContext(), ETime, id);
                iOrderController.updateStateOrder(getContext(), CheckState, id);

            });
        }
        else {
            btComplete.setEnabled(false);
        }
    }

    private void setVisibleFalse() {
        editPickup.setEnabled(false);
        edtReceive.setEnabled(false);
        edtReceiverName.setEnabled(false);
        edtReceiverPhone.setEnabled(false);
        edtMass.setEnabled(false);
        edtPostage.setEnabled(false);
        edtDescription.setEnabled(false);
    }
}