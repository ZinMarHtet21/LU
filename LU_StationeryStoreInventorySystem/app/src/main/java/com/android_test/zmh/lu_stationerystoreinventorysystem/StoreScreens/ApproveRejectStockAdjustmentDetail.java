package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucherDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ApproveRejectStockAdjustmentDetail extends ActionBarActivity {

    AdjustmentVoucher model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_stock_adjustment_detail);
        if (getIntent()!= null) {
            model = (AdjustmentVoucher) getIntent().getSerializableExtra("Adjustment");
        }


        ListView lv = (ListView) findViewById(R.id.lv_adDetail);
        SimpleAdapter mysimpleAdapter = new SimpleAdapter(this, convertModelToHashMapModel(model),
                R.layout.row_adjustment_detail,
                new String[]{"itemName", "qty"},
                new int[]{R.id.tv_itemName,R.id.editText_Qty});
        lv.setAdapter(mysimpleAdapter);

        TextView tv1 = (TextView) findViewById(R.id.tv_date);
        TextView tv2 = (TextView) findViewById(R.id.voucher_id);
        Button btn1 = (Button) findViewById(R.id.btApprove);
        Button btn2 = (Button) findViewById(R.id.btReject);
        tv1.setText(model.getDate().toString());
        tv2.setText(model.getVoucher_id());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApproveRejectStockAdjustmentDetail.this, RejectReason.class);
                // i.putExtra("user", );
                startActivity(i);
            }
        });


    }

    public ArrayList<hash> convertModelToHashMapModel(AdjustmentVoucher model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (AdjustmentVoucherDetail ad : model.getAdjustmentVoucherDetails()) {


            String qty = ""+ad.getQty();

            hashlist.add(new hash(ad.getItemName(),qty));
        }
        return hashlist;
    }


    public  class hash extends HashMap<String,String> {
        public hash(String item,String qty) {
            put("itemName", item);
            put("qty", qty);

        }
    }
}
