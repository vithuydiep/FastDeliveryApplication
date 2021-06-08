package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.Model.Contact;
import com.example.TVK.Model.IContact;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.ICustomer;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.ContactAdapter;
import com.example.TVK.View.Fragment.IListContact;
import com.example.TVK.View.MainAdminActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageContactController implements  IManageContactController, CallBack {
    IListContact iListContact;
    IContact iContact = new Contact();
    MainAdminActivity iMainAdminActivity;
    Customer customer;
    ICustomer iCustomer = new Customer();


    public ManageContactController(IListContact iListContact) {
        this.iListContact = iListContact;
    }

    public ManageContactController() {
    }

    @Override
    public ContactAdapter loadadapter(Context context) {
        ContactAdapter contactAdapter;
        ArrayList<Contact> contactArrayList;
        contactArrayList = new ArrayList<>();
        contactAdapter = new ContactAdapter(context, R.layout.layout_custom_contact, contactArrayList, new ContactAdapter.IClickItemListener() {
            @Override
            public void onClickItemContact(String id) {

                //mainAdminActivity.gotoDetailFragment();
                CallBack callBack = new ManageContactController();
                iCustomer.findCustomerbyID(context,callBack,id,customer);
                //Log.i("customer", customer.getFullName());
                /*if(customer != null)
                {

                }else {
                    Toast.makeText(context,"lỖI", Toast.LENGTH_LONG).show();
                }*/


            }
        });
        iContact.getAllDataContact(context, contactArrayList, contactAdapter);
        return contactAdapter;
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {
        if (jsonObject != null)
        {
            try {
                Customer a = (Customer) object;
                a = new Customer(jsonObject.getInt("ID"),
                            jsonObject.getString("Name"),
                            jsonObject.getString("Gender"),
                            jsonObject.getString("Phone"),
                            jsonObject.getString("Address"),
                            jsonObject.getString("Email"),
                            jsonObject.getString("Username"),
                            jsonObject.getString("Password"),
                            jsonObject.getString("ActivationCode"),
                            jsonObject.getString("State"),
                            jsonObject.getString("ResetPasswordCode"),
                            jsonObject.getString("TypeUser"));
                customer =a;
                iMainAdminActivity = (MainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(customer);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
}
