package com.example.TVK.View.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        txtname = (EditText) view.findViewById(R.id.edtName);
        txtgender = (EditText) view.findViewById(R.id.edtGender);
        txtphone = (EditText) view.findViewById(R.id.edtPhone);
        txtaddr = (EditText) view.findViewById(R.id.edtAddress);
        txtemail = (EditText) view.findViewById(R.id.edtEmail);
        btnupdate = (Button) view.findViewById(R.id.btnupdate);
        txtname.setEnabled(false);
        txtgender.setEnabled(false);
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

    }
}
