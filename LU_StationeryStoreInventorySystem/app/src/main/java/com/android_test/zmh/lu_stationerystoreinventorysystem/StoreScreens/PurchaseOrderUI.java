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

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.PurchaseOrderPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;


public class PurchaseOrderUI extends ActionBarActivity {

    PurchaseOrderPopulator obj = new PurchaseOrderPopulator();
    private ListView lv;
    private MyAdapter myadapter;
    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, List<PurchaseOrder>>() {
            @Override
            protected List<PurchaseOrder> doInBackground(Void... params) {

                return obj.PopulatePurchaseOrderListFromWcf();

            }
            @Override
            protected void onPostExecute(List<PurchaseOrder> result) {



                myadapter = new MyAdapter(PurchaseOrderUI.this,result);
                lv.setAdapter(myadapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(PurchaseOrderUI.this,PurchaseOrderDetailUI.class);

                        String po =  ((PurchaseOrder) parent.getAdapter().getItem(position)).getId();

                        i.putExtra("PurchaseOrderId", po);

                        startActivity(i);
                    }
                });

            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);
        lv = (ListView) findViewById(R.id.orderlist);


    }


    public class MyAdapter extends BaseAdapter {

        // starts here...
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        Context ctx;
        LayoutInflater inflater;

        public MyAdapter(Context contxt, List<PurchaseOrder> orders) {
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

            View v = inflater.inflate(R.layout.row_order, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.po);
            TextView tv2 = (TextView) v.findViewById(R.id.o_status);
            TextView tv3 = (TextView) v.findViewById(R.id.o_date);
            TextView tv4 = (TextView) v.findViewById(R.id.o_supplier);
            tv1.setText(list.get(position).getNumber());
            tv2.setText(list.get(position).getStatus());
            tv3.setText(list.get(position).getDate());
            tv4.setText(list.get(position).getSupplierName());
            return v;
        }

    }
}
