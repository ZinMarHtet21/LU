package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;


public class NewRequisition extends ActionBarActivity {

    ListView new_req_listView;
    ArrayAdapter mArrayAdapter;
    ArrayList mItemList = new ArrayList();
    Button sendReqBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
        new_req_listView = (ListView)findViewById(R.id.item_list_lv);
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mItemList);
        new_req_listView.setAdapter(mArrayAdapter);

        sendReqBtn = (Button)findViewById(R.id.button_send_req);
        sendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        NewRequisition.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Alert Dialog");

                // Setting Dialog Message
                alertDialog.setMessage("Welcome to AndroidHive.info");

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });
    }

}
