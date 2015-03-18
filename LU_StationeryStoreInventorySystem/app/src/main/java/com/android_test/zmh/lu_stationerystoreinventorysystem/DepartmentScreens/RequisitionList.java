package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DepartmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.List;


public class RequisitionList extends Activity {

    RequisitionPopulator reqPopulator = new RequisitionPopulator();
    private ArrayList<Requisition> reqList;
    private String url = UrlManager.APIROOTURL;
    ListView reqlist_lv;
    Myadapter myadapter;
//    @Override
//    protected void onStart() {
//        super.onStart();
//        finish();
//        startActivity(getIntent());
//    }


    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, List<Requisition>>() {
            @Override
            protected List<Requisition> doInBackground(Void... params) {
                return reqPopulator.getRequisitionList(MainActivity.emp.getDepartmentID());
            }
            @Override
            protected void onPostExecute(List<Requisition> result) {
                reqlist_lv = (ListView) findViewById(R.id.req_list_lv);

                 myadapter = new Myadapter(RequisitionList.this, result);
                reqlist_lv.setAdapter(myadapter);

                reqlist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(RequisitionList.this,RequisitionListDetail.class);
                        String req =  ((Requisition) parent.getAdapter().getItem(position)).getId();
                        String req_date = ((Requisition)parent.getAdapter().getItem(position)).getDate();
                        i.putExtra("req_date", req_date);
                        i.putExtra("req_id",req);
                        startActivity(i);
                    }
                });
            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_list);
        reqlist_lv = (ListView) findViewById(R.id.req_list_lv);

        new AsyncTask<Void, Void, List<Requisition>>() {
            @Override
            protected List<Requisition> doInBackground(Void... params) {
                return reqPopulator.getRequisitionList(MainActivity.emp.getDepartmentID());
            }
            @Override
            protected void onPostExecute(List<Requisition> result) {

                 myadapter = new Myadapter(RequisitionList.this, result);
                reqlist_lv.setAdapter(myadapter);

//                reqlist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent i = new Intent(RequisitionList.this,RequisitionListDetail.class);
//                        String req =  ((Requisition) parent.getAdapter().getItem(position)).getId();
//                        String req_date = ((Requisition)parent.getAdapter().getItem(position)).getDate();
//                        i.putExtra("req_date", req_date);
//                        i.putExtra("req_id",req);
//                        startActivity(i);
//                    }
//                });
            }
        }.execute();
    }

    public class Myadapter extends BaseAdapter {

        // starts here...
        List<Requisition> list = new ArrayList<Requisition>();
        Context ctx;
        LayoutInflater inflater;

        public Myadapter(Context contxt, List<Requisition> listData) {
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

            View v = inflater.inflate(R.layout.row_requisition_list, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.req_id_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.req_date_txt);

            tv1.setText(list.get(position).getId());
            tv2.setText(list.get(position).getDate());

            return v;
        }

    }

}
