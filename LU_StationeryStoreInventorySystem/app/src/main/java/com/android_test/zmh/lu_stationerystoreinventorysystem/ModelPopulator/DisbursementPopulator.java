package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDisbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

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
    public final static String disbURL = baseurl + "disbursement_listApi";


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

    public List<Disbursement> getDisbursementList() {
        List<Disbursement> list = new ArrayList<Disbursement>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",disbURL));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                Disbursement disb = new Disbursement();

                disb.setId(obj.getString("disbursement_list_id").toString());
//                disb.setDate(obj.getString("").toString());
//                disb.setItemName(obj.getString("").toString());
//                disb.setDepartmentName(obj.getString("").toString());
                disb.setQty(obj.getInt("disbursement_list_item_qty"));
//               disbursement_list_item_qty disb.setActualQty(obj.getInt(""));

                list.add(disb);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);

    }

    @Override
    public List<RequisitionDetail> getDisbursementListDetails(String req_id, String url) {
        return null;
    }


}
