package com.example.TVK.Controller;

import android.content.Context;
import android.widget.EditText;

import com.example.TVK.Model.User.Driver;

public interface ILoginController {
    void OnLogin(EditText userName, EditText passWord, Context context);
}
