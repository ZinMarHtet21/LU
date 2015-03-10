package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;

/**
 * Created by student on 10/3/15.
 */
public class AdjustmentVoucherDetail implements Serializable {
    private String id;
    //private String voucher_id;
    private String itemName;
    private int qty;
    private double price;

    public AdjustmentVoucherDetail(){}

    public AdjustmentVoucherDetail(String id, String itemName, int qty, double price) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AdjustmentVoucherDetail{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
