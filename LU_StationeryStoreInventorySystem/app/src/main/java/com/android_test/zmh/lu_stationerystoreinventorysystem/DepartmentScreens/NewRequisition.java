package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;


public class NewRequisition extends ActionBarActivity {

    ListView new_req_listView;
    ArrayAdapter mArrayAdapter;
    ArrayList mItemList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
        new_req_listView = (ListView)findViewById(R.id.item_list_lv);
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mItemList);
        new_req_listView.setAdapter(mArrayAdapter);
    }

}
