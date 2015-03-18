package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDisbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItemList;
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
    public final static String receiveDisbURL = disbURL + "/confirmcollect";

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

//    @Override
//    public JSONObject receiveDisbursementList(int deptID, String remark, List<DisbursementItemList> disbList) {
//     //   String result = null;
//       // String json = null;
//        if(remark.equals("")){
//            remark = "Received no remark";
//        }
//
//        System.out.println("RECEIVE DISBURSEMENT LIST");
//        System.out.println("DEPT ID : " + deptID);
//        System.out.println("REMARK : " + remark);
//        System.out.println("DISBURSEMENT LIST : " + disbList.toString());
//        JSONObject obj = new JSONObject();
//        JSONArray arr = new JSONArray();
//        try{
//
//            obj.put("departmentID",deptID);
//            obj.put("remark",remark);
//
//            for(int i =0; i<disbList.size();i++){
//
//                JSONObject itemdetail = new JSONObject();
//                itemdetail.put("itemCode",disbList.get(i).getItemCode());
//                itemdetail.put("lblAcutal",disbList.get(i).getLblAcutal());
//
//                arr.put(obj);
//            }
//            obj.put("list",arr);
//
//          //  obj.put("list",disbList);
//          //  json = obj.toString();
//           // result = JSONParser.postStream(String.format("%s",receiveDisbURL),json);
//        }catch(Exception e){
//            Log.e("Receive Disbursement List", "JSON Error");
//        }
//        Log.i("Json",obj.toString());
//
//        System.out.println("CONVERTED JSON");
//        System.out.println(obj);
//
//        return obj;
//    }

//
    @Override
    public String receiveDisbursementList(int deptID, String remark, List<DisbursementItemList> disbList) {
        String result = null;
       // String json = null;
        if(remark.equals("")){
            remark = "Received no remark";
        }

        System.out.println("RECEIVE DISBURSEMENT LIST");
        System.out.println("DEPT ID : " + deptID);
        System.out.println("REMARK : " + remark);
        System.out.println("DISBURSEMENT LIST : " + disbList.toString());

        JSONObject obj = new JSONObject();
        JSONArray jar = new JSONArray();
        try{

            obj.put("departmentID",deptID);
            obj.put("remark",remark);

            for(int i =0; i<disbList.size();i++){

                String itemcode = disbList.get(i).getItemCode();
                String lalActual = String.valueOf(disbList.get(i).getLblAcutal());

                JSONObject itemdetail = new JSONObject();
                itemdetail.put("itemCode",itemcode);
                itemdetail.put("lblAcutal",lalActual);

                jar.put(itemdetail);
            }
           obj.put("list",jar);
         //   System.out.print("JSONSSSSS"+obj.toString());

         //   obj.put("list",disbList);
            String json = obj.toString();
            result = JSONParser.postStream(String.format("%s",receiveDisbURL),json);
        }catch(Exception e){
            Log.e("Receive Disbursement List", "JSON Error");
        }

    //    System.out.println("CONVERTED JSON");
     //   System.out.println(json);

        return result;
    }


}
