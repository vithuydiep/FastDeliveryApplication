package com.example.TVK.View.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.Driver.IInfoController;
import com.example.TVK.Controller.Driver.InfoController;
import com.example.TVK.R;
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.IMainDriverActivity;
import com.example.TVK.View.LoginActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

public class DetailDriver extends Fragment implements IDetailDriver{
    EditText txtname, txtgender, txtphone, txtemail, txtaddr;
    Button btnupdate;
    Dialog Profile;
    private LoginActivity mloginActivity;
    IMainDriverActivity iMainDriverActivity;
    IInfoController iInfoController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_detail_driver, container, false);
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

        txtname = (EditText) view.findViewById(R.id.edtName);
        txtgender = (EditText) view.findViewById(R.id.edtGender);
        txtphone = (EditText) view.findViewById(R.id.edtPhone);
        txtaddr = (EditText) view.findViewById(R.id.edtAddress);
        txtemail = (EditText) view.findViewById(R.id.edtEmail);
        btnupdate = (Button) view.findViewById(R.id.btnupdate);
        txtname.setEnabled(false);
        txtgender.setEnabled(false);
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

        JSONObject logined_user = GlobalUser.getInstances().get("logined_user");
        try {
            txtname.setText(logined_user.getString("Name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            txtgender.setText(logined_user.getString("Gender"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            txtphone.setText(logined_user.getString("Phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            txtaddr.setText(logined_user.getString("Address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            txtemail.setText(logined_user.getString("Email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        btnupdate.setOnClickListener(v -> {
            OpenDialog(Gravity.CENTER);

        });

        return view;
    }

    private void OpenDialog(int gravity) {

        Profile = new Dialog(getContext());
        Profile.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Profile.setContentView(R.layout.dialog_profile);

        Window window = Profile.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        EditText edtName = Profile.findViewById(R.id.edtName);
        EditText edtGender = Profile.findViewById(R.id.edtGender);
        EditText edtPhone = Profile.findViewById(R.id.edtPhone);
        EditText edtEmail = Profile.findViewById(R.id.edtEmail);
        EditText edtAddress = Profile.findViewById(R.id.edtAddress);

        JSONObject logined_user = GlobalUser.getInstances().get("logined_user");

        try {
            edtName.setText(logined_user.getString("Name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            edtGender.setText(logined_user.getString("Gender"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            edtPhone.setText(logined_user.getString("Phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            edtEmail.setText(logined_user.getString("Email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            edtAddress.setText(logined_user.getString("Address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String name = edtName.getText().toString().trim();
        String gender = edtGender.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        Button Complete = Profile.findViewById(R.id.btnok);
        Complete.setOnClickListener(v -> {
            Profile.dismiss();
            Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
//            iInfoController = new InfoController();
//            iInfoController.updateInforDriver(getContext(), name, gender, phone, email, address);
//            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

        });

        Profile.setCancelable(true);
        Profile.show();
    }


    @Override
    public void OnClickDialog() {
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
