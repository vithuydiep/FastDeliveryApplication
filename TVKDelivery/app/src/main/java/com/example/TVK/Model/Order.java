package com.example.TVK.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.Fragment.ListOrder;
import com.example.TVK.View.IMainAdminActivity;
import com.example.TVK.View.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order implements IOrder, Serializable {
    private int id;
    private int iduser;
    private String pickupAddress;
    private String receiveAddress;
    private double mass;
    private String receiveName;
    private String receivePhone;
    private String description;
    private double postage;
    private double total;
    private String state;
    private Date startTime;
    private Date endTime;
    private int iddriver;

    String baseUrl = "http://192.168.1.7/androidwebservce/";
    public Order() {
    }


    public Order(int id, int iduser, String pickupAddress, String receiveAddress, double mass, String receiveName, String receivePhone, String description, double postage, double total, String state, Date startTime, Date endTime) {
        this.id = id;
        this.iduser = iduser;
        this.pickupAddress = pickupAddress;
        this.receiveAddress = receiveAddress;
        this.mass = mass;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.description = description;
        this.postage = postage;
        this.total = total;
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public Order(int id, int iduser, String pickupAddress, String receiveAddress, double mass, String receiveName, String receivePhone, String description, double postage, double total, String state, Date startTime, Date endTime, int iddriver) {
        this.id = id;
        this.iduser = iduser;
        this.pickupAddress = pickupAddress;
        this.receiveAddress = receiveAddress;
        this.mass = mass;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.description = description;
        this.postage = postage;
        this.total = total;
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
        this.iddriver = iddriver;
    }

    public int getIddriver() {
        return iddriver;
    }

    public void setIddriver(int iddriver) {
        this.iddriver = iddriver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public void getAllDataOrder(Context context, ArrayList<Order> orderArrayList, OrderAdapter adapter,String type) {
        String namequery = null;
        if(type.equals("ALLORDER"))
        {
            namequery="getDataOrder";
        }else if(type.equals("RECENT"))
        {
            namequery="getDataOrderRecent";
        }else if(type.equals("COMPLETED"))
        {
            namequery="getDataOrderCompleted";
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String finalNamequery = namequery;
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
                                String start = object.getString("StartTime");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date starttime = sdf.parse(start);
                                String end = object.getString("EndTime");
                                if (end=="null")
                                {
                                    if(object.getString("IDDriver")!= "null") {
                                        orderArrayList.add(new Order(
                                                object.getInt("ID"),
                                                object.getInt("IDUser"),
                                                object.getString("Pickupaddress"),
                                                object.getString("Deliveryaddress"),
                                                object.getDouble("Mass"),
                                                object.getString("ReceiveName"),
                                                object.getString("receiverphone"),
                                                object.getString("Description"),
                                                object.getDouble("Postage"),
                                                object.getDouble("Total"),
                                                object.getString("State"),
                                                starttime,
                                                null,
                                                object.getInt("IDDriver")));
                                    }else {
                                        orderArrayList.add(new Order(
                                                object.getInt("ID"),
                                                object.getInt("IDUser"),
                                                object.getString("Pickupaddress"),
                                                object.getString("Deliveryaddress"),
                                                object.getDouble("Mass"),
                                                object.getString("ReceiveName"),
                                                object.getString("receiverphone"),
                                                object.getString("Description"),
                                                object.getDouble("Postage"),
                                                object.getDouble("Total"),
                                                object.getString("State"),
                                                starttime,
                                                null,
                                                0
                                        ));
                                    }

                                }else if(object.getString("IDDriver")=="null")
                                {
                                    Date endtime= sdf.parse(end);
                                    orderArrayList.add(new Order(
                                            object.getInt("ID"),
                                            object.getInt("IDUser"),
                                            object.getString("Pickupaddress"),
                                            object.getString("Deliveryaddress"),
                                            object.getDouble("Mass"),
                                            object.getString("ReceiveName"),
                                            object.getString("receiverphone"),
                                            object.getString("Description"),
                                            object.getDouble("Postage"),
                                            object.getDouble("Total"),
                                            object.getString("State"),
                                            starttime,
                                            endtime,
                                            0
                                    ));

                                }else {
                                    Date endtime= sdf.parse(end);
                                    orderArrayList.add(new Order(
                                            object.getInt("ID"),
                                            object.getInt("IDUser"),
                                            object.getString("Pickupaddress"),
                                            object.getString("Deliveryaddress"),
                                            object.getDouble("Mass"),
                                            object.getString("ReceiveName"),
                                            object.getString("receiverphone"),
                                            object.getString("Description"),
                                            object.getDouble("Postage"),
                                            object.getDouble("Total"),
                                            object.getString("State"),
                                            starttime,
                                            endtime,
                                            object.getInt("IDDriver")
                                    ));
                                }
                            } catch (JSONException | ParseException e) {
                                e.printStackTrace();
                                Log.i("loi",e.toString());
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
                params.put("type", finalNamequery);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }


    public void deleteOrder(Context context,String id)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Successful"))
                        {
                            Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
                            IMainAdminActivity iMainAdminActivity = (IMainAdminActivity) context;
                            iMainAdminActivity.loadFragment(new ListOrder());
                        }else if (response.equals("Something went wrong")){
                            Toast.makeText(context, "Can't delete order", Toast.LENGTH_LONG).show();
                        }
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","deleteOrder");
                params.put("id",id);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void findCustomerbyID(Context context, CallBack callBack, String id, Order order)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Error"))
                        {
                            callBack.onGetDataSucess(null,null,response,context,order);
                            Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        }else {
                            try {
                                JSONObject object = new JSONObject(response);
                                callBack.onGetDataSucess(object,null,"customer",context,order);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","findOrderbyID");
                params.put("id",id);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}
