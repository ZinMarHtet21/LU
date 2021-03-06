package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucherDetail;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IAdjustmentVoucher {
    List<AdjustmentVoucher> populateAdjustmentVoucher();
    public void caculateAmount(List<AdjustmentVoucher> list);
    List<AdjustmentVoucher> populateManagerList();
    List<AdjustmentVoucher> populateSupervisorList();
    List<AdjustmentVoucher> populateManagerListFromWcf();
    List<AdjustmentVoucher> populateSupervisorListFromWcf();
    public List<AdjustmentVoucher> populateAllListFromWcf();
    List<AdjustmentVoucherDetail>populateAdjustmentDetailFromWcf(String url);

}
