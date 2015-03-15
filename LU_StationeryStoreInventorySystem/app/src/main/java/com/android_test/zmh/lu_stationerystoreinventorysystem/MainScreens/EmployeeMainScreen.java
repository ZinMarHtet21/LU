package com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.NewRequisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.RequisitionHistory;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.UpdateProfile;


public class EmployeeMainScreen extends Activity implements AdapterView.OnItemClickListener {

    String[] emp_menu = {"Update Profile","New Requisition","Requisition History"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main_screen);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,emp_menu);
//        setListAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row,R.id.main_screen_list_row,emp_menu);
        ListView emp_lv = (ListView)findViewById(R.id.emp_mainscreen_list);
        emp_lv.setAdapter(adapter);
        emp_lv.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        String item = (String)av.getAdapter().getItem(position);
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

//    @Override
//    public void onListItemClick(ListView l,View v,int position,long id){
//
//        String item = (String)getListAdapter().getItem(position);
//        Intent i = new Intent();
//
//        if(item.equals("Update Profile")){
//            i = new Intent(this, UpdateProfile.class);
//        }else if(item.equals("New Requisition")){
//            i = new Intent(this, NewRequisition.class);
//        }else if(item.equals("Requisition History")){
//            i = new Intent(this, RequisitionHistory.class);
//        }
//        startActivity(i);
//    }
}
