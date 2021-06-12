package com.example.TVK.Model.User;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.Ultis.CallBack;
import com.example.TVK.Ultis.IGetAPICallback;
import com.example.TVK.Ultis.IViewUltis;
import com.example.TVK.View.Adapter.CustomerAdapter;

import java.util.ArrayList;

public interface ICustomer {
    void getAllDataCustomer(Context context, ArrayList<Customer> customerArrayList, CustomerAdapter adapter);
    void addNewCustomter(Customer customer, Context context, IViewUltis iViewUltis, IGetAPICallback iGetAPICallback_argument);

    void checkExistPhone(Context context, CallBack callBack_ar, EditText txtcusname_add, EditText txtcusphone_add, EditText txtcusemail_add);

    void updateStateCustomer(Context context, String state, String id);
    void findCustomerbyID(Context context, CallBack callBack, String id, Customer customer);

}
