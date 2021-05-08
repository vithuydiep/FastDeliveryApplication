package com.example.TVK.Model.User;

import com.example.TVK.Model.IOrder;
import com.example.TVK.Ultis.IGetAPICallback;

public class Driver extends User {
    private static Driver instance;
    private String gplx;


    public String getGplx() {
        return gplx;
    }

    public void setGplx(String gplx) {
        this.gplx = gplx;
    }

    public Driver(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser, String gplx) {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser);
        this.gplx = gplx;
    }
    private IGetAPICallback iGetAPICallback;
    private IOrder iOrder;

    String baseUrl = "http://192.168.1.6/androidwebservce/";

    public Driver()
    {

    }
    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }


}
