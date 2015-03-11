package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewRequisition extends ActionBarActivity {

    ListView new_req_listView;
    ArrayAdapter mArrayAdapter;
    ArrayList mItemList = new ArrayList();
    Button sendReqBtn, addBtn;
    EditText itemQty;
    Spinner itemSpinner;
    String itemName;
    int itemID;
    int qty;
    HashMap<Integer,String> itemMap = new HashMap<Integer,String>();

    List<Integer> itemIDList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
        addItemToMap();

        itemQty = (EditText)findViewById(R.id.item_qty_et);
        itemSpinner = (Spinner)findViewById(R.id.itemlist_spnr);

        new_req_listView = (ListView)findViewById(R.id.item_list_lv);
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mItemList);
        new_req_listView.setAdapter(mArrayAdapter);


        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                itemName = adapter.getItemAtPosition(position).toString();

                for (Map.Entry<Integer, String> entry : itemMap.entrySet()) {
                    System.out.println(itemName);
                    System.out.println(entry.getValue());

                    if(entry.getValue().equals(itemName)){
                        System.out.println("SAME");
                        itemID = entry.getKey();
                    }
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        addBtn = (Button)findViewById(R.id.button_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = Integer.parseInt(itemQty.getText().toString());

                AlertDialog alertDialog = new AlertDialog.Builder(
                        NewRequisition.this).create();

                alertDialog.setTitle("Alert Dialog");
                alertDialog.setMessage(itemID + "\n" + qty);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog.show();

            }
        });

        sendReqBtn = (Button)findViewById(R.id.button_send_req);
        sendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void addItemToMap(){
        itemMap.put(1, "Clip Double 1\"");
        itemMap.put(2,"Clip Dobule 2\"");
        itemMap.put(3,"Clip Double 3/4\"");
        itemMap.put(4,"Clip Paper large");
        itemMap.put(5,"Clip Paper Medium");
        itemMap.put(6,"Clip Paper Small");
    }



}
