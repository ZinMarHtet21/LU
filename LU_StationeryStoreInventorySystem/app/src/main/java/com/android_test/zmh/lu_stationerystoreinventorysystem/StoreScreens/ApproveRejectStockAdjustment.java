package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Context;
import android.content.Intent;
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
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;


public class ApproveRejectStockAdjustment extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_stock_adjustment);
        AdjustmentPopulator obj = new AdjustmentPopulator();
        final List<AdjustmentVoucher> orders = obj.populateSupervisorList();



        ListView lv = (ListView) findViewById(R.id.list_voucher);
        Adapter myadapter = new Adapter(this, orders);
        lv.setAdapter(myadapter);
        //click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ApproveRejectStockAdjustment.this, ApproveRejectStockAdjustmentDetail.class);
                i.putExtra("Adjustment", orders.get(position));
                startActivity(i);
            }
        });

    }


    public class Adapter extends BaseAdapter {

        // starts here...
        List<AdjustmentVoucher> list = new ArrayList<AdjustmentVoucher>();
        Context ctx;
        LayoutInflater inflater;

        public Adapter(Context contxt, List<AdjustmentVoucher> orders) {
            ctx = contxt;
            list = orders;
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
            tv1.setText("Voucher# "+list.get(position).getVoucher_id());

            return v;
        }

    }
}
