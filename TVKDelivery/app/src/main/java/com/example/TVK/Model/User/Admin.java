package com.example.TVK.Model.User;

public class Admin extends User {
    private static Admin instance;

    public Admin()
    {

    }
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }
}
