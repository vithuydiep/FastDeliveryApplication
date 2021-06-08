package com.example.TVK.Controller;

import android.content.Context;

import com.example.TVK.Model.Feedback;
import com.example.TVK.Model.IFeedback;
import com.example.TVK.Model.IOrder;
import com.example.TVK.Model.Order;
import com.example.TVK.R;
import com.example.TVK.Ultis.CallBack;
import com.example.TVK.View.FeedbackAdapter;
import com.example.TVK.View.Fragment.IListFeedback;
import com.example.TVK.View.IMainAdminActivity;
import com.example.TVK.View.MainAdminActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManageFeedbackController implements IManageFeedbackController, CallBack{
    IListFeedback iListFeedback;
    IFeedback iFeedback = new Feedback();
    IMainAdminActivity iMainAdminActivity;
    IOrder order = new Order();

    public ManageFeedbackController(IListFeedback iListFeedback) {
        this.iListFeedback = iListFeedback;
    }

    public ManageFeedbackController() {
    }

    @Override
    public FeedbackAdapter loadadapter(Context context) {
        FeedbackAdapter feedbackAdapter;
        ArrayList<Feedback> feedbackArrayList;
        feedbackArrayList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(context, R.layout.layout_custom_feedback, feedbackArrayList, new FeedbackAdapter.IClickItemListener() {
            @Override
            public void onClickItemFeedback(String idorder) {
                CallBack callBack = new ManageFeedbackController();
                Order order =new Order();
                order.findCustomerbyID(context,callBack,idorder,order);

            }
        });
        iFeedback.getAllDataFeedback(context, feedbackArrayList, feedbackAdapter);
        return feedbackAdapter;
    }

    @Override
    public void onGetDataSucess(JSONObject jsonObject, JSONArray jsonArray, String string_response, Context context, Object object) {
        if (jsonObject != null)
        {
            try {
                String start = jsonObject.getString("StartTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date starttime = sdf.parse(start);
                String end = jsonObject.getString("EndTime");
                Date endtime= sdf.parse(end);
                Order a = new Order(jsonObject.getInt("ID"),
                        jsonObject.getInt("IDUser"),
                        jsonObject.getString("Pickupaddress"),
                        jsonObject.getString("Deliveryaddress"),
                        jsonObject.getDouble("Mass"),
                        jsonObject.getString("ReceiveName"),
                        jsonObject.getString("receiverphone"),
                        jsonObject.getString("Description"),
                        jsonObject.getDouble("Postage"),
                        jsonObject.getDouble("Total"),
                        jsonObject.getString("State"),
                        starttime,
                        endtime,
                        jsonObject.getInt("IDDriver")
                );
                iMainAdminActivity = (MainAdminActivity) context;
                iMainAdminActivity.gotoDetailFragment(a);
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onResponseError(String error, Context context) {

    }

    @Override
    public void onGetDataError(String error_message, Context context) {

    }
}
