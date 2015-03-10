package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class ItemPopulator implements IItem {
    @Override
    public List<Item> populateItem() {

        List<Item> item_list = new LinkedList<Item>();

        Item item = new Item("Item01","File","Transparent File",30,50,10,"Full","Piece");
        Item item2 = new Item("Item02","Envelope","Envelope 10\"x7\" ",50,70,20,"Low Stock","Piece");
        Item item3 = new Item("Item03","Eraser","Black Eraser",30,50,10,"Full","Piece");

        item_list.add(item);
        item_list.add(item2);
        item_list.add(item3);

        return item_list;
    }
}
