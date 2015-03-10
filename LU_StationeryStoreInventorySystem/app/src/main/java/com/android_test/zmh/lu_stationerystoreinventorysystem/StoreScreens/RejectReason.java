package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

/**
 * Created by student on 8/3/15.
 */
public class RejectReason extends ActionBarActivity

    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_reason);

            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);

            Button btn =(Button) findViewById(R.id.btnSave);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RejectReason.this, ApproveRejectStockAdjustment.class);
                    //i.putExtra("Adjustment", orders.get(position));
                    startActivity(i);
                }
            });



    }




   }
