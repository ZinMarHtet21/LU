package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionHistory;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class EmployeeMainScreen extends ListActivity {

    String[] emp_menu = {"Update Profile","New Requisition","Requisition History"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,emp_menu);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){

        String item = (String)getListAdapter().getItem(position);
        Intent i = new Intent();

        if(item.equals("Update Profile")){
            i = new Intent(this, UpdateProfile.class);
        }else if(item.equals("New Requisition")){
            i = new Intent(this, NewRequisition.class);
        }else if(item.equals("Requisition History")){
            i = new Intent(this, RequisitionHistory.class);
        }
        startActivity(i);
    }
}
