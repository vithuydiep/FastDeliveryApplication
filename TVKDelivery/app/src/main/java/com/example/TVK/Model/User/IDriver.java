package com.example.TVK.Model.User;

import android.content.Context;

public interface IDriver {
    void getAllDataDriver(Context context, ArrayList<Driver> driverArrayList, DriverAdapter adapter);
    void addNewDriver(Driver driver, Context context);
    void checkExistPhone(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);
    void updateStateDriver(Context context, String state, String id);
    void checkExistLicense(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);
    void checkExistID(Context context, CallBack callBack, EditText txtname, EditText txtphone, EditText txtgender, EditText txtemail, EditText txtidnumber, EditText txtlicense,EditText txtaddress);
    void checkExistUpdate(Context context,CallBack callBacka, EditText nameupdate,EditText editText,String id,String type);
    void updatePhone(Context context, String id, String phone);
    void updateEmail(Context context, String id, EditText nameupdate,String email);
    void updateAddress(Context context, String id,EditText nameupdate, String address);
    void updateLicense(Context context, String id, String license);
    void updateIDNumber(Context context, String id, String idnum);
    void checkExistUpdateID(Context context,CallBack callBacka, EditText nameupdate,EditText editText,String id,String type);
    void checkExistUpdateLicense(Context context,CallBack callBacka,EditText nameupdate, EditText editText,String id,String type);
    void findCustomerbyID(Context context, CallBack callBack, String id,Driver driver);
    void updateInfoDriver(Context context, String name, String gender, String phone, String email, String address);
}
