package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;


import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 7/3/15.
 */
public class ItemPopulator implements IItem {

    public final static String baseurl = UrlManager.APIROOTURL;
    public final static String itemURL = baseurl +"itemApi";

    @Override
    public List<Item> getItemList() {
        List<Item> list = new ArrayList<Item>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",itemURL));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Item item = new Item();
                item.setId(obj.getString("item_id").toString());
                item.setCategory(obj.getInt("category_category_id"));
                item.setDescription(obj.getString("item_description").toString());
                item.setReorderLevel(obj.getInt("item_reorder_level"));
                item.setReorderQty(obj.getInt("item_reorder_qty"));
//                item.setBalance(obj.getInt(""));
                item.setVirtualBalance(obj.getInt("item_virtual_balance"));
                item.setStatus(obj.getString("item_status").toString());
                item.setUom(obj.getString("uom").toString());

                list.add(item);
            }

        } catch (Exception e) {
            Log.e("Item list", "JSONArray error");
        }

        return(list);

    }

    @Override
    public ArrayList<Item> populateItemList(JSONArray ja) {
        System.out.print(ja);

        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                Item item = new Item();
                item.setCategory(jo.getInt("category"));
                item.setDescription(jo.getString("item_description"));
                item.setReorderLevel(jo.getInt("item_reorder_level"));
                item.setBalance(jo.getInt("item_virtual_balance"));
                list.add(item);
                // list.add(new (jo.getString("id"),jo.getString("server"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    @Override
    public ArrayList<Item> populatePendingProcessedItem(JSONArray ja) {
        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                Item item = new Item();
                item.setDescription(jo.getString("id"));
                item.setBalance(jo.getInt("server"));
                list.add(item);
                // list.add(new (jo.getString("id"),jo.getString("server"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Item populateItemDetail(JSONObject jo) throws JSONException {
        Item i = new Item();
        i.setCategory(jo.getInt("id"));
        i.setDescription(jo.getString("id"));
        i.setReorderLevel(jo.getInt("server"));
        i.setBalance(jo.getInt("farm"));
        return i;
    }
}
