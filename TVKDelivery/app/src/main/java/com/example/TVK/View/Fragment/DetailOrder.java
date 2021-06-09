package com.example.TVK.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
    Dialog confirm;


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

        if (CheckState.equals("DANGXULY")){
            btComplete.setText("Select");
            btComplete.setOnClickListener(v -> {
                OpenDialog(Gravity.CENTER);

            });
        }
        else if (CheckState.equals("DANGGIAO")) {
            btComplete.setOnClickListener(v -> {
                OpenDialog(Gravity.CENTER);
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
    private void OpenDialog(int gravity) {

        confirm = new Dialog(getContext());
        confirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirm.setContentView(R.layout.dialog_sure);

        Window window = confirm.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        Button btnOK = confirm.findViewById(R.id.dialog_ok);
        Button btnCancel = confirm.findViewById(R.id.dialog_cancel);
        JSONObject logined_user = GlobalUser.getInstances().get("logined_user");
        btnOK.setOnClickListener(v -> {
            if (CheckState.equals("DANGXULY")) {
                CheckState = "DANGGIAO";
                Processing pcs = new Processing();
                try {
                    CheckIddriver = logined_user.getInt("ID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                iOrderController = new OrderController();
                iOrderController.updateStateOrder(getContext(), CheckState, id);
                iOrderController.updateIddriverOrder(getContext(), CheckIddriver, id);
                confirm.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, pcs).commit();
                return;
            }
            else if (CheckState.equals("DANGGIAO")) {
                CheckState = "DAGIAO";
                OrderView orv = new OrderView();
                iOrderController = new OrderController();
                iOrderController.updateEndtimeOrder(getContext(), id);
                iOrderController.updateStateOrder(getContext(), CheckState, id);
                confirm.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, orv).commit();
                return;
            }
        });
        btnCancel.setOnClickListener(v ->{
            confirm.dismiss();
        });

        confirm.setCancelable(true);
        confirm.show();
    }
}