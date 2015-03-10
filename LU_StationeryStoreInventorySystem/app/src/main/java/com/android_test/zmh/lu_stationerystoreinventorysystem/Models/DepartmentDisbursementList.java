package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by student on 8/3/15.
 */
public class DepartmentDisbursementList implements Serializable {
    Department department;
    ArrayList<Disbursement> disbursementList;

    public DepartmentDisbursementList(){}

    public DepartmentDisbursementList(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Disbursement> getDisbursementList() {
        return disbursementList;
    }

    public void setDisbursementList(ArrayList<Disbursement> disbursementList) {
        this.disbursementList = disbursementList;
    }

    @Override
    public String toString() {
        return "DepartmentDisbursementList{" +
                "department=" + department +
                ", disbursementList=" + disbursementList +
                '}';
    }
}
