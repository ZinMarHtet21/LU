package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;


import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 7/3/15.
 */
public interface IItem {

    List<Item> populateItem();

    public ArrayList<Item> populateItemList(JSONArray ja);

    public ArrayList<Item> populatePendingProcessedItem(JSONArray ja);

    public Item populateItemDetail(JSONObject jo) throws JSONException;

}
