package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiawenHuang on 6/3/15.
 */

public class RetrivalItem implements Serializable {
    private String item_code;
    private String item_qty;
    private String  item_desc;
    private String  item_actual;

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getItem_actual() {
        return item_actual;
    }

    public void setItem_actual(String item_actual) {
        this.item_actual = item_actual;
    }
}

