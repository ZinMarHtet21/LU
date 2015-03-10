package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
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

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DisbursementPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;


public class DisbursementList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list);

        DisbursementPopulator pop = new DisbursementPopulator();
        final List<Disbursement> listdata = pop.populateDisbursement();


        // create an adapter
        // pass the data.
        //set the adapter
        ListView lv = (ListView) findViewById(R.id.disbursement_list_lv);
        Myadapter myadapter = new Myadapter(this,listdata);
        lv.setAdapter(myadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DisbursementList.this,DisbursementListDetail.class);
                i.putExtra("Disbursement",listdata.get(position));
                startActivity(i);
            }
        });
    }

    public class Myadapter extends BaseAdapter {

        // starts here...
        List<Disbursement> list = new ArrayList<Disbursement>();
        Context ctx;
        LayoutInflater inflater;

        public Myadapter(Context contxt, List<Disbursement> listData) {
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

            View v = inflater.inflate(R.layout.disbursement_list_row, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.disbursement_id_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.disbursement_date_txt);
            tv1.setText(list.get(position).getID());
            tv2.setText(list.get(position).getDate().toString());
            return v;
        }

    }

}
