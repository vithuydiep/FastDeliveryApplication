package com.example.TVK.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.TVK.Model.INotification;
import com.example.TVK.Model.Notification;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.Fragment.IAddNotification;

import org.json.JSONArray;
import org.json.JSONObject;

public class ManageNotificationController implements IManageNotificationController, CallBack {
    IAddNotification iAddNotification;
    INotification iNotification = new Notification();
    String title, content;

    public ManageNotificationController(IAddNotification iAddNotification) {
        this.iAddNotification = iAddNotification;
    }

    public ManageNotificationController() {
    }


    @Override
    public void onCheckData(EditText txttitle, EditText txtcontent, Context context) {
        title = txttitle.getText().toString().trim();
        content = txtcontent.getText().toString().trim();
        if(TextUtils.isEmpty(title))
        {
            txttitle.requestFocus();
            txttitle.setError("Feild can not be left blank");
            return;
        }
        if(TextUtils.isEmpty(content))
        {
            txtcontent.requestFocus();
            txtcontent.setError("Feild can not be left blank");
            return;
        }
        iNotification.addNewNotify(txttitle,txtcontent,context);

    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {

    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
}
