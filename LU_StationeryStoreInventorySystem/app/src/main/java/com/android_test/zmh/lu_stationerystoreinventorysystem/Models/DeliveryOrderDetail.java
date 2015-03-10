package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.util.Date;

/**
 * Created by student on 5/3/15.
 */
public class DeliveryOrderDetail {
    private String id;
    private Date date;
    private String status;
    private String remark;

    public DeliveryOrderDetail() {
    }

    public DeliveryOrderDetail(String id, Date date, String status, String remark) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.remark = remark;
    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DeliveryOrderDetail{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
