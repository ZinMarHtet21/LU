package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItemList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IDisbursement {
//    public DisbursementItemList<Disbursement> populateDisbursement();
    public List<DisbursementItem> getDisbursementList(int departmentID);

   public String receiveDisbursementList(int deptID, String remark, List<DisbursementItemList> disbList);
 //  public DisbursementItemList<RequisitionDetail> getDisbursementListDetails(String req_id, String url);
//public JSONObject receiveDisbursementList(int deptID, String remark, List<DisbursementItemList> disbList);
}
