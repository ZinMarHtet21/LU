package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequisitionHistoryDetail extends ActionBarActivity {
    Requisition model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history_detail);

        if(getIntent()!= null) {
             model = (Requisition) getIntent().getSerializableExtra("Requisition");
            //    ("Requisition",listdata.get(position));
        }

        // model to hashmap >>
       // convertModelToHashMapModel(model)

        ListView lv = (ListView) findViewById(R.id.listView_ReqDetails);
        SimpleAdapter mysimpleAdapter = new SimpleAdapter(this,convertModelToHashMapModel(model),android.R.layout.simple_list_item_2,new String[]{"itemName","qty"} ,new int[]{ android.R.id.text1,android.R.id.text2});
        lv.setAdapter(mysimpleAdapter);
        //create an adapter for listview
        //set the adapter with the respective model  (Requisition details)

        TextView tv = (TextView) findViewById(R.id.textView_Date);
        tv.setText(model.getProcessDate().toString());

    }

    public ArrayList<temp> convertModelToHashMapModel(Requisition model) {
        ArrayList<temp> tempList = new ArrayList<temp>();
        for(RequisitionDetail rd : model.getRequisitionDetails()) {

            String qty = ""+rd.getQty();
            tempList.add(new temp(rd.getItemName(),qty));

        }

        return  tempList;
    }

    public  class temp extends HashMap<String,String>{
        public temp(String item,String qty) {
            put("itemName", item);
            put("qty", qty);
        }
    }

}
