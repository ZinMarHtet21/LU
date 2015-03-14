package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by student on 6/3/15.
 */
public class RequisitionPopulator implements IRequisition {

    public final static String baseurl = UrlManager.APIROOTURL;
    public final static String reqHistoryURL = baseurl +"requisitionsApi/history/";
    public final static String reqListURL = baseurl + "requisitionsApi/department/Pending/";
    public final static String reqDetailURL = baseurl + "requisitionsApi/detail/";

    @Override
    public List<Requisition> getRequisitionHistoryList(int deptID) {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",reqHistoryURL, deptID));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Requisition req = new Requisition();
                req.setId(obj.getString("requisition_id").toString());
                req.setDate(obj.getString("requisition_date").toString());
                req.setStatus(obj.getString("requisition_status").toString());
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);
    }

//    @Override
//    public List<RequisitionDetail> getRequisitionHistoryDetails(String req_id, String url) {
//        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
//        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",reqDetailURL,req_id));
//
//        try {
//            for (int i =0; i < arr.length(); i++) {
//
//                JSONObject obj = arr.getJSONObject(i);
//                RequisitionDetail req = new RequisitionDetail();
//                req.setId(obj.getString("requisition_detail_id").toString());
//                //req.setQty(Integer.parseInt(b.getString("requisition_detail_qty").toString()));
//                req.setItemName(obj.getString("item_item_id").toString());//NEed to change
//                list.add(req);
//            }
//
//        } catch (Exception e) {
//            Log.e("list", "JSONArray error");
//        }
//
//        return(list);
//    }

    @Override
    public List<Requisition> getRequisitionList(int empID) {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",reqListURL,empID));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Requisition req = new Requisition();
                req.setId(obj.getString("requisition_id").toString());
                req.setDate(obj.getString("requisition_date").toString());
                req.setStatus(obj.getString("requisition_status").toString());
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);
    }

//    @Override
//    public List<RequisitionDetail> getRequisitionListDetails(String req_id, String url) {
//        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
//        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",reqDetailURL,req_id));
//
//        try {
//            for (int i =0; i < arr.length(); i++) {
//
//                JSONObject obj = arr.getJSONObject(i);
//                RequisitionDetail req = new RequisitionDetail();
//                req.setId(obj.getString("requisition_detail_id").toString());
//                //req.setQty(Integer.parseInt(b.getString("requisition_detail_qty").toString()));
//                req.setItemName(obj.getString("item_item_id").toString());//NEed to change
//                list.add(req);
//            }
//
//        } catch (Exception e) {
//            Log.e("list", "JSONArray error");
//        }
//
//        return(list);
//    }

    @Override
    public List<RequisitionDetail> getRequisitionDetail(String reqID, String url) {
        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",reqDetailURL,reqID));

        try {
            for (int i =0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);
                RequisitionDetail reqDetail = new RequisitionDetail();
                reqDetail.setId(obj.getInt("requisition_detail_id"));
                reqDetail.setItemName(obj.getString("item"));
                reqDetail.setQty(obj.getInt("requisition_detail_qty"));
                //reqDetail.setActualQty(obj.getInt(""));
                list.add(reqDetail);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        System.out.println("Requisition History Detail");
        System.out.println(list.toString());
        return(list);
    }


}
