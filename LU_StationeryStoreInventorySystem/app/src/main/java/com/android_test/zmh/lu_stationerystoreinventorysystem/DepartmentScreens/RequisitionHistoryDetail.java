package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class RequisitionHistoryDetail extends ActionBarActivity {

    RequisitionPopulator pop = new RequisitionPopulator();
    String baseurl = UrlManager.APIROOTURL + "requisition_detailApi/new";

    String req_id;
    Requisition model;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history_detail);
        lv = (ListView) findViewById(R.id.listView_ReqDetails);
        //model = new Requisition();

        if (getIntent() != null) {
            req_id = getIntent().getStringExtra("req_id").toString();
            //    ("Requisition",listdata.get(position));
        }

        //model.setRequisitionDetails();
        // creat a new async task to pull all the requisition details..
        new AsyncTask<Void, Void, List<RequisitionDetail>>() {
            @Override
            protected List<RequisitionDetail> doInBackground(Void... params) {

                List<RequisitionDetail> listdetail = pop.getRequisitionDetail(req_id, baseurl);
                //model.setRequisitionDetails(listdetail);
                return listdetail;

            }

            @Override
            protected void onPostExecute(List<RequisitionDetail> result) {

                Myadapter myadapter = new Myadapter(RequisitionHistoryDetail.this,result);
                lv.setAdapter(myadapter);

//                SimpleAdapter mysimpleAdapter = new SimpleAdapter(RequisitionHistoryDetail.this,
//                        convertModelToHashMapModel(model),
//                        android.R.layout.simple_list_item_2,
//                        new String[]{"itemName", "qty"},
//                        new int[]{android.R.id.text1, android.R.id.text2});
//                lv.setAdapter(mysimpleAdapter);
            }
        }.execute();
    }

    public class Myadapter extends BaseAdapter {

        // starts here...
        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
        Context ctx;
        LayoutInflater inflater;

        public Myadapter(Context contxt, List<RequisitionDetail> listData) {
            ctx = contxt;
            list = listData;
            inflater = LayoutInflater.from(this.ctx);
        }
        // ends here...

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = inflater.inflate(R.layout.row_items, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.itemName_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.itemQty_et);
//            TextView tv3 = (TextView) v.findViewById(R.id.req_status);

            tv1.setText(list.get(position).getItemName());
            tv2.setText(Integer.toString(list.get(position).getItem_detail_qty()));

            return v;
        }
    }
}

//    public ArrayList<temp> convertModelToHashMapModel(Requisition model) {
//        ArrayList<temp> tempList = new ArrayList<temp>();
//        for(RequisitionDetail rd : model.getRequisitionDetails()) {
//            String qty = ""+rd.getItem_detail_qty();
//            tempList.add(new temp(rd.getItemName(),qty));
//        }
//        return  tempList;
//    }
//
//    public  class temp extends HashMap<String,String>{
//        public temp(String item,String qty) {
//            put("itemName", item);
//            put("qty", qty);
//        }
//    }
//}
