package com.example.TVK.Model;

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

import java.util.HashMap;
import java.util.Map;

public class Notification implements  INotification{
    private int id;
    private String title;
    private String content;
    public String baseUrl = "http://192.168.1.7/androidwebservce/";
    public Notification(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Notification(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void addNewNotify(EditText title, EditText content, Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Successful"))
                        {
                            Toast.makeText(context, "Add New Notification Successfully",Toast.LENGTH_LONG).show();
                            title.clearFocus();
                            content.clearFocus();
                            title.getText().clear();
                            content.getText().clear();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","newnotify");
                params.put("title",title.getText().toString().trim());
                params.put("content",content.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }
}
