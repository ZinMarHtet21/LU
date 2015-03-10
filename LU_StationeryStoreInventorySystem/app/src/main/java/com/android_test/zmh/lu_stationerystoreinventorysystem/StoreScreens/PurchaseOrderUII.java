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

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.PurchaseOrderPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;


public class PurchaseOrderUII extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);


        //get the data list from populator(orders)
        PurchaseOrderPopulator obj = new PurchaseOrderPopulator();
        final List<PurchaseOrder> orders = obj.PopulatePurchaseOrder();



        //create an adapter and pass the data ,set the adapter for the listView
        ListView lv = (ListView) findViewById(R.id.orderlist);
        MyAdapter myadapter = new MyAdapter(this, orders);
        lv.setAdapter(myadapter);
        //click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PurchaseOrderUII.this,PurchaseOrderDetail.class);
                i.putExtra("PurchaseOrders",  orders.get(position));
                startActivity(i);
            }
        });

    }


    public class MyAdapter extends BaseAdapter {

        // starts here...
        List<com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder> list = new ArrayList<com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder>();
        Context ctx;
        LayoutInflater inflater;

        public MyAdapter(Context contxt, List<com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder> orders) {
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
            tv1.setText(list.get(position).getNumber());
            tv2.setText(list.get(position).getStatus());
            return v;
        }

    }
}
