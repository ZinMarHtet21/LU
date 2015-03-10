package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;

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

    public final static String baseurl = URLman.base+"/requisitionsApi/new";
   // public final static String baseurl = "http://10.10.1.200/LU_Store_MvcV1/api/requisitionsApi/new";


    @Override
    public List<Requisition> populateRequisition() {
      /*
        List<Requisition> req_list = new LinkedList<Requisition>();
        List<RequisitionDetail> reqDetails_list = new ArrayList<RequisitionDetail>();

        // new Requisition
        Requisition newReq = new Requisition("Req01","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));
        Requisition newReq2 = new Requisition("Req02","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));
        Requisition newReq3 = new Requisition("Req03","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));

        // some details....
        RequisitionDetail details1 = new RequisitionDetail("Req01","Item01",10,5);
        RequisitionDetail details2 = new RequisitionDetail("Req01","Item02",10,5);
        RequisitionDetail details3 = new RequisitionDetail("Req01","Item03",10,5);

        // Create Details List
        // Map details into Requisition
        reqDetails_list.add(details1);
        reqDetails_list.add(details2);
        reqDetails_list.add(details3);

        // set the reqdetails into the a single Req.
        newReq.setRequisitionDetails(reqDetails_list);
        newReq2.setRequisitionDetails(reqDetails_list);
        newReq3.setRequisitionDetails(reqDetails_list);

        // save the req into the list of req,,
        req_list.add(newReq);
        return req_list;
        */
        return null;
    }

    @Override
    public ArrayList<Requisition> populateRequisitionList(JSONArray ja) {

        ArrayList<Requisition> list = new ArrayList<Requisition>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject obj = ja.getJSONObject(i);
                Requisition req = new Requisition();
                req.setId(obj.getString("requisition_id"));
                req.setMadeBy(obj.getString("requisition_made_by"));
                req.setDate(obj.getString("requisition_date"));
                req.setStatus(obj.getString("requisition_status"));
                req.setRemark(obj.getString("requisition_status"));

                /*JSONArray  detailArray = obj.getJSONArray("requisition_detail");
                for (int j =0; j<detailArray.length();j++){
                    RequisitionDetail rd = new RequisitionDetail();
                    rd.setId(detailArray.getJSONObject(j).getString("  "));
                    rd.setActualQty(detailArray.getJSONObject(j).getInt(""));
                    rd.setItemName(detailArray.getJSONObject(j).getString(""));
                    rd.setQty(detailArray.getJSONObject(j).getInt(""));
                }
                */
                //req.setRequisitionDetails(obj.getJSONArray("requisition_detail"));
                list.add(req);
                // list.add(new (jo.getString("id"),jo.getString("server"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;

    }


    public List<Requisition> getRequisitionList() {
        List<Requisition> list = new ArrayList<Requisition>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(String.format("%s",baseurl));
        System.out.println("JSONArray");
        System.out.println(a);


        try {
           // JSONObject jo = a.getJSONObject(0);
            //System.out.println("JSONOBJECT");
            //System.out.println(jo);
            for (int i =0; i<a.length(); i++) {

                JSONObject b = a.getJSONObject(i);
               // System.out.println("JSONObject");
                //System.out.println(b.toString());
                Requisition req = new Requisition();
                req.setId(b.getString("requisition_id").toString());
                req.setDate(b.getString("requisition_date").toString());
                req.setStatus(b.getString("requisition_status").toString());
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);

    }


    public List<RequisitionDetail> getRequisitionDetails(String req_id, String url)
    {
        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(String.format("%s/%s",url,req_id));
        System.out.println("JSONArray Req_details...");
        System.out.println(a);


        try {
            for (int i =0; i<a.length(); i++) {

                JSONObject b = a.getJSONObject(i);
                // System.out.println("JSONObject");
                //System.out.println(b.toString());
                RequisitionDetail req = new RequisitionDetail();
                req.setId(b.getString("requisition_detail_id").toString());
                req.setQty(Integer.parseInt(b.getString("requisition_detail_qty").toString()));
                req.setItemName(b.getString("item_item_id").toString());
                list.add(req);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);

    }
    /*
    public static Product getProduct(String id) {
        Product p = null;
        try {
            JSONObject a = JSONParser.getJSONFromUrl(String.format("%s/Service.svc/GetProduct/%s", baseurl, id));
            p = new Product(a.getString("Id"), a.getString("Name"), a.getDouble("Price"), a.getInt("Quantity"));
        } catch (Exception e) {
            Log.e("getProduct", "JSON error");
        }
        return p;
    }

    public static void updateProduct(String id, String name, String price, String quantity) {
        try {
            JSONObject customer = new JSONObject();
            customer.put("Id", id);
            customer.put("Name", name);
            customer.put("Price", price);
            customer.put("Quantity", quantity);
            String json = customer.toString();
            String result = JSONParser.postStream(
                    String.format("%s/Service.svc/UpdateProduct", baseurl),
                    json);
        } catch (Exception e) {
            Log.e("updateProduct", "JSON error");
        }
    }
    */
}
