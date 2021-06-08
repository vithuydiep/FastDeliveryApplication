package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.ManageOrderController;
import com.example.TVK.Model.Order;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.Model.User.ICustomer;
import com.example.TVK.Model.User.IDriver;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailOrder extends Fragment {
    TextView txtiduser, txtpickup, txtreceive,txtmass,txtiddriver, txtreceivername, txtreceiverphone,  txtdescription, txtpostage, txttotal, txtstate,txtstart,txtend;
    ImageButton btnback;
    Order order;
    Customer customer;
    Driver driver;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailOrder newInstance(String param1, String param2) {
        DetailOrder fragment = new DetailOrder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail_order, container, false);
        Anhxa(view);

        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            order = (Order) bundle.getSerializable("object_order");
            if(order!=null)
            {
                txtstate.setText(order.getState());
                txttotal.setText(formatter.format(order.getTotal())+" VNĐ");
                txtreceive.setText(order.getReceiveAddress());
                txtreceiverphone.setText(order.getReceivePhone());
                txtpickup.setText(order.getPickupAddress());
                txtpostage.setText(formatter.format(order.getPostage())+" VNĐ");
                txtmass.setText(order.getMass()+" Kg");
                txtdescription.setText(order.getDescription());
                txtreceivername.setText(order.getReceiveName());
                txtiduser.setText(Html.fromHtml("<u>"+String.valueOf(order.getIduser())+"</u>"));
                if(order.getEndTime()!= null)
                {
                    txtend.setText(format.format(order.getEndTime()));
                }
                txtstart.setText(format.format(order.getStartTime()));
                if(order.getIddriver() != 0)
                {
                    txtiddriver.setText(Html.fromHtml("<u>"+String.valueOf(order.getIddriver())+"</u>"));
                }
            }
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager()!=null)
                {
                    getFragmentManager().popBackStack();
                }
            }
        });
        txtiduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallBack callBack = (CallBack) new ManageOrderController();
                ICustomer iCustomer = new Customer();
                iCustomer.findCustomerbyID(getContext(),callBack,String.valueOf(order.getIduser()),customer);

            }
        });
        txtiddriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallBack callBack = (CallBack) new ManageOrderController();
                IDriver iDriver = new Driver();
                iDriver.findCustomerbyID(getContext(),callBack,String.valueOf(order.getIddriver()),driver);
            }
        });

        return view;
    }

    public void Anhxa(View view)
    {
        txtiduser = view.findViewById(R.id.txtidcus_detailorder);
        txtpickup = view.findViewById(R.id.txtpickup_detailorder);
        txtdescription = view.findViewById(R.id.txtdescription_detailorder);
        txtmass = view.findViewById(R.id.txtmass_detailorder);
        txtpostage = view.findViewById(R.id.txtpostage_detailorder);
        txtreceivername = view.findViewById(R.id.txtreceivername_detailorder);
        txtreceiverphone=view.findViewById(R.id.txtreceiverphone_detailorder);
        txtreceive = view.findViewById(R.id.txtreceiveradd_detailorder);
        txttotal = view.findViewById(R.id.txttotal_detailorder);
        txtstate = view.findViewById(R.id.txtstate_detailorder);
        txtstart= view.findViewById(R.id.txtstart_detailorder);
        txtend = view.findViewById(R.id.txtend_detailorder);
        btnback = view.findViewById(R.id.btnback3);
        txtiddriver = view.findViewById(R.id.txtiddriver_detailorder);
    }




}