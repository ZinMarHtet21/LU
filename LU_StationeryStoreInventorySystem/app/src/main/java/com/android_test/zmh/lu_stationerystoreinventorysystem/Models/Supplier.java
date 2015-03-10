package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 4/3/15.
 */
public class Supplier {
    private String id;
    private String name;
    private String contactName;
    private String phone;
    private String fax;
    private String address;
    private String gstRegistrationNumber;

    public Supplier() {
    }

    public Supplier(String id, String name, String contactName, String phone, String fax, String address, String gstRegistrationNumber) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public String getFax(){
        return fax;
    }

    public void setFax(String fax){
        this.fax = fax;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getGstRegistrationNumber(){
        return gstRegistrationNumber;
    }

    public void setGstRegistrationNumber(String gstRegistrationNumber){
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", address='" + address + '\'' +
                ", gstRegistrationNumber='" + gstRegistrationNumber + '\'' +
                '}';
    }
}
