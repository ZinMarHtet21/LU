package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectPurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustmentForManager;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class ManagerMainScreen extends Activity implements AdapterView.OnItemClickListener {

    String[] manager_menu = {"Update Profile","Approve/Reject Stock Adjustment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_screen);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row,R.id.main_screen_list_row,manager_menu);
        ListView clerk_lv = (ListView)findViewById(R.id.lv_manager);
        clerk_lv.setAdapter(adapter);
        clerk_lv.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        String item = (String)av.getAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Approve/Reject Stock Adjustment")){
            i = new Intent(this, ApproveRejectStockAdjustmentForManager.class);
        }
        startActivity(i);
    }

    /*@Override
    public void onListItemClick(ListView l,View v,int position,long id){
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Retrieval DisbursementItemList")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Approve/Reject Stock Adjustment")){
            i = new Intent(this,ApproveRejectStockAdjustmentForManager.class);
        }

        startActivity(i);
    }*/
}
