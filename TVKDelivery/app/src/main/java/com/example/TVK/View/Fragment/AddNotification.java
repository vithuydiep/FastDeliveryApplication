package com.example.TVK.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.TVK.Controller.IManageNotificationController;
import com.example.TVK.Controller.ManageNotificationController;
import com.example.TVK.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNotification extends Fragment implements IAddNotification {
    EditText txttitle,txtcontent;
    Button btnconfirm, btnback;
    IManageNotificationController iManageNotificationController;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNotification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNotification.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNotification newInstance(String param1, String param2) {
        AddNotification fragment = new AddNotification();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_notification, container, false);

        txttitle = view.findViewById(R.id.txttitle_notify);
        txtcontent = view.findViewById(R.id.txtcontent_notify);
        btnconfirm = view.findViewById(R.id.btnconfirm_add2);
        btnback = view.findViewById(R.id.btncancel_add2);
        iManageNotificationController = new ManageNotificationController(this);
        txttitle.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtcontent.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iManageNotificationController.onCheckData(txttitle,txtcontent,getContext());
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeAdmin()).commit();
            }
        });

        return view;
    }

    @Override
    public void toastinfor(String message) {

    }

    @Override
    public void open_dialogLoading(int gravity) {

    }

    @Override
    public void close_dialogLoading() {

    }

    @Override
    public void hideKeyboard(View view, boolean hasFocus) {
        if(!hasFocus) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showToast(String str_error) {

    }

    @Override
    public EditText getEditText(String name_editText) {
        return null;
    }
}