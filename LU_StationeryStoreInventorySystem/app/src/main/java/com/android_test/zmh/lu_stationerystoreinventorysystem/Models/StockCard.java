package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.util.Date;

/**
 * Created by student on 4/3/15.
 */
public class StockCard {
    private String id;
    private String itemName;
    private String supplierName;
    private Date date;
    private int qty;
    private int balance;

    public StockCard(){}

    public StockCard(String id, String itemName, String supplierName, Date date, int qty, int balance) {
        this.id = id;
        this.itemName = itemName;
        this.supplierName = supplierName;
        this.date = date;
        this.qty = qty;
        this.balance = balance;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "StockCard{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", date=" + date +
                ", qty=" + qty +
                ", balance=" + balance +
                '}';
    }

}
