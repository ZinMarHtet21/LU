package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrderUI;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ReportDiscrepency;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;

import java.util.ArrayList;
import java.util.List;


public class test extends ListActivity {

    String[] clerk_menu = {"Update Profile","Retrieval DisbursementItemList","Disbursement DisbursementItemList","Check Low Stock","Purchase Order","Report Discrepency"};
    List data= new ArrayList<Requisition>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequisitionPopulator pop = new RequisitionPopulator();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Retrieval DisbursementItemList")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Disbursement DisbursementItemList")){
            i = new Intent(this, DisbursementList.class);
        }else if(item.equals("Check Low Stock")){
            i = new Intent(this, CheckLowStockMain.class);
        }else if(item.equals("Purchase Order")){
            i = new Intent(this, PurchaseOrderUI.class);
        }else if(item.equals("Report Discrepency")){
            i = new Intent(this, ReportDiscrepency.class);
        }

        startActivity(i);
    }

}

