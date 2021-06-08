package com.example.TVK.View.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageDriverController;
import com.example.TVK.Controller.ManageDriverController;
import com.example.TVK.Model.User.Driver;
import com.example.TVK.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailDriver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailDriver extends Fragment {
    EditText txtgender, txtphone, txtemail, txtaddr, txtusername,txtidnumber, txtlicense,txteditdriver;
    TextView txtname, txtnameedit;
    ImageButton btnback, btneditPhone, btneditEmail, btneditAddress, btnIDnum, btnLicense;
    Switch state;
    Dialog dialog, dialogedit;
    TextView textView;
    Button ok,cancel, btnedit, btncancel;
    String statedriver;
    public static final String TAG = DetailInforCustomer.class.getName();
    IManageDriverController iManageDriverController;
    Driver driver;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailDriver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailDriver.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailDriver newInstance(String param1, String param2) {
        DetailDriver fragment = new DetailDriver();
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
        View view= inflater.inflate(R.layout.fragment_detail_driver, container, false);
        AnhXa(view);
        initDialog();
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            driver = (Driver) bundle.getSerializable("object_driver");
            if(driver !=null)
            {
                txtname.setText(driver.getFullName());
                txtusername.setText(driver.getUserName());
                txtemail.setText(driver.getEmail());
                txtphone.setText(driver.getPhone());
                txtaddr.setText(driver.getAddress());
                txtgender.setText(driver.getGender());
                txtlicense.setText(driver.getGplx());
                txtidnumber.setText(driver.getIdnumber());
                if(driver.getStatus().equals("consudung"))
                {
                    state.setChecked(true);
                }else
                {
                    state.setChecked(false);
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

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.isChecked() == false)
                {
                    textView.setText("Are you sure you want to block account of this driver?");
                    dialog.show();
                }
                else {
                    textView.setText("Are you sure you want to open account of this driver?");
                    dialog.show();
                }
            }
        });

        btneditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogEdit("Phone",txtphone);
                txteditdriver.setText(driver.getPhone());
                dialogedit.show();
            }
        });
        btneditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogEdit("Email",txtemail);
                txteditdriver.setText(driver.getEmail());
                dialogedit.show();
            }
        });
        btneditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogEdit("Address",txtaddr);
                txteditdriver.setText(driver.getAddress());
                dialogedit.show();
            }
        });
        btnIDnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogEdit("IDNum",txtidnumber);
                txteditdriver.setText(driver.getIdnumber());
                dialogedit.show();
            }
        });
        btnLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogEdit("License",txtlicense);
                txteditdriver.setText(driver.getGplx());
                dialogedit.show();
            }
        });



        return view;
    }
    public void setVisibleTrue()
    {
        txtphone.setEnabled(true);
        txtaddr.setEnabled(true);
        txtemail.setEnabled(true);
        txtidnumber.setEnabled(true);
        txtlicense.setEnabled(true);
    }
    public void setVisibleFalse()
    {
        txtphone.setEnabled(false);
        txtaddr.setEnabled(false);
        txtemail.setEnabled(false);
        txtidnumber.setEnabled(false);
        txtlicense.setEnabled(false);
    }
    public void initDialog()
    {
        dialog = new Dialog(getActivity());
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
                iManageDriverController = new ManageDriverController();
                if(state.isChecked() == true)
                {
                    statedriver ="consudung";
                }else if(state.isChecked() == false)
                {
                    statedriver ="block";
                }

                iManageDriverController.updateStateDriver(getContext(),statedriver,txtphone.getText().toString());

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.isChecked()==false)
                {
                    state.setChecked(true);

                }
                else if(state.isChecked()==true)
                {
                    state.setChecked(false);
                }
                dialog.dismiss();
            }
        });
    }
    public void initDialogEdit(String type, EditText nameupdate)
    {
        dialogedit = new Dialog(getActivity());
        dialogedit.setContentView(R.layout.dialog_edit);
        dialogedit.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogedit.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        dialogedit.setCancelable(false);
        dialogedit.getWindow().getAttributes().windowAnimations =R.style.animation;
        txteditdriver = (EditText) dialogedit.findViewById(R.id.txteditdriver);
        btnedit = (Button) dialogedit.findViewById(R.id.btnconfirm_edit);
        btncancel = (Button) dialogedit.findViewById(R.id.btncancel_edit);
        txtnameedit = (TextView) dialogedit.findViewById(R.id.txtnameedit);
        txteditdriver.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v,hasFocus);
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iManageDriverController = new ManageDriverController();
                iManageDriverController.OnCheckDataUpdate(nameupdate,txteditdriver,getContext(),String.valueOf(driver.getIdUser()),type);
            }
        });
        txtnameedit.setText(type);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogedit.dismiss();
            }
        });
    }
    public void AnhXa(View view)
    {
        txtname = (TextView) view.findViewById(R.id.txtdrivername);
        txtgender = (EditText) view.findViewById(R.id.txtdrivergender);
        txtphone = (EditText) view.findViewById(R.id.txtdriverphone);
        txtaddr = (EditText) view.findViewById(R.id.txtdriveraddress);
        txtemail = (EditText) view.findViewById(R.id.txtdriveremail);
        txtusername = (EditText) view.findViewById(R.id.txtdriverusername);
        txtidnumber = (EditText) view.findViewById(R.id.txtdriveridnumber);
        txtlicense =(EditText) view.findViewById(R.id.txtdriverlicense);
        btnback = (ImageButton) view.findViewById(R.id.btndriverback);
        state =(Switch) view.findViewById(R.id.switchstatedriver);
        btneditPhone = (ImageButton) view.findViewById(R.id.btnEditPhone);
        btneditAddress = (ImageButton) view.findViewById(R.id.btnEditAddress);
        btneditEmail =(ImageButton) view.findViewById(R.id.btnEditEmail);
        btnIDnum = (ImageButton) view.findViewById(R.id.btnEditIDNum);
        btnLicense = (ImageButton) view.findViewById(R.id.btnEditLicense);
    }
    public void hideKeyboard(View view, boolean hasFocus) {
        if(!hasFocus) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}