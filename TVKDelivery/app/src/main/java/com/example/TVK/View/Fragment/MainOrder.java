package com.example.TVK.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageOrderController;
import com.example.TVK.Controller.ManageOrderController;
import com.example.TVK.Model.IOrder;
import com.example.TVK.R;
import com.example.TVK.View.MainAdminActivity;
import com.example.TVK.View.OrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainOrder extends Fragment implements IMainOrder{
    ListView listOrder;
    IOrder iOrder;
    IManageOrderController iManageOrderController;
    MainAdminActivity mainAdminActivity;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static MainOrder newInstance(String param1, String param2) {
        MainOrder fragment = new MainOrder();
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
        View view =  inflater.inflate(R.layout.fragment_main_order, container, false);
        listOrder = view.findViewById(R.id.listOrder);
        String title = getArguments().getString("title");

        if(title.equals("All of order"))
        {
            setAdaper(view,"ALLORDER");
        }
        if(title.equals("Processing"))
        {
            setAdaper(view,"RECENT");
        }
        if (title.equals("Completed"))
        {
            setAdaper(view,"COMPLETED");
        }


        return view;
    }

    @Override
    public void setAdaper(View view, String type) {
        iManageOrderController= new ManageOrderController(this);
        OrderAdapter orderAdapter = iManageOrderController.loadadapter(getContext(),type);
        listOrder.setAdapter(orderAdapter);
    }

    @Override
    public void changeFragment(Fragment fragment) {

    }

    @Override
    public void notifyerror(String message) {

    }

}