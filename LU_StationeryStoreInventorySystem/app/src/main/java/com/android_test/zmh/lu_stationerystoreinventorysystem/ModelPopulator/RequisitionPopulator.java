package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class RequisitionPopulator implements IRequisition {
    @Override
    public List<Requisition> populateRequisition() {
        List<Requisition> req_list = new LinkedList<Requisition>();
        List<RequisitionDetail> reqDetails_list = new ArrayList<RequisitionDetail>();

        // new Requisition
        Requisition newReq = new Requisition("Req01","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));
        Requisition newReq2 = new Requisition("Req02","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));
        Requisition newReq3 = new Requisition("Req03","SomeONe",(new Date(System.currentTimeMillis())),"SomeDepartMent","Approved","New Remark","HOD",(new Date(System.currentTimeMillis())));

        // some details....
        RequisitionDetail details1 = new RequisitionDetail("Req01","Item01",10,5);
        RequisitionDetail details2 = new RequisitionDetail("Req01","Item02",10,5);
        RequisitionDetail details3 = new RequisitionDetail("Req01","Item03",10,5);

        // Create Details List
        // Map details into Requisition
        reqDetails_list.add(details1);
        reqDetails_list.add(details2);
        reqDetails_list.add(details3);

        // set the reqdetails into the a single Req.
         newReq.setRequisitionDetails(reqDetails_list);
        newReq2.setRequisitionDetails(reqDetails_list);
        newReq3.setRequisitionDetails(reqDetails_list);

        // save the req into the list of req,,
        req_list.add(newReq);
        return req_list;
    }
}
