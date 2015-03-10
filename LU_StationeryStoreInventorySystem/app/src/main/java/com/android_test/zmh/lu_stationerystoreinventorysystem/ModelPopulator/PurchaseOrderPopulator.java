package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IPurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;

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

    @Override
    public List<PurchaseOrder> PopulatePurchaseOrder() {
        List<PurchaseOrder> PO_list = new LinkedList<PurchaseOrder>();
        List<PurchaseOrderDetail> PODetails_list = new ArrayList<PurchaseOrderDetail>();


        PurchaseOrder newPO = new PurchaseOrder("1","20000069",(new Date(System.currentTimeMillis())),"CHEP","Sam","Peter","Approved");


        PurchaseOrderDetail details1 = new PurchaseOrderDetail("p1","Clips Double 2",10,5.0);
        PurchaseOrderDetail details2 = new PurchaseOrderDetail("p1","Pad",45,3.4);
        PurchaseOrderDetail details3 = new PurchaseOrderDetail("p1","papper",6,20);


        PODetails_list.add(details1);
        PODetails_list.add(details2);
        PODetails_list.add(details3);


        newPO.setPurchaseOrderDetail(PODetails_list);


        PO_list.add(newPO);
        return PO_list;
    }
    @Override
    public List<PurchaseOrder> PopulatePurchaseOrderFromWcf() {
        return null;
    }
}
