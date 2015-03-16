package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 15/3/15.
 */
public class RetrivalItemDetail {
   private String retrivalDetailitemID;
   private String DepartmentName;
   private int neededQty;
   private int actualQty;


    public String getRetrivalDetailitemID() {
        return retrivalDetailitemID;
    }

    public void setRetrivalDetailitemID(String retrivalDetailitemID) {
        this.retrivalDetailitemID = retrivalDetailitemID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public int getNeededQty() {
        return neededQty;
    }

    public void setNeededQty(int neededQty) {
        this.neededQty = neededQty;
    }

    public int getActualQty() {
        return actualQty;
    }

    public void setActualQty(int actualQty) {
        this.actualQty = actualQty;
    }
}
