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

    public Order(int id, int idUser, String pickupaddress, String deliveryaddress, double mass, String receiveName, String receiverphone, String description, double postage, double total, String state, Date starttime, Date endtime, int idDriver){}

    public Order(int idOrder, int idUser, String pickupAddress, String deliveryAddress, double mass, String receiverName, String receiverPhone, String decscription, int postage , int total, String state, Date startTime,  Date endTime, int idDriver)
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

    public void createOrder(Context context, Order order, ICreateEditOrderCustomerController iCreateEditOrderCustomerController)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iCreateEditOrderCustomerController.showToast(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCreateEditOrderCustomerController.showToast(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "createOrder");
                params.put("iduser", String.valueOf(order.getIdUser()));
                params.put("pickupaddress", order.getPickupAddress());
                params.put("deliveryaddress", order.getDeliveryAddress());
                params.put("mass", String.valueOf((int)order.getMass()));
                params.put("receivername", order.getReceiverName());
                params.put("receiverphone", order.getReceiverPhone());
                params.put("description", order.getDescription());
                params.put("postage", String.valueOf((int)order.getPostage()));
                params.put("total", String.valueOf((int)order.getTotal()));
                params.put("state", order.getState());
                params.put("startTime", order.getStartTime().toString());
                return params;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }

    public void cancelOrder(Context context, int idOrder, IOrderMByStatusController iOrderMByStatusController)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, baseUrl + "getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iOrderMByStatusController.responseCancel(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iOrderMByStatusController.responseCancel(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "cancelOrder");
                params.put("idorder", String.valueOf(idOrder));
                return params;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }

    public void getOrderByStatus(Context context, String status, IOrderMByStatusController iOrderMByStatusController) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        List<Order> orderArrayList = new ArrayList<Order>();
        StringRequest jsonArrayRequest= new StringRequest(Request.Method.POST, baseUrl+"getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("Error"))
                        {
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
                                            Order current_order = new Order();
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setIdUser(object.getInt("IDUser"));
                                            current_order.setPickupAddress(object.getString("PickupAddress"));
                                            current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                            current_order.setMass(object.getDouble("Mass"));
                                            current_order.setReceiverName( object.getString("ReceiverName"));
                                            current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                            current_order.setDescription(object.getString("Description"));
                                            current_order.setPostage(object.getDouble("Postage"));
                                            current_order.setTotal(object.getDouble("Total"));
                                            current_order.setState(object.getString("State"));
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setStartTime(starttime);
                                            current_order.setEndTime(null);
                                            current_order.setIdDriver(object.getInt("IDDriver"));
                                            orderArrayList.add(current_order);
                                        }else {
                                            Order current_order = new Order();
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setIdUser(object.getInt("IDUser"));
                                            current_order.setPickupAddress(object.getString("PickupAddress"));
                                            current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                            current_order.setMass(object.getDouble("Mass"));
                                            current_order.setReceiverName( object.getString("ReceiverName"));
                                            current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                            current_order.setDescription(object.getString("Description"));
                                            current_order.setPostage(object.getDouble("Postage"));
                                            current_order.setTotal(object.getDouble("Total"));
                                            current_order.setState(object.getString("State"));
                                            current_order.setIdOrder(object.getInt("ID"));
                                            current_order.setStartTime(starttime);
                                            current_order.setEndTime(null);
                                            current_order.setIdDriver(0);
                                            orderArrayList.add(current_order);
                                        }

                                    }else if(object.getString("IDDriver")=="null")
                                    {
                                        Date endtime= sdf.parse(end);
                                        Order current_order = new Order();
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setIdUser(object.getInt("IDUser"));
                                        current_order.setPickupAddress(object.getString("PickupAddress"));
                                        current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                        current_order.setMass(object.getDouble("Mass"));
                                        current_order.setReceiverName( object.getString("ReceiverName"));
                                        current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                        current_order.setDescription(object.getString("Description"));
                                        current_order.setPostage(object.getDouble("Postage"));
                                        current_order.setTotal(object.getDouble("Total"));
                                        current_order.setState(object.getString("State"));
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setStartTime(starttime);
                                        current_order.setEndTime(endtime);
                                        current_order.setIdDriver(0);
                                        orderArrayList.add(current_order);

                                    }else {
                                        Date endtime= sdf.parse(end);
                                        Order current_order = new Order();
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setIdUser(object.getInt("IDUser"));
                                        current_order.setPickupAddress(object.getString("PickupAddress"));
                                        current_order.setDeliveryAddress(object.getString("DeliveryAddress"));
                                        current_order.setMass(object.getDouble("Mass"));
                                        current_order.setReceiverName( object.getString("ReceiverName"));
                                        current_order.setReceiverPhone(object.getString("ReceiverPhone"));
                                        current_order.setDescription(object.getString("Description"));
                                        current_order.setPostage(object.getDouble("Postage"));
                                        current_order.setTotal(object.getDouble("Total"));
                                        current_order.setState(object.getString("State"));
                                        current_order.setIdOrder(object.getInt("ID"));
                                        current_order.setStartTime(starttime);
                                        current_order.setEndTime(endtime);
                                        current_order.setIdDriver(object.getInt("IDDriver"));
                                        orderArrayList.add(current_order);
                                    }
                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                    Log.i("loi",e.toString());
                                }
                            }
                        }
                        iOrderMByStatusController.setListOrder(orderArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iOrderMByStatusController.showToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type","getOrderByStatus");
                params.put("status",status);
                params.put("iduser", String.valueOf(GlobalUser.getInstanceCustomer("CUSTOMER").getIdUser()));
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
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
