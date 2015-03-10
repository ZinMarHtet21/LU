package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiawenHuang on 6/3/15.
 */

public class RetrivalItem{
    private String description;
    private String qty;

    public RetrivalItem(){}

    public RetrivalItem(String description, String qty) {
        this.description = description;
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RetrivalItem{" +
                "description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}

