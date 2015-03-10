package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.DisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionHistory;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ReportDiscrepency;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class ClerkMainScreen extends ListActivity {

    String[] clerk_menu = {"Update Profile","Retrieval List","Disbursement List","Check Low Stock","Purchase Order","Report Discrepency"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,clerk_menu);
        setListAdapter(adapter);
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
