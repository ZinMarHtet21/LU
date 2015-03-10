package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.*;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ApproveRejectPurchaseOrderDetail extends ActionBarActivity {
    PurchaseOrder model;
    double amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_purchase_order_detail);
        if (getIntent()!= null) {
            model = (PurchaseOrder) getIntent().getSerializableExtra("PurchaseOrder");
        }


        ListView lv = (ListView) findViewById(R.id.lv_sorder);
        SimpleAdapter mysimpleAdapter = new SimpleAdapter(this, convertModelToHashMapModel(model),
                R.layout.row_orderdetail,
                new String[]{"itemName", "qty", "price"},
                new int[]{R.id.itemName,R.id.qty,R.id.price});
        lv.setAdapter(mysimpleAdapter);

        TextView tv1 = (TextView) findViewById(R.id.poNumber);
        TextView tv2 = (TextView) findViewById(R.id.Amount);
        Button btn1 = (Button) findViewById(R.id.btnApprove);
        Button btn2 = (Button) findViewById(R.id.btnReject);
        tv1.setText(model.getNumber());
        tv2.setText(String.valueOf(amount));


    }

    public ArrayList<hash> convertModelToHashMapModel(com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (PurchaseOrderDetail pd : model.getPurchaseOrderDetail()) {

            amount=amount+pd.getPrice()*pd.getQty();
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
