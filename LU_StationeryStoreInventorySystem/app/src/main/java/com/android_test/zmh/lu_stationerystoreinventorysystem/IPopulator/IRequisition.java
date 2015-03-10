package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IRequisition {
    List<Requisition> populateRequisition();

    public ArrayList<Requisition> populateRequisitionList(JSONArray ja)throws JSONException;    ;
    public List<Requisition> getRequisitionList();

}
