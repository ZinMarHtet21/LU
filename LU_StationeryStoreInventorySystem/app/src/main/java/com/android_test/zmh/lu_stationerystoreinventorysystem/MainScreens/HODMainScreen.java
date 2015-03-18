package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.DisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionHistory;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;

import java.lang.reflect.Array;
import java.sql.SQLOutput;


public class HODMainScreen extends Activity implements AdapterView.OnItemClickListener {

    private String[] hod_menu = {"Update Profile", "Approve/Reject Requisition","Logout"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_main_screen);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hod_menu);
//        setListAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.main_screen_list_row, hod_menu);
        ListView hod_lv = (ListView) findViewById(R.id.hod_mainscreen_list);
        hod_lv.setAdapter(adapter);
        hod_lv.setOnItemClickListener(this);

    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        String item = (String) av.getAdapter().getItem(position);
        Intent i = new Intent();

        if (item.equals("Update Profile")) {
            i = new Intent(this, UpdateProfile.class);
            startActivity(i);
        } else if (item.equals("Approve/Reject Requisition")) {
            i = new Intent(this, RequisitionList.class);
            startActivity(i);
        }else if(item.equals("Logout")){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.emp = null;
                            HODMainScreen.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }
}

//    @Override
//    public void onListItemClick(ListView l,View v,int position,long id){
//        String item = (String)getListAdapter().getItem(position);
//        Intent i = new Intent();
//
//        System.out.println("ETYPE");
//        System.out.println(MainActivity.emp.getType());
//
//
//
////        if(item.equals("Update Profile")){
////            i = new Intent(this, UpdateProfile.class);
////        }else if(item.equals("New Requisition")){
////            i = new Intent(this, NewRequisition.class);
////        }else if(item.equals("Requisition DisbursementItemList")){
////            i = new Intent(this, RequisitionList.class);
////        }else if(item.equals("Requisition History")){
////            i = new Intent(this, RequisitionHistory.class);
////        }
//        startActivity(i);
//    }


//}
