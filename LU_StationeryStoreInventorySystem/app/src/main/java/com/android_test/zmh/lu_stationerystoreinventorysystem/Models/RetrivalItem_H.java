package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiawenHuang on 6/3/15.
 */
public class RetrivalItem_H extends HashMap<String,String> {
    public RetrivalItem_H(String description, String qty){
        put("description",description);
        put("qty",qty);
    }

    public static List<RetrivalItem_H> parseJSONArray(JSONArray ja){

        List<RetrivalItem_H> list = new ArrayList<RetrivalItem_H>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                list.add(new RetrivalItem_H(jo.getString("id"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
