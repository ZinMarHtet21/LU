package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ReportDiscrepency;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
public class test extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
}*/


public class test extends ListActivity {

    String[] clerk_menu = {"Update Profile","Retrieval List","Disbursement List","Check Low Stock","Purchase Order","Report Discrepency"};
    List data= new ArrayList<Requisition>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequisitionPopulator pop = new RequisitionPopulator();
        //data =  pop.populateRequisition();

        for(Requisition r :pop.populateRequisition()){
            data.add(r.getStatus());
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);



        /*setListAdapter(new SimpleAdapter(this,data,android.R.layout.simple_list_item_1,
                new String[]{"date","departmentName"},
                new int[]{android.R.id.text1,android.R.id.text2}));*/
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Retrieval List")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Disbursement List")){
            i = new Intent(this, DisbursementList.class);
        }else if(item.equals("Check Low Stock")){
            i = new Intent(this, CheckLowStockMain.class);
        }else if(item.equals("Purchase Order")){
            i = new Intent(this, PurchaseOrder.class);
        }else if(item.equals("Report Discrepency")){
            i = new Intent(this, ReportDiscrepency.class);
        }

        startActivity(i);
    }

}

