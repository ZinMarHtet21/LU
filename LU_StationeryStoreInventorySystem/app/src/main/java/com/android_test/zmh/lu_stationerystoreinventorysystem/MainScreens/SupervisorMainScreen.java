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

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectPurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;

import java.util.List;


public class SupervisorMainScreen extends ListActivity {

    String[] supervisor_menu = {"Update Profile","Approve/Reject Purchase Order", "Approve/Reject Stock Adjustment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,supervisor_menu);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id) {
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("Approve/Reject Purchase Order")){
            i = new Intent(this, ApproveRejectPurchaseOrder.class);
        }else if(item.equals("Approve/Reject Stock Adjustment")){
            i = new Intent(this,ApproveRejectStockAdjustment.class);
        }

        startActivity(i);
    }
}
