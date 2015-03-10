package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.*;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.HashMap;


public class PurchaseOrderDetail extends ActionBarActivity {

   com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order_detail);


        if (getIntent()!= null) {
            model = (com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder) getIntent().getSerializableExtra("PurchaseOrders");
        }

//convert model to hash map
        ListView lv = (ListView) findViewById(R.id.lv_OrderDetail);
        SimpleAdapter mysimpleAdapter = new SimpleAdapter(this, convertModelToHashMapModel(model),
                R.layout.row_orderdetail,
                new String[]{"itemName", "qty", "price"},
                new int[]{R.id.itemName,R.id.qty,R.id.price});
        lv.setAdapter(mysimpleAdapter);

        TextView tv1 = (TextView) findViewById(R.id.order_date);
        TextView tv2 = (TextView) findViewById(R.id.supplierName);
        TextView tv3 = (TextView) findViewById(R.id.status);
        tv1.setText(model.getDate().toString());
        tv2.setText(model.getSupplierName());
        tv3.setText(model.getStatus());
    }

    public ArrayList<hash> convertModelToHashMapModel(com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail pd : model.getPurchaseOrderDetail()) {
            String qty = ""+pd.getQty();
            String price = ""+pd.getPrice();
            hashlist.add(new hash(pd.getItemName(),qty,price));
        }
        return hashlist;
    }


    public  class hash extends HashMap<String,String> {
        public hash(String item,String qty,String price) {
            put("itemName", item);
            put("qty", qty);
            put("price",price);
        }
    }
}
