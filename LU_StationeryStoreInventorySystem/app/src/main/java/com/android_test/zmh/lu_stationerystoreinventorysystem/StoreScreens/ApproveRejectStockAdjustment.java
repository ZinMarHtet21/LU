package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.AdjustmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;


public class ApproveRejectStockAdjustment extends ActionBarActivity {
    AdjustmentPopulator obj = new AdjustmentPopulator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_stock_adjustment);
        new AsyncTask<Void, Void, List<AdjustmentVoucher>>() {
            @Override
            protected List<AdjustmentVoucher> doInBackground(Void... params) {

                return obj.populateSupervisorListFromWcf();

            }
            @Override
            protected void onPostExecute(List<AdjustmentVoucher> result) {
                ListView lv = (ListView) findViewById(R.id.list_voucher);


                MyAdapter myadapter = new MyAdapter(ApproveRejectStockAdjustment.this,result);
                lv.setAdapter(myadapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(ApproveRejectStockAdjustment.this,ApproveRejectStockAdjustmentDetail.class);
                        String vi =  ((AdjustmentVoucher) parent.getAdapter().getItem(position)).getVoucher_id();
                        i.putExtra("VoucherNo",  vi);

                        startActivity(i);
                    }
                });

            }
        }.execute();

    }


    public class MyAdapter extends BaseAdapter {


        List<AdjustmentVoucher> list = new ArrayList<AdjustmentVoucher>();
        Context ctx;
        LayoutInflater inflater;

        public MyAdapter(Context contxt, List<AdjustmentVoucher> orders) {
            ctx = contxt;
            list = orders;
            inflater = LayoutInflater.from(this.ctx);
        }


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
