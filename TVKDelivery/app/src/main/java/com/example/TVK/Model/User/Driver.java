package com.example.TVK.Model.User;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Model.IOrder;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.View.Adapter.DriverAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Driver extends User implements IDriver{
    private static Driver instance;
    private String gplx;
    private String idnumber;
    CallBack callBack;


    public Driver(String fullName, String gender, String phone, String address, String email, String gplx, String idnumber) {
        super(fullName, gender, phone, address, email);
        this.gplx = gplx;
        this.idnumber = idnumber;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getGplx() {
        return gplx;
    }

    public void setGplx(String gplx) {
        this.gplx = gplx;
    }

    public Driver(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser, String gplx, String idnumber) {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser);
        this.gplx = gplx;
        this.idnumber = idnumber;
    }
    private IGetAPICallback iGetAPICallback;
    private IOrder iOrder;

    String baseUrl = "http://192.168.1.71/androidwebservce/";

    public Driver()
    {

    }
    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public void getAllDataDriver(Context context, ArrayList<Driver> driverArrayList, DriverAdapter adapter) {
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
                        for (int i = 0 ; i< array.length(); i++)
                        {
                            try {
                                JSONObject object = array.getJSONObject(i);
                                driverArrayList.add(new Driver(
                                        object.getInt("ID"),
                                        object.getString("Name"),
                                        object.getString("Gender"),
                                        object.getString("Phone"),
                                        object.getString("Address"),
                                        object.getString("Email"),
                                        object.getString("Username"),
                                        object.getString("Password"),
                                        object.getString("ActivationCode"),
                                        object.getString("State"),
                                        object.getString("ResetPasswordCode"),
                                        object.getString("TypeUser"),
                                        object.getString("DriverLicenseNumber"),
                                        object.getString("IdNumber")
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
                params.put("type","getDataDriver");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public void checkExistPhone(Context context, CallBack callBack_a, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense, EditText txtaddress) {
        callBack = callBack_a;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Exist Phone"))
                {
                    txtphone.requestFocus();
                    txtphone.setError("Number phone is exist");
                    callBack.onGetDataSucess(null,null,response,context,null);

                }else if (response.equals("No Exist Phone")){
                    //txtcusphone_add.requestFocus();
                    //txtcusphone_add.setError("Number phone is not exist");
                    Driver driver = new Driver(getText(txtname),getText(txtgender),getText(txtphone),getText(txtaddress),getText(txtemail),getText(txtlicense),getText(txtidnumber));
                    callBack.onGetDataSucess(null,null,response,context,driver);
                   /* txtemail.setText("");
                    txtname.setText("");
                    txtphone.setText("");
                    txtaddress.setText("");
                    txtgender.setText("");
                    txtidnumber.setText("");
                    txtlicense.setText("");
                    txtemail.setCursorVisible(false);
                    txtname.setCursorVisible(false);
                    txtphone.setCursorVisible(false);
                    txtaddress.setCursorVisible(false);
                    txtgender.setCursorVisible(false);
                    txtidnumber.setCursorVisible(false);
                    txtlicense.setCursorVisible(false);*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onResponseError(error.getMessage(), null);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","checkExist");
                params.put("typecheck","phone");
                params.put("phone",getText(txtphone));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public String getText(EditText name)
    {
        return name.getText().toString().trim();
    }
    public static class DriverBuilder extends UserBuilder{
        private int id;
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
        private String idnumber;
        private String license;


        public Driver.DriverBuilder setIdUser(int idUser) {
            this.id = idUser;
            return this;
        }

        public Driver.DriverBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Driver.DriverBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Driver.DriverBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Driver.DriverBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Driver.DriverBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Driver.DriverBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Driver.DriverBuilder setPassWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public Driver.DriverBuilder setActivationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public Driver.DriverBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Driver.DriverBuilder setResetPassword(String resetPassword) {
            this.resetPassword = resetPassword;
            return this;
        }

        public Driver.DriverBuilder setTypeOfUser(String typeOfUser) {
            this.typeOfUser = typeOfUser;
            return this;
        }

        public Driver.DriverBuilder setIdnumber(String idnumber) {
            this.idnumber = idnumber;
            return this;
        }

        public Driver.DriverBuilder setLicense(String license) {
            this.license = license;
            return this;
        }

        public Driver build() {
            return new Driver(
                    this.id,
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
                    this.typeOfUser,
                    this.idnumber,
                    this.license);
        }

    }
    @Override
    public void updateInfoDriver(Context context, String name, String gender, String phone, String email, String address) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Successful"))
                {
                    Toast.makeText(context,"Successful",Toast.LENGTH_LONG).show();

                }else if(response.equals("Something went wrong"))
                {
                    Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
                }
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
                params.put("type","editInfoDriver");
                params.put("name", name);
                params.put("gender", gender);
                params.put("phone", phone);
                params.put("email", email);
                params.put("address", address);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }



}
