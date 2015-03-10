package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by student on 7/3/15.
 */
public class DisbursementDepartment {

    private String id;
    private String name;
    private String representative;
    private String collection_point;

    public DisbursementDepartment(){}

    public DisbursementDepartment(String id, String name, String representative, String collection_point) {
        this.id = id;
        this.name = name;
        this.representative = representative;
        this.collection_point = collection_point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DisbursementDepartment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", representative='" + representative + '\'' +
                ", collection_point='" + collection_point + '\'' +
                '}';
    }
}
