package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Department;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by student on 8/3/15.
 */
public class DepartmentDisbursementListPopulator implements IDepartmentDisbursementList {
    @Override
    public ArrayList<DepartmentDisbursementList> populateDepartmentDisbursementList(JSONArray ja) {
        ArrayList <DepartmentDisbursementList> list = new ArrayList<DepartmentDisbursementList>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                DepartmentDisbursementList ddl = new DepartmentDisbursementList();
                //department info
                Department department = new Department();
                department.setCode(jo.getString("dept"));
                department.setRepresentative(jo.getString("rep"));
                department.setCollection_point(jo.getString("CollectionPoint"));
                ddl.setDepartment(department);
                ArrayList<Disbursement> disbursements = new ArrayList<Disbursement>();
                ddl.setDisbursementList(disbursements);
                list.add(ddl);
                } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public ArrayList<Disbursement> populateDisbursementForADepartment(JSONArray ja) throws JSONException {
        ArrayList<Disbursement> ad = new ArrayList<Disbursement>();
        for (int i =0;i<ja.length();i++){
            JSONObject o = ja.getJSONObject(i);
            Disbursement d = new Disbursement();
            d.setQty(o.getInt("item_qty"));
            d.setActualQty(o.getInt("item_actual"));
            d.setItemCode(o.getString("item_code"));
            d.setItemName(o.getString("item_desc"));
            ad.add(d);;
        }
        return ad;
    }
}
