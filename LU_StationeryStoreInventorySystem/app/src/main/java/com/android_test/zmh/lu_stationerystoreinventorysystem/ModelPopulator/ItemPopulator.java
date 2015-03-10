package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;


import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;

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

    @Override
    public List<Item> populateItem() {

        List<Item> item_list = new LinkedList<Item>();

        Item item = new Item("Item01", "File", "Transparent File", 30, 50, 10, 5, "Full", "Piece");
        Item item2 = new Item("Item02", "Envelope", "Envelope 10\"x7\" ", 50, 70, 20, 5, "Low Stock", "Piece");
        Item item3 = new Item("Item03", "Eraser", "Black Eraser", 30, 50, 10, 5, "Full", "Piece");

        item_list.add(item);
        item_list.add(item2);
        item_list.add(item3);

        return item_list;
    }

    @Override
    public ArrayList<Item> populateItemList(JSONArray ja) {

        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                Item item = new Item();
                item.setCategory(jo.getString("id"));
                item.setDescription(jo.getString("id"));
                item.setReorderLevel(jo.getInt("server"));
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
        i.setCategory(jo.getString("id"));
        i.setDescription(jo.getString("id"));
        i.setReorderLevel(jo.getInt("server"));
        i.setBalance(jo.getInt("farm"));
        return i;
    }
}
