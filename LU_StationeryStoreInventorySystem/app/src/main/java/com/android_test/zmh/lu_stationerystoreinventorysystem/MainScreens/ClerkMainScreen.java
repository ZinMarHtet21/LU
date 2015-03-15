package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.DisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionHistory;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementDepartment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockSearch;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.DisbursementDepartmentList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ProcessRequisitions;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrderUII;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ReportDiscrepency;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class ClerkMainScreen extends Activity implements AdapterView.OnItemClickListener{

    String[] clerk_menu = {"Update Profile","Retrieval List",
            "Disbursement List","Check Low Stock","Purchase Order","Report Discrepency"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clerk_main_screen);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,clerk_menu);
        //setListAdapter(adapter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row,R.id.main_screen_list_row,clerk_menu);
        ListView clerk_lv = (ListView)findViewById(R.id.lv_clerk);
        clerk_lv.setAdapter(adapter);
        clerk_lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        String item = (String)av.getAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Retrieval List")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Disbursement List")){
            i = new Intent(this, DisbursementDepartmentList.class);
        }else if(item.equals("CheckLowStockMain")){
            i = new Intent(this, CheckLowStockMain.class);
        }else if(item.equals("Purchase Order")){
            i = new Intent(this, PurchaseOrderUII.class);
        }else if(item.equals("Report Discrepency")){
            i = new Intent(this, CheckLowStockSearch.class);
        }
        startActivity(i);
    }

    /*@Override
    public void onListItemClick(ListView l,View v,int position,long id){
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, ProcessRequisitions.class);
        }else if(item.equals("Retrieval List")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Disbursement List")){
            i = new Intent(this, DisbursementDepartmentList.class);
        }else if(item.equals("Check Low Stock")){
            i = new Intent(this, CheckLowStockMain.class);
        }else if(item.equals("Purchase Order")){
            i = new Intent(this, PurchaseOrderUII.class);
        }else if(item.equals("Report Discrepency")){
            i = new Intent(this, CheckLowStockSearch.class);
        }

        startActivity(i);
    }*/

}
