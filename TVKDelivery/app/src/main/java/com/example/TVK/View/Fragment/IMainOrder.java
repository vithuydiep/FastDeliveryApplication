package com.example.TVK.View.Fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface IMainOrder {
    void setAdaper(View view,  String type);
    void changeFragment(Fragment fragment);
    void notifyerror(String message);

}
