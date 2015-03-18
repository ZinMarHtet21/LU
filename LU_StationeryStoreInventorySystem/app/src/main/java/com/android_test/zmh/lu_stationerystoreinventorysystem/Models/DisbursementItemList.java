package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 17/3/15.
 */
public class DisbursementItemList {

    private String itemCode;
    private int lblAcutal;

    public DisbursementItemList() {
    }

    public DisbursementItemList(String itemCode, int lblAcutal) {
        this.itemCode = itemCode;
        this.lblAcutal = lblAcutal;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getLblAcutal() {
        return lblAcutal;
    }

    public void setLblAcutal(int lblAcutal) {
        this.lblAcutal = lblAcutal;
    }

//    @Override
//    public String toString() {
//        return "{itemCode='" + itemCode + '\'' +
//                ", lblAcutal='" + lblAcutal + '\'' +
//                '}';
//    }
}
