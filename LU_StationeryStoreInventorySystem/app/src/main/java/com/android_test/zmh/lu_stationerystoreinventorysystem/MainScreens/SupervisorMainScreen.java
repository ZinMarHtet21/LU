package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectPurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ApproveRejectStockAdjustment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockSearch;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.DisbursementDepartmentList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrderUII;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;

import java.util.List;


public class SupervisorMainScreen extends Activity implements AdapterView.OnItemClickListener {

    String[] supervisor_menu = {"Update Profile","Approve/Reject Purchase Order", "Approve/Reject Stock Adjustment","Logout"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_main_screen);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,supervisor_menu);
        //setListAdapter(adapter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row,R.id.main_screen_list_row,supervisor_menu);
        ListView clerk_lv = (ListView)findViewById(R.id.lv_supervisor);
        clerk_lv.setAdapter(adapter);
        clerk_lv.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        String item = (String)av.getAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
            startActivity(i);
        }else if(item.equals("Approve/Reject Purchase Order")){
            i = new Intent(this, ApproveRejectPurchaseOrder.class);
            startActivity(i);
        }else if(item.equals("Approve/Reject Stock Adjustment")){
            i = new Intent(this, ApproveRejectStockAdjustment.class);
            startActivity(i);
        }else if(item.equals("Logout")){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.emp = null;
                            SupervisorMainScreen.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }

    /*@Override
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
    }*/
}
