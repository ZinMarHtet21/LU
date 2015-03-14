package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IPurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
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
public class PurchaseOrderPopulator  implements IPurchaseOrder {
    public PurchaseOrderPopulator() {
    }

    public final static String baseurl = UrlManager.APIROOTURL+"purchase_orderApi/";
    //public final static String orderlistURL = baseurl +"purchase_orderApi/";
    //public final static String pendingListURL = baseurl + "purchase_orderApi/Pending/";
    //public final static String DetailURL = baseurl + "purchase_orderApi/detail/";
    @Override
    public List<PurchaseOrder> PopulatePurchaseOrder() {
        List<PurchaseOrder> PO_list = new LinkedList<PurchaseOrder>();
        List<PurchaseOrderDetail> PODetails_list = new ArrayList<PurchaseOrderDetail>();


        PurchaseOrder newPO = new PurchaseOrder("1","20000069","2014-09-06","CHEP","Approved");


        PurchaseOrderDetail details1 = new PurchaseOrderDetail("p1","1","Clips Double 2",10,5.0);
        PurchaseOrderDetail details2 = new PurchaseOrderDetail("p1","1","Pad",45,3.4);
        PurchaseOrderDetail details3 = new PurchaseOrderDetail("p1","1","papper",6,20);


        PODetails_list.add(details1);
        PODetails_list.add(details2);
        PODetails_list.add(details3);


       // newPO.setPurchaseOrderDetail(PODetails_list);


        PO_list.add(newPO);
        return PO_list;
    }


        @Override
        public List<PurchaseOrder> PopulatePurchaseOrderListFromWcf() {

            ArrayList<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
            JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",baseurl));
            for (int i = 0; i<arr.length();i++){
                try {
                    JSONObject jo = arr.getJSONObject(i);
                    PurchaseOrder order = new PurchaseOrder();
                    order.setId(jo.getString("purchase_order_id"));
                    order.setNumber(jo.getString("purchase_order_number"));
                    order.setDate(jo.getString("purchase_order_date"));
                    order.setSupplierName(jo.getString("supplier_supplier_id"));

                    order.setStatus(jo.getString("purchase_order_status"));


                    list.add(order);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return list;
    }
    @Override
    public List<PurchaseOrder> PopulatePendingPurchaseOrderList() {

        ArrayList<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",baseurl+"Pending"));
        for (int i = 0; i<arr.length();i++){
            try {
                JSONObject jo = arr.getJSONObject(i);
                PurchaseOrder order = new PurchaseOrder();
                order.setId(jo.getString("purchase_order_id"));
                order.setNumber(jo.getString("purchase_order_number"));
                order.setStatus(jo.getString("purchase_order_status"));
                order.setDate(jo.getString("purchase_order_date"));
                order.setSupplierName(jo.getString("supplier_supplier_id"));
                list.add(order);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    @Override
    public List<PurchaseOrderDetail> PopulatePurchaseOrderDetailFromWcf(String url){
        List<PurchaseOrderDetail> list = new ArrayList<PurchaseOrderDetail>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",url));
        System.out.println(arr);

        try {
            for (int i =0; i < arr.length(); i++) {


                JSONObject obj = arr.getJSONObject(i);
                PurchaseOrderDetail po = new PurchaseOrderDetail();
                po.setPurchase_id(obj.getString("purchase_order_purchase_order_id"));
                po.setItemName(obj.getString("item_description").toString());
                po.setQty(Integer.parseInt(obj.getString("purchase_order_detail_qty")));
                po.setPrice(Double.parseDouble(obj.getString("item_supplier_list_price")));
                list.add(po);
            }

        } catch (Exception e) {
            Log.e("list", "JSONArray error");
        }

        return(list);
    }


}
