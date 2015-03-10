package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;



import java.util.Date;
import java.util.List;

/**
 * Created by student on 4/3/15.
 */
public class PurchaseOrder {
    private String id;
    private String number;
    private Date date;
    private String supplierName;
    private String orderBy;
    private String approvedBy;
    private String status;
    private List<PurchaseOrderDetail> purchaseOrderDetail;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String id, String number, Date date, String supplierName, String orderBy, String approvedBy, String status) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.supplierName = supplierName;
        this.orderBy = orderBy;
        this.approvedBy = approvedBy;
        this.status = status;
    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getSupplierName(){
        return supplierName;
    }

    public void setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }

    public String getOrderBy(){
        return orderBy;
    }

    public void setOrderBy(String orderBy){
        this.orderBy = orderBy;
    }

    public String getApprovedBy(){
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy){
        this.approvedBy = approvedBy;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public List<PurchaseOrderDetail> getDeliveryOrderDetail(){
        return purchaseOrderDetail;
    }

    public void setDeliveryOrderDetail(List<PurchaseOrderDetail> purchaseOrderDetail){
        this.purchaseOrderDetail = purchaseOrderDetail;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", supplierName='" + supplierName + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", status='" + status + '\'' +
                ", purchaseOrderDetail=" + purchaseOrderDetail +
                '}';
    }
}
