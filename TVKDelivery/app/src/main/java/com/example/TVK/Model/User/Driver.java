package com.example.TVK.Model.User;

public class Driver extends User {
    private static Driver instance;
    private String gplx;

    public String getGplx() {
        return gplx;
    }

    public void setGplx(String gplx) {
        this.gplx = gplx;
    }

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
