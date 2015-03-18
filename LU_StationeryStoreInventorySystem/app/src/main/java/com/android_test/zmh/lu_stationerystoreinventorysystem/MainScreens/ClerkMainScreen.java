package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockMain;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.CheckLowStockSearch;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.DisbursementDepartmentList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.ProcessRequisitions;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.PurchaseOrderUI;
import com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens.RetrievalList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class ClerkMainScreen extends Activity implements AdapterView.OnItemClickListener{



    String[] clerk_menu = {"Process Requisitions","Check Retrieval Form",
            "Check Disbursement List","Check Stock","Check Purchase Order","Report Discrepancy","Update Profile"};




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
        }else if(item.equals("Check Retrieval Form")){
            i = new Intent(this, RetrievalList.class);

            startActivity(i);
        }else if(item.equals("Check Disbursement List")){
            i = new Intent(this, DisbursementDepartmentList.class);
            startActivity(i);
        }else if(item.equals("Check Stock")){
            i = new Intent(this, CheckLowStockMain.class);
            startActivity(i);
        }else if(item.equals("Check Purchase Order")){

            i = new Intent(this, PurchaseOrderUI.class);
            startActivity(i);
        }else if(item.equals("Report Discrepancy")){

            i = new Intent(this, CheckLowStockSearch.class);
            startActivity(i);
        }else if(item.equals("Process Requisitions")){
            i = new Intent(this, ProcessRequisitions.class);
            startActivity(i);
        }else if(item.equals("Logout")){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.emp = null;
                            ClerkMainScreen.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

    }

    /*@Override
    public void onListItemClick(ListView l,View v,int position,long id){
        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, ProcessRequisitions.class);
        }else if(item.equals("Retrieval DisbursementItemList")){
            i = new Intent(this, RetrievalList.class);
        }else if(item.equals("Disbursement DisbursementItemList")){
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
