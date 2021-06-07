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


public class Processing extends Fragment implements IProcessing{
    IOrderController iOrderController;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_processing, container, false);
        setAdapter(view);
        return view;
    }

    public void setAdapter(View view) {
        iOrderController = new OrderController(this);
        OrderAdapter adapter = iOrderController.loadOrderadapter(getContext(), "DANGXULY");
        listView = (ListView) view.findViewById(R.id.listviewProcessing);
        listView.setAdapter(adapter);
    }
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
}