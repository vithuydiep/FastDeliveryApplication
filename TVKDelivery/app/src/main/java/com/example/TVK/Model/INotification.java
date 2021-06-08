package com.example.TVK.Model;

import android.content.Context;
import android.widget.EditText;

public interface INotification {
    void addNewNotify(EditText title, EditText content, Context context);
}
