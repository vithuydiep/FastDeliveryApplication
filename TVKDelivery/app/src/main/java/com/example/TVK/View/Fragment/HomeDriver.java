package com.example.TVK.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.TVK.R;

public class HomeDriver extends Fragment {
    ImageButton ibtnNew, ibtnOld, ibtnStatic, ibtnContact;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_driver, container, false);

        ibtnNew = view.findViewById(R.id.imageButton);
        ibtnOld = view.findViewById(R.id.imageButton2);
        ibtnStatic = view.findViewById(R.id.imageButton5);
        ibtnContact = view.findViewById(R.id.imageButton4);

        ibtnNew.setOnClickListener(v -> {
            Processing processing = new Processing();
            replaceFragment(processing);
        });
        ibtnOld.setOnClickListener(v -> {
            Delevered delevered = new Delevered();
            replaceFragment(delevered);
        });
        ibtnContact.setOnClickListener(v ->{
            Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
        });
        ibtnStatic.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
        });

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.display_layout, new Delevering());
        transaction.commit();

        return view;
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}