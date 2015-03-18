package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.AdjustmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 9/3/15.
 */
public class ApproveRejectStockAdjustmentForManager extends ActionBarActivity {
    AdjustmentPopulator obj = new AdjustmentPopulator();
    private ListView lv;
    private MyAdapter myadapter;

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, List<AdjustmentVoucher>>() {
            @Override
            protected List<AdjustmentVoucher> doInBackground(Void... params) {

                return obj.populateManagerListFromWcf();

            }

            @Override
            protected void onPostExecute(List<AdjustmentVoucher> result) {

                myadapter = new MyAdapter(ApproveRejectStockAdjustmentForManager.this, result);
                lv.setAdapter(myadapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(ApproveRejectStockAdjustmentForManager.this, ApproveRejectStockAdjustmentDetailForManager.class);
                        //i.putExtra("AdjustmentForManager", orders.get(position));
                        String vi =  ((AdjustmentVoucher) parent.getAdapter().getItem(position)).getVoucher_id();
                        i.putExtra("VoucherNo",  vi);

                        startActivity(i);
                    }
                });

            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_stock_adjustment);

        lv = (ListView) findViewById(R.id.list_voucher);

    }

    public class MyAdapter extends BaseAdapter {

        // starts here...
        List<AdjustmentVoucher> list = new ArrayList<AdjustmentVoucher>();
        Context ctx;
        LayoutInflater inflater;

        public MyAdapter(Context contxt, List<AdjustmentVoucher> orders2) {
            ctx = contxt;
            list = orders2;
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

            View v = inflater.inflate(R.layout.row_order_s, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.PO_s);
            TextView tv2 = (TextView) v.findViewById(R.id.s_date);
            TextView tv3 = (TextView) v.findViewById(R.id.s_status);
            tv1.setText("Voucher# "+list.get(position).getVoucher_id());
            tv2.setText(list.get(position).getDate());
            tv3.setText(list.get(position).getStatus());
            return v;
        }

    }
}