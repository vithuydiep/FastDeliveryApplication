package com.example.TVK.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.TVK.Controller.Driver.IOrderController;
import com.example.TVK.Controller.Driver.OrderController;
import com.example.TVK.R;
import com.example.TVK.View.Adapter.OrderAdapter;


public class Delevered extends Fragment implements IDelivered{
    IOrderController iOrderController;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_delivered, container, false);
        setAdapter(view);
        return view;
    }

    public void setAdapter(View view) {
        iOrderController = new OrderController(this);
        OrderAdapter adapter = iOrderController.loadOrderadapter(getContext(), "DAGIAO");
        listView = (ListView) view.findViewById(R.id.listviewOld);
        listView.setAdapter(adapter);
    }
    @Override
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
}