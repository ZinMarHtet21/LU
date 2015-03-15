package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.util.List;

/**
 * Created by student on 15/3/15.
 */
public class NewRequisitionObject {

    private int empID;
    private List<NewRequisitionDetail> req_detail_list;

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public List<NewRequisitionDetail> getReq_detail_list() {
        return req_detail_list;
    }

    public void setReq_detail_list(List<NewRequisitionDetail> req_detail_list) {
        this.req_detail_list = req_detail_list;
    }
}
