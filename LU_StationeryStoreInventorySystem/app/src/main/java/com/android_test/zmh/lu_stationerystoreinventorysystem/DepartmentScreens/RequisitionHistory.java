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
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.List;


public class RequisitionHistory extends Activity {

    RequisitionPopulator reqPopulator = new RequisitionPopulator();
    private ArrayList<Requisition> reqList;
    private String url = UrlManager.APIROOTURL;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history);
        list = (ListView) findViewById(R.id.ListView_Req);

        new AsyncTask<Void, Void, List<Requisition>>() {
            @Override
            protected List<Requisition> doInBackground(Void... params) {
                return reqPopulator.getRequisitionHistoryList(MainActivity.emp.getId());

            }
            @Override
            protected void onPostExecute(List<Requisition> result) {

                Myadapter myadapter = new Myadapter(RequisitionHistory.this,result);
                list.setAdapter(myadapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(RequisitionHistory.this,RequisitionHistoryDetail.class);
//                      i.putExtra("Requisition_id",reqList.get(position).getId());
                        String req =  ((Requisition) parent.getAdapter().getItem(position)).getId();

                        i.putExtra("req_id",req);
                        //i.putExtra("Requisition_id",reqList.get(position).getId());
                        startActivity(i);
                    }
                });

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

            View v = inflater.inflate(R.layout.row_requisition_history, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.req_id);
            TextView tv2 = (TextView) v.findViewById(R.id.req_date);
            TextView tv3 = (TextView) v.findViewById(R.id.req_status);

            tv1.setText(list.get(position).getId());
            tv2.setText(list.get(position).getDate());
            tv3.setText(list.get(position).getStatus());

            return v;
        }

    }

}
