package com.example.TVK.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Model.User.User;
import com.example.TVK.R;
import com.example.TVK.View.LoginActivity;


public class Category extends Fragment {

    Button btndangxuat;
    TextView txtNameAdmin;
    ImageButton btncus, btndriver, btnnotify, btncontact;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Category.
     */
    // TODO: Rename and change types and number of parameters
    public static Category newInstance(String param1, String param2) {
        Category fragment = new Category();
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
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        btndangxuat = view.findViewById(R.id.btndangxuat);
        btncus = view.findViewById(R.id.btncus_categogy);
        btndriver = view.findViewById(R.id.btndriver_categogy);
        btncontact = view.findViewById(R.id.btncontact_categogy);
        btnnotify = view.findViewById(R.id.btnnotify_categogy);
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        txtNameAdmin = (TextView) view.findViewById(R.id.txtnameAd);
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            User user = (User) bundle.getSerializable("admin");
            txtNameAdmin.setText(user.getFullName());
        }
        btnnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddNotification();
                changeFragment(fragment);
            }
        });
        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListContact();
                changeFragment(fragment);
            }
        });
        btndriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListDriver();
                changeFragment(fragment);
            }
        });
        btncus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListCustomer();
                changeFragment(fragment);
            }
        });



        return view;
    }
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }

}