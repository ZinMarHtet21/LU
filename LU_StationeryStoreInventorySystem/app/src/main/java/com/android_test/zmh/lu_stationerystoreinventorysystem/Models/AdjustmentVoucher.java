package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 10/3/15.
 */
public class AdjustmentVoucher implements Serializable{

    private String id;
    private String voucher_id;
    private String date;
    private String status;
    private String reason;
    private List<AdjustmentVoucherDetail> adjustmentVoucherDetails=new ArrayList<AdjustmentVoucherDetail>();

    public AdjustmentVoucher(){}

    public AdjustmentVoucher(String id, String voucher_id, String date, String status, String reason) {
        this.id = id;
        this.voucher_id = voucher_id;
        this.date = date;
        this.status = status;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<AdjustmentVoucherDetail> getAdjustmentVoucherDetails() {
        return adjustmentVoucherDetails;
    }

    public void setAdjustmentVoucherDetails(List<AdjustmentVoucherDetail> adjustmentVoucherDetails) {
        this.adjustmentVoucherDetails = adjustmentVoucherDetails;
    }

    @Override
    public String toString() {
        return "AdjustmentVoucher{" +
                "id='" + id + '\'' +
                ", voucher_id='" + voucher_id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", adjustmentVoucherDetails=" + adjustmentVoucherDetails +
                '}';
    }
}
