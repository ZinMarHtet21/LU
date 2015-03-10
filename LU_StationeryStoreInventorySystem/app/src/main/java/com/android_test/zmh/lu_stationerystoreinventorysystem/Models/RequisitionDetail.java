package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;

/**
 * Created by student on 5/3/15.
 */
public class RequisitionDetail implements Serializable{
    private String id;
    private String itemName;
    private int qty;
    private int actualQty;

    public RequisitionDetail() {
    }

    public RequisitionDetail(String id, String itemName, int qty, int actualQty) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.actualQty = actualQty;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getActualQty() {
        return actualQty;
    }

    public void setActualQty(int actualQty) {
        this.actualQty = actualQty;
    }

    @Override
    public String toString() {
        return "RequisitionDetail{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", actualQty=" + actualQty +
                '}';
    }
}
