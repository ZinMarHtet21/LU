package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.NewRequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.NewRequisitionObject;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    public final static String reqHistoryURL = baseurl + "requisitionsApi/history/";
    public final static String reqListURL = baseurl + "requisitionsApi/department/Pending/";
    public final static String reqDetailURL = baseurl + "requisitionsApi/detail/";
    public final static String reqSendNewURL = baseurl + "requisitionsApi/create";

    @Override
    public List<Requisition> getRequisitionHistoryList(int deptID) {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s", reqHistoryURL, deptID));

        try {

            for (int i = 0; i < arr.length(); i++) {
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

        return (list);
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
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s", reqListURL, empID));

        try {

            for (int i = 0; i < arr.length(); i++) {
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

        return (list);
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
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s", reqDetailURL, reqID));

        try {
            for (int i = 0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);
                RequisitionDetail reqDetail = new RequisitionDetail();
                reqDetail.setId(obj.getInt("requisition_detail_id"));
                reqDetail.setItemName(obj.getString("item"));
                reqDetail.setItem_detail_qty(obj.getInt("requisition_detail_qty"));
                //reqDetail.setActualQty(obj.getInt(""));
                list.add(reqDetail);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        System.out.println("Requisition History Detail");
        System.out.println(list.toString());
        return (list);
    }

    @Override
    public String sendNewRequisition(int empID, ArrayList<RequisitionDetail> reqDetails) {


        String result = null;
        NewRequisitionObject newRequisitionObject = new NewRequisitionObject();
        newRequisitionObject.setEmpID(empID);


        List<NewRequisitionDetail> newRequisitionDetails = new ArrayList<NewRequisitionDetail>();
        for (int i = 0; i < reqDetails.size(); i++) {

            NewRequisitionDetail ne = new NewRequisitionDetail();
            ne.setItem_id(reqDetails.get(i).getItem_id());
            ne.setItem_detail_qty(reqDetails.get(i).getItem_detail_qty());
            newRequisitionDetails.add(ne);
        }
        newRequisitionObject.setReq_detail_list(newRequisitionDetails);
        Gson gson = new Gson();

        String json = gson.toJson(newRequisitionObject);

        result = JSONParser.postStream(String.format("%s", reqSendNewURL), json);
        return result;
    }
}


//        String result = null;
//
//        try {
//
//            JSONObject reqDetailObj = new JSONObject();
//            reqDetailObj.put("emp_id", empID);
//            reqDetailObj.put("req_detail_list", reqDetailsArr);
//            String json = reqDetailObj.toString();
//
//            System.out.println("JSON NEW REQ");
//            System.out.println(json);
//            //************ NEED URL TO POST TO SERVER
//            result = JSONParser.postStream(String.format("%s", reqSendNewURL), json);
//
//        } catch (Exception e) {
//            Log.e("Update Employee Profile", "JSON Error");
//        }
//
//        System.out.println("JSON NEW REQ");
//        System.out.println(result);
//
//        return result;
//    }
//}


