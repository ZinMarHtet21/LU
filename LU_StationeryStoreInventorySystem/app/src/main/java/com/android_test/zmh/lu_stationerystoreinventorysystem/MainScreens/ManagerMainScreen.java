package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustmentForManager;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class ManagerMainScreen extends ListActivity {

    String[] manager_menu = {"Update Profile","Approve/Reject Stock Adjustment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,manager_menu);
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
        }else if(item.equals("Approve/Reject Stock Adjustment")){
            i = new Intent(this,ApproveRejectStockAdjustmentForManager.class);
        }

        startActivity(i);
    }
}
