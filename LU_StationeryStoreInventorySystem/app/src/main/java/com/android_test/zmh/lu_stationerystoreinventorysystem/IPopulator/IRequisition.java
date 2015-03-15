package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IRequisition {

    public List<Requisition> getRequisitionHistoryList(int empID);
//    public List<RequisitionDetail> getRequisitionHistoryDetails(String req_id, String url);

    public List<Requisition> getRequisitionList(int deptID);
//    public List<RequisitionDetail> getRequisitionListDetails(String req_id, String url);

    public List<RequisitionDetail> getRequisitionDetail(String reqID,String url);

    public String sendNewRequisition(int empID,ArrayList<RequisitionDetail> reqDetails);
}
