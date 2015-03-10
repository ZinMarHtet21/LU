package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;


public class RequisitionHistory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history);
        RequisitionPopulator pop = new RequisitionPopulator();
        final List<Requisition> listdata = pop.populateRequisition();


        // create an adapter
        // pass the data.
        //set the adapter
        ListView lv = (ListView) findViewById(R.id.listView2);
        Myadapter myadapter = new Myadapter(this,listdata);
        lv.setAdapter(myadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(RequisitionHistory.this,RequisitionHistoryDetail.class);
                i.putExtra("Requisition",listdata.get(position));
              startActivity(i);
            }
        });

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

            View v = inflater.inflate(R.layout.requisition_history_row, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.req_id);
            TextView tv2 = (TextView) v.findViewById(R.id.req_date);
            TextView tv3 = (TextView) v.findViewById(R.id.req_status);
            tv1.setText(list.get(position).getId());
            tv2.setText(list.get(position).getProcessDate().toString());
            tv3.setText(list.get(position).getStatus());

            return v;
        }

    }

}
