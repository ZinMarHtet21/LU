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
    public final static String approveRejectURL = baseurl + "requisitionsApi/approve";

    @Override
    public List<Requisition> getRequisitionHistoryList(int deptID) {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s", reqHistoryURL, deptID));

        try {

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Requisition req = new Requisition();
                req.setId(obj.getString("requisition_id").toString());
                String date = obj.getString("requisition_date").toString();
                String remark = obj.getString("requisition_remark").toString();
                String substringDate = date.substring(0, 10);
                String formattedDate = dateFormatter(substringDate);
                req.setDate(formattedDate);
                req.setStatus(obj.getString("requisition_status").toString());
                req.setRemark(remark);
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return (list);
    }

    public String dateFormatter(String dateString){

        String strCurrentDate = dateString;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = format.format(newDate);
        return formattedDate;

    }

    @Override
    public List<Requisition> getRequisitionList(int empID) {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s/%s", reqListURL, empID));

        try {

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Requisition req = new Requisition();
                req.setId(obj.getString("requisition_id").toString());
                String date = obj.getString("requisition_date").toString();
                String substringDate = date.substring(0, 10);
                String formattedDate = dateFormatter(substringDate);
                req.setDate(formattedDate);
                req.setStatus(obj.getString("requisition_status").toString());
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return (list);
    }

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
                list.add(reqDetail);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

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

    @Override
    public String sendRequisitionResponseByHOD(String reqID, String remark, String outcome) {

        String result = null;
        try{
            String jsonString;
            JSONObject obj = new JSONObject();
//            obj.put("approved_by",approvedBy);
            obj.put("requisitionID",reqID);
            obj.put("remark",remark);
            obj.put("outcome",outcome);
            String json = obj.toString();
            result = JSONParser.postStream(String.format("%s",approveRejectURL),json);

        }catch(Exception e){
            Log.e("Approve Reject Requisition","JSON Error");
        }
        return result;
    }
}
