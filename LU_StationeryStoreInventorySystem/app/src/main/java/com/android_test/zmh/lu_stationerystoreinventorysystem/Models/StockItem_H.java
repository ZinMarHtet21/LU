package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiawenHuang on 5/3/15.
 */
public class StockItem_H extends HashMap<String,String> {

    public StockItem_H(String description, String reorderlevel, String balance) {
        put("description", description);
        put("reorderlevel", reorderlevel);
        put("balance", balance);
    }

    public static List<StockItem_H> parseJSONArray(JSONArray ja){

        List<StockItem_H> list = new ArrayList<StockItem_H>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                list.add(new StockItem_H(jo.getString("id"),jo.getString("server"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }







}
