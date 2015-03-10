package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 5/3/15.
 */
public class PurchaseOrderDetail {
    private String id;
    private String itemName;
    private int qty;
    private double price;

    public PurchaseOrderDetail() {
    }

    public PurchaseOrderDetail(String id, String itemName, int qty, double price) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
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

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
