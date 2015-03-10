package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 4/3/15.
 */

public class Department implements Serializable {

    private String id;
    private String code;
    private String name;
    private String contactNumber;
    private String fax;
    private String representative;
    private String collection_point;

    public Department(){}

    public Department(String id, String code, String name, String contactNumber, String fax, String representative, String collection_point) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.contactNumber = contactNumber;
        this.fax = fax;
        this.representative = representative;
        this.collection_point = collection_point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getCollection_point() {
        return collection_point;
    }

    public void setCollection_point(String collection_point) {
        this.collection_point = collection_point;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", fax='" + fax + '\'' +
                ", representative='" + representative + '\'' +
                ", collection_point='" + collection_point + '\'' +
                '}';
    }
}
