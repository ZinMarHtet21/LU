package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DepartmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DisbursementPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.List;


public class DisbursementList extends ActionBarActivity {

    DisbursementPopulator disbPopulator = new DisbursementPopulator();
    private ArrayList<Disbursement> disbList;
//    private String url = UrlManager.APIROOTURL;
    ListView disburment_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list);
        disburment_lv = (ListView) findViewById(R.id.disbursement_list_lv);

        new AsyncTask<Void, Void, List<Disbursement>>() {
            @Override
            protected List<Disbursement> doInBackground(Void... params) {

                return disbPopulator.getDisbursementList();

            }

            @Override
            protected void onPostExecute(List<Disbursement> result) {
                Myadapter myadapter = new Myadapter(DisbursementList.this, result);
                disburment_lv.setAdapter(myadapter);

                disburment_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(DisbursementList.this, DisbursementListDetail.class);
//                      i.putExtra("Requisition_id",reqList.get(position).getId());
                        String disID = ((Disbursement) parent.getAdapter().getItem(position)).getId();

                        i.putExtra("disb_id", disID);
                        //i.putExtra("Requisition_id",reqList.get(position).getId());
                        startActivity(i);
                    }
                });
            }
        }.execute();
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

            System.out.println("LIST");
            System.out.println(list.toString());
            View v = inflater.inflate(R.layout.row_disbursement_list, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.disbursement_id_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.disbursement_date_txt);

            tv1.setText((list.get(position).getId()).toString());
            tv2.setText(Integer.toString((list.get(position).getQty())));
            return v;
        }

    }
}
