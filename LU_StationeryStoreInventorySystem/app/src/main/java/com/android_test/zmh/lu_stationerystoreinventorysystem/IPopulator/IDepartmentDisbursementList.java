package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;


import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 8/3/15.
 */
public interface IDepartmentDisbursementList {
    public ArrayList<DepartmentDisbursementList> populateDepartmentDisbursementList(JSONArray ja);
}
