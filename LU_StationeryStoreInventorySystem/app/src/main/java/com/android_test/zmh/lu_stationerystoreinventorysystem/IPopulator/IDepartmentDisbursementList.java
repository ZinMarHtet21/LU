package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;


import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 8/3/15.
 */
public interface IDepartmentDisbursementList {
    public ArrayList<DepartmentDisbursementList> populateDepartmentDisbursementList(JSONArray ja);

    public ArrayList<Disbursement> populateDisbursementForADepartment(JSONArray ja) throws JSONException;

}
