package com.example.TVK.Ultis;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GlobalUser {
    private static HashMap<String, JSONObject> hashmap;

    public static Map<String,JSONObject> getInstances()
    {
        if(hashmap==null)
        {
            hashmap = new HashMap<String,JSONObject>();
        }
        return hashmap;
    }
}
