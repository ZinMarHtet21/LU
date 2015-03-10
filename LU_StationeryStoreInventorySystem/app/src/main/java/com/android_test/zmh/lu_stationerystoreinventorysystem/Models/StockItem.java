package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiawenHuang on 5/3/15.
 */
public class StockItem{
    private String description;
    private String reorder_level;
    private String balance;

    public StockItem(){}

    public StockItem(String description, String reorder_level, String balance) {
        this.description = description;
        this.reorder_level = reorder_level;
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReorder_level() {
        return reorder_level;
    }

    public void setReorder_level(String reorder_level) {
        this.reorder_level = reorder_level;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "description='" + description + '\'' +
                ", reorder_level='" + reorder_level + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}


