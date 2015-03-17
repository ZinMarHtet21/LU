package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;

import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IDisbursement {
//    public List<Disbursement> populateDisbursement();
    public List<DisbursementItem> getDisbursementList(int departmentID);

    public String receiveDisbursementList(List<DisbursementItem> disbList);
//    public List<RequisitionDetail> getDisbursementListDetails(String req_id, String url);
}
