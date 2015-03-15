package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;


import java.io.Serializable;

/**
 * Created by student on 5/3/15.
 */
public class RequisitionDetail implements Serializable{

    private int id;
    private String item_id;
    private String itemName;
    private int item_detail_qty;

    public RequisitionDetail() {
    }

    public RequisitionDetail(int id, String item_id, String itemName, int item_detail_qty) {
        this.id = id;
        this.item_id = item_id;
        this.itemName = itemName;
        this.item_detail_qty = item_detail_qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItem_detail_qty() {
        return item_detail_qty;
    }

    public void setItem_detail_qty(int item_detail_qty) {
        this.item_detail_qty = item_detail_qty;
    }

    @Override
    public String toString() {
        return "{ item_id='" + item_id + '\'' +
                ", item_detail_qty=" + item_detail_qty +
                '}';
    }
}
