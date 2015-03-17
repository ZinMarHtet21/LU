package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDisbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class DisbursementPopulator implements IDisbursement {
    public final static String baseurl = UrlManager.APIROOTURL;
    public final static String disbURL = baseurl + "disbursementlistApi";
    public final static String receiveDisbURL = disbURL + "";


//    @Override
//    public List<Disbursement> populateDisbursement() {
//
//        List<Disbursement> disb_list = new LinkedList<Disbursement>();
//
//        Disbursement disbursement = new Disbursement("Dis01",(new Date(System.currentTimeMillis())),"Clip","ENGL",10,30);
//        Disbursement disbursement2 = new Disbursement("Dis02",(new Date(System.currentTimeMillis())),"Envelope","COMM",5,20);
//        Disbursement disbursement3 = new Disbursement("Dis03",(new Date(System.currentTimeMillis())),"File Transparent","ZOO",7,56);
//
//        disb_list.add(disbursement);
//        disb_list.add(disbursement2);
//        disb_list.add(disbursement3);
//
//        return disb_list;
//    }

    public List<DisbursementItem> getDisbursementList(int departmentID) {
        List<DisbursementItem> list = new ArrayList<DisbursementItem>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%d",disbURL,departmentID));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                DisbursementItem disb = new DisbursementItem();
                 disb.setItem_code(obj.getString("item_code").toString());
                disb.setItem_desc(obj.getString("item_desc").toString());
                disb.setItem_qty(obj.getInt("item_qty"));
                disb.setItem_actual(obj.getInt("item_actual"));
                list.add(disb);
            }

        }catch(Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);
   }

    @Override
    public String receiveDisbursementList(List<DisbursementItem> disbList) {

        String result = null;
        Gson gson = new Gson();

        String json = gson.toJson(disbList);

        System.out.println("CONVERTED JSON");
        System.out.println(json);


//        result = JSONParser.postStream(String.format("%s", receiveDisbURL), json);
        return result;
    }


}
