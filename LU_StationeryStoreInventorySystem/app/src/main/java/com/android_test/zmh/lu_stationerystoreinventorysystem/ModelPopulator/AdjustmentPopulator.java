package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;


import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IAdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucherDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by student on 6/3/15.
 */
public class AdjustmentPopulator implements IAdjustmentVoucher {
   private List<AdjustmentVoucher> managerlist= new LinkedList<AdjustmentVoucher>() ;
    private List<AdjustmentVoucher> supervisorlist = new LinkedList<AdjustmentVoucher>();
    public final static String baseurl = UrlManager.APIROOTURL+"stockAdjustmentApi/pending/";

    @Override
    public List<AdjustmentVoucher> populateAdjustmentVoucher() {

        List<AdjustmentVoucher> adv_list = new LinkedList<AdjustmentVoucher>();

        List<AdjustmentVoucherDetail> avDetails_list = new ArrayList<AdjustmentVoucherDetail>();
        List<AdjustmentVoucherDetail> avDetails2_list = new ArrayList<AdjustmentVoucherDetail>();

        AdjustmentVoucher newAv = new AdjustmentVoucher("1", "DDS/111/98", "2014_09-08", "","");
        AdjustmentVoucher newAv2 = new AdjustmentVoucher("2", "DDS/113/99", "2014-09-09", "","");


        AdjustmentVoucherDetail details1 = new AdjustmentVoucherDetail("1","Clips", 10, 3.4);
        AdjustmentVoucherDetail details2 = new AdjustmentVoucherDetail("2","Pad", 20, 5);
        AdjustmentVoucherDetail details3 = new AdjustmentVoucherDetail("3","Clips", 70, 3.4);
        AdjustmentVoucherDetail details4 = new AdjustmentVoucherDetail("4","Pad", 25, 5);


        avDetails_list.add(details1);
        avDetails_list.add(details2);
        avDetails2_list.add(details3);
        avDetails2_list.add(details4);



        newAv.setAdjustmentVoucherDetails(avDetails_list);
        newAv2.setAdjustmentVoucherDetails(avDetails2_list);


        adv_list.add(newAv);
        adv_list.add(newAv2);
        return adv_list;
       /* for (AdjustmentVoucherDetail ad : model.getAdjustmentVoucherDetails()) {

            amount = amount + ad.getPrice() * ad.getQty();
        }*/
    }
    //AdjustmentVoucher model;
    List<AdjustmentVoucher> list = populateAdjustmentVoucher();
    @Override
    public void caculateAmount(List<AdjustmentVoucher> list){
        double amount=0;
        for (AdjustmentVoucher model : list){
            for (AdjustmentVoucherDetail ad:model.getAdjustmentVoucherDetails()){
                amount =amount +ad.getQty()*ad.getPrice();

            }
            if(amount>=250){

                managerlist.add(model);

            }
            else {

                supervisorlist.add(model);
            }
        }

    }
    @Override
    public List<AdjustmentVoucher> populateManagerList(){
        caculateAmount(list);
        return managerlist;

    }
    @Override
    public List<AdjustmentVoucher> populateSupervisorList(){
        caculateAmount(list);
        return  supervisorlist;
    }

    @Override
    public List<AdjustmentVoucher> populateManagerListFromWcf() {
        ArrayList<AdjustmentVoucher> list = new ArrayList<AdjustmentVoucher>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",baseurl+"manager"));

        for (int i = 0; i<arr.length();i++){
            try {
                JSONObject jo = arr.getJSONObject(i);
                AdjustmentVoucher ad = new AdjustmentVoucher();
                ad.setVoucher_id(jo.getString("Adj_id"));
                ad.setStatus(jo.getString("Status"));
                ad.setDate(jo.getString("Date"));

                list.add(ad);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<AdjustmentVoucher> populateSupervisorListFromWcf() {
        ArrayList<AdjustmentVoucher> list = new ArrayList<AdjustmentVoucher>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",baseurl+"supervisor"));

        for (int i = 0; i<arr.length();i++){
            try {
                JSONObject jo = arr.getJSONObject(i);
                AdjustmentVoucher ad = new AdjustmentVoucher();
                ad.setVoucher_id(jo.getString("Adj_id"));
                ad.setStatus(jo.getString("Status"));
                ad.setDate(jo.getString("Date"));

                list.add(ad);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    @Override
    public List<AdjustmentVoucherDetail> populateAdjustmentDetailFromWcf(String url){
        List<AdjustmentVoucherDetail> list = new ArrayList<AdjustmentVoucherDetail>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",url));
        System.out.println(arr);

        try {
            for (int i =0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);
                AdjustmentVoucherDetail avd = new AdjustmentVoucherDetail();
               // avd.setCategory(obj.getString("category_name").toStr
               // ing());

                avd.setItemName(obj.getString("item_description"));
                avd.setQty(obj.getInt("stock_adjustment_qty"));
                System.out.println(avd.getQty());
                //avd.setPrice(Double.parseDouble(obj.getString("stock_adjustment_price")));
                //avd.setReason(obj.getString("stock_adjustment_reason").toString());
                list.add(avd);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);
    }

}

