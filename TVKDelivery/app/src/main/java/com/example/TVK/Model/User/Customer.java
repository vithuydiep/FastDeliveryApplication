package com.example.TVK.Model.User;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.View.Adapter.CustomerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements ICustomer , Serializable {
    private static Customer instance;
    private IGetAPICallback iGetAPICallback;


    public Customer(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser)
    {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser);
    }

    public Customer() {

    }

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }


    public void addNewCustomter(Customer customer, Context context, IViewUltis iViewUltis, IGetAPICallback iGetAPICallback_argument) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        iGetAPICallback = iGetAPICallback_argument;
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, super.baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray jsonArray = new JSONArray(response);
                            iGetAPICallback.onGetDataSucess(null,null,response,iViewUltis,"addNewCustomer");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iGetAPICallback.onResponseError(error.getMessage(),iViewUltis);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","register");
                params.put("username",customer.getUserName().trim());
                params.put("password",customer.getPassWord().trim());
                params.put("phone",customer.getPhone().trim());
                params.put("activationcode",customer.getActivationCode().trim());
                params.put("state",customer.getStatus().trim());
                params.put("typeofuser",customer.getTypeOfUser().trim());
                return params;
            }
        };
        /*jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void getAllDataCustomer(Context context, ArrayList<Customer> customerArrayList, CustomerAdapter adapter) {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONArray array = null;
                            try {
                                array = new JSONArray(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (int i=0 ; i< array.length(); i++)
                            {
                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    customerArrayList.add(new Customer(
                                            object.getInt("ID"),
                                            object.getString("Name"),
                                            object.getString("Gender"),
                                            object.getString("Phone"),
                                            object.getString("Address"),
                                            object.getString("Email"),
                                            object.getString("Username"),
                                            object.getString("Password"),
                                            object.getString("ActivationCode"),
                                            object.getString("ResetPasswordCode"),
                                            object.getString("State"),
                                            object.getString("TypeUser")
                                    ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("type","getDataCustomer");
                    return params;
                }
            };
            requestQueue.add(jsonArrayRequest);
    }


    public static class CustomerBuilder {
        private int idUser;
        private String fullName;
        private String gender;
        private String phone;
        private String address;
        private String email;
        private String userName;
        private String passWord;
        private String activationCode;
        private String status;
        private String resetPassword;
        private String typeOfUser;


        public CustomerBuilder setIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public CustomerBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public CustomerBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public CustomerBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public CustomerBuilder setPassWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public CustomerBuilder setActivationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public CustomerBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public CustomerBuilder setResetPassword(String resetPassword) {
            this.resetPassword = resetPassword;
            return this;
        }

        public CustomerBuilder setTypeOfUser(String typeOfUser) {
            this.typeOfUser = typeOfUser;
            return this;
        }

        public Customer build() {
            return new Customer(
                    this.idUser,
                    this.fullName,
                    this.gender,
                    this.phone,
                    this.address,
                    this.email,
                    this.userName,
                    this.passWord,
                    this.activationCode,
                    this.status,
                    this.resetPassword,
                    this.typeOfUser);
        }

    }
}
