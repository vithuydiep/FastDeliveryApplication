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
import com.example.TVK.Ultis.GlobalUser;
import com.example.TVK.View.Adapter.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order implements IOrder, Serializable {

    private int idOrder;
    private int idUser;
    private String pickupAddress;
    private String deliveryAddress;
    private double mass;
    private String receiverName;
    private String receiverPhone;
    private String description;
    private double postage;
    private double total;
    private String state;
    Date startTime;
    Date endTime;
    int idDriver;

    CallBack callBack;
    String baseUrl = "http://192.168.1.71/androidwebservce/";
    JSONObject logined_user = GlobalUser.getInstances().get("logined_user");

    public Order(int id, int idUser, String pickupaddress, String deliveryaddress, double mass, String receiverName, String receiverphone, String description, double postage, double total, String state, Date starttime, Object endTime, int idDriver) {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public double getMass() {
        return mass;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getDescription() {
        return description;
    }

    public double getPostage() {
        return postage;
    }

    public double getTotal() {
        return total;
    }

    public String getState() {
        return state;
    }


    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public Order(){}

    public Order(int idOrder, int idUser, String pickupAddress, String deliveryAddress, double mass, String receiverName, String receiverPhone, String decscription, double postage , double total, String state, Date startTime,  Date endTime, int idDriver)
    {
        this.idOrder=idOrder;
        this.idUser=idUser;
        this.pickupAddress=pickupAddress;
        this.deliveryAddress=deliveryAddress;
        this.mass=mass;
        this.receiverName=receiverName;
        this.receiverPhone=receiverPhone;
        this.description=decscription;
        this.postage=postage;
        this.total=total;
        this.state=state;
        this.startTime=startTime;
        this.endTime=endTime;
        this.idDriver=idDriver;
    }

    public void getAllDataOrder(Context context, ArrayList<Order> orderArrayList, OrderAdapter adapter, String state) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl + "getdata.php",
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
                                if (end == "null")
                                {
                                    if(object.getString("IDDriver")!= "null") {
                                        orderArrayList.add(new Order(
                                                object.getInt("ID"),
                                                object.getInt("IDUser"),
                                                object.getString("PickupAddress"),
                                                object.getString("DeliveryAddress"),
                                                object.getDouble("Mass"),
                                                object.getString("ReceiverName"),
                                                object.getString("ReceiverPhone"),
                                                object.getString("Description"),
                                                object.getDouble("Postage"),
                                                object.getDouble("Total"),
                                                object.getString("State"),
                                                starttime,
                                                null,
                                                object.getInt("IDDriver")));
                                    } else
                                        {
                                        orderArrayList.add(new Order(
                                                object.getInt("ID"),
                                                object.getInt("IDUser"),
                                                object.getString("PickupAddress"),
                                                object.getString("DeliveryAddress"),
                                                object.getDouble("Mass"),
                                                object.getString("ReceiverName"),
                                                object.getString("ReceiverPhone"),
                                                object.getString("Description"),
                                                object.getDouble("Postage"),
                                                object.getDouble("Total"),
                                                object.getString("State"),
                                                starttime,
                                                null,
                                                0
                                        ));
                                    }

                                }
                                else if(object.getString("IDDriver")=="null")
                                {
                                    Date endtime= sdf.parse(end);
                                    orderArrayList.add(new Order(
                                            object.getInt("ID"),
                                            object.getInt("IDUser"),
                                            object.getString("PickupAddress"),
                                            object.getString("DeliveryAddress"),
                                            object.getDouble("Mass"),
                                            object.getString("ReceiverName"),
                                            object.getString("ReceiverPhone"),
                                            object.getString("Description"),
                                            object.getDouble("Postage"),
                                            object.getDouble("Total"),
                                            object.getString("State"),
                                            starttime,
                                            endtime,
                                            0
                                    ));

                                } else {
                                    Date endtime= sdf.parse(end);
                                    orderArrayList.add(new Order(
                                            object.getInt("ID"),
                                            object.getInt("IDUser"),
                                            object.getString("PickupAddress"),
                                            object.getString("DeliveryAddress"),
                                            object.getDouble("Mass"),
                                            object.getString("ReceiverName"),
                                            object.getString("ReceiverPhone"),
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
                switch (state){
                    case "DANGXULY":
                        params.put("type","getDataOrder1");
                        break;
                    case "DANGGIAO":
                        params.put("type","getDataOrder2");
                        try {
                            params.put("iddriver", logined_user.getString("ID"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DAGIAO":
                        params.put("type","getDataOrder3");
                        try {
                            params.put("iddriver", logined_user.getString("ID"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }


    public void updateStateOrder(Context context, String state, int id) {
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
                params.put("type","editStateOrder");
                params.put("id",String.valueOf(id));
                params.put("state",state);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateIddriverOrder(Context context, int iddriver, int id) {
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
                params.put("type","editIddriverOrder");
                params.put("id",String.valueOf(id));
                params.put("iddriver", String.valueOf(iddriver));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateEndtimeOrder(Context context, Date endTime, int id) {
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
                SimpleDateFormat sdf = new SimpleDateFormat();
                params.put("type","editEndtimeOrder");
                params.put("id",String.valueOf(id));
                params.put("endTime", String.valueOf(endTime));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
