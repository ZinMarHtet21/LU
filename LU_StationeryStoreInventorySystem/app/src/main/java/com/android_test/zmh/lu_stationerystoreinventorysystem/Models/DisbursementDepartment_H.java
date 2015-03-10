package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by student on 7/3/15.
 */
public class DisbursementDepartment_H extends HashMap<String,String> {

   public DisbursementDepartment_H(String ID, String name, String representative, String collectionpoint) {
       put("ID", ID);
       put("name", name);
       put("representative", representative);
       put("collectionpoint", collectionpoint);
   }

       public static List<DisbursementDepartment_H> parseJSONArray(JSONArray ja){

           List<DisbursementDepartment_H> list = new ArrayList<DisbursementDepartment_H>();
           for (int i = 0; i<ja.length();i++){
               try {
                   JSONObject jo = ja.getJSONObject(i);
                   list.add(new DisbursementDepartment_H(jo.getString("id"),jo.getString("server"),jo.getString("server"),jo.getString("server")));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
           return list;


   }
}
