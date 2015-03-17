package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 17/3/15.
 */
public class DisbursementItem {
    private String item_code;
    private String item_desc;
    private int item_qty;
    private int item_actual;

    public DisbursementItem() {
    }

    public DisbursementItem(String item_code, String item_desc, int item_qty, int item_actual) {
        this.item_code = item_code;
        this.item_desc = item_desc;
        this.item_qty = item_qty;
        this.item_actual = item_actual;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public int getItem_actual() {
        return item_actual;
    }

    public void setItem_actual(int item_actual) {
        this.item_actual = item_actual;
    }

    @Override
    public String toString() {
        return "DisbursementItem{" +
                "item_code='" + item_code + '\'' +
                ", item_desc='" + item_desc + '\'' +
                ", item_qty=" + item_qty +
                ", item_actual=" + item_actual +
                '}';
    }
}
