package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;

import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IPurchaseOrder {
    List<PurchaseOrder> PopulatePurchaseOrder();
    List<PurchaseOrder>PopulatePurchaseOrderFromWcf();
}
