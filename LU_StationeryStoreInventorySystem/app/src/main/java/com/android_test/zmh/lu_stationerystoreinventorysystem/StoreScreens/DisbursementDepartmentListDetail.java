package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.DisbursementDepratmentListDetailAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

public class DisbursementDepartmentListDetail extends ActionBarActivity {
    DepartmentDisbursementList ddl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_department_list_detail);
        ListView list = (ListView) findViewById(R.id.itemlist);
        TextView text_depratmentName = (TextView) findViewById(R.id.departmentName);
        Intent i = getIntent();
        ddl = (DepartmentDisbursementList) i.getExtras().getSerializable("disbursementlist");
        text_depratmentName.setText(ddl.getDepartment().getName());
        DisbursementDepratmentListDetailAdapter adapter = new DisbursementDepratmentListDetailAdapter(DisbursementDepartmentListDetail.this, ddl);
        list.setAdapter(adapter);


        System.out.println(ddl.getDepartment().getRepresentative());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disbursement_department_list_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
