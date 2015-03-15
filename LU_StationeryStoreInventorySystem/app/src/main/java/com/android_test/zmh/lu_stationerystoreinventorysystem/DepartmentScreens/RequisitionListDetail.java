package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequisitionListDetail extends ActionBarActivity {

    RequisitionPopulator pop = new RequisitionPopulator();
    String baseurl;

    String req_id;
    Requisition model;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_list_detail);
        lv = (ListView) findViewById(R.id.listView_ReqDetails);
        model = new Requisition();

        if(getIntent()!= null) {
            req_id = getIntent().getStringExtra("req_id").toString();
            baseurl = UrlManager.APIROOTURL + "requisition_detailApi/new/";
            //    ("Requisition",listdata.get(position));
        }



        //model.setRequisitionDetails();
        // creat a new async task to pull all the requisition details..
        new AsyncTask<Void, Void, List<RequisitionDetail>>() {
            @Override
            protected List<RequisitionDetail> doInBackground(Void... params) {

                System.out.println("REQID");
                System.out.println(req_id);

                List<RequisitionDetail> listdetail = pop.getRequisitionDetail(req_id, baseurl);
                model.setRequisitionDetails(listdetail);
                return listdetail;

            }
            @Override
            protected void onPostExecute(List<RequisitionDetail> result) {
                System.out.println("REQUISITION DETAIL");
                System.out.println(result.toString());
                SimpleAdapter mysimpleAdapter = new SimpleAdapter(RequisitionListDetail.this,
                        convertModelToHashMapModel(model),
                        android.R.layout.simple_list_item_2,
                        new String[]{"itemName","qty"} ,
                        new int[]{ android.R.id.text1,android.R.id.text2});
                lv.setAdapter(mysimpleAdapter);
            }
        }.execute();
    }

    public ArrayList<temp> convertModelToHashMapModel(Requisition model) {
        ArrayList<temp> tempList = new ArrayList<temp>();
        for(RequisitionDetail rd : model.getRequisitionDetails()) {
            String qty = "" + rd.getItem_detail_qty();
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
