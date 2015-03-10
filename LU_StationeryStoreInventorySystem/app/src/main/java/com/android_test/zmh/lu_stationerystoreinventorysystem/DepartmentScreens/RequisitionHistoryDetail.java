package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequisitionHistoryDetail extends ActionBarActivity {
    RequisitionPopulator pop = new RequisitionPopulator();
    String baseurl = "http://10.10.1.200/LU_Store_MvcV1/api/requisition_detailApi/new";
    String req_id;
    Requisition model;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history_detail);



        // model to hashmap >>
       // convertModelToHashMapModel(model)

         lv = (ListView) findViewById(R.id.listView_ReqDetails);

        //create an adapter for listview
        //set the adapter with the respective model  (Requisition details)


        model = new Requisition();
        if(getIntent()!= null) {
            req_id = getIntent().getStringExtra("Req_id").toString();
            //    ("Requisition",listdata.get(position));
        }
        //model.setRequisitionDetails();
        // creat a new async task to pull all the requisition details..

        new AsyncTask<Void, Void, List<RequisitionDetail>>() {
            @Override
            protected List<RequisitionDetail> doInBackground(Void... params) {
                Log.i("doInBackground", "ERROR");

                List<RequisitionDetail> listde = pop.getRequisitionDetails(req_id, baseurl);
                model.setRequisitionDetails(listde);
                return  listde;

            }
            @Override
            protected void onPostExecute(List<RequisitionDetail> result) {
              //  Myadapter myadapter = new Myadapter(RequisitionHistoryDetail.this,result);
                SimpleAdapter mysimpleAdapter = new SimpleAdapter(RequisitionHistoryDetail.this,convertModelToHashMapModel(model),android.R.layout.simple_list_item_2,new String[]{"itemName","qty"} ,new int[]{ android.R.id.text1,android.R.id.text2});
                lv.setAdapter(mysimpleAdapter);



            }
        }.execute();




        TextView tv = (TextView) findViewById(R.id.textView_Date);
        //tv.setText(model.getProcessDate().toString());

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
