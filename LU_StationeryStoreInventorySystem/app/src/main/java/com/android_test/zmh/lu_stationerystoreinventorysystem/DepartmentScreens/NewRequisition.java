package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewRequisition extends ActionBarActivity {

    ListView new_req_listView;

    Button sendReqBtn, addBtn;
    EditText itemQty;
    Spinner itemSpinner;
    String itemName;
    ItemsAdapter itemAdapter;

    int itemID;
    int qty;
    HashMap<Integer,String> itemMap = new HashMap<Integer,String>();
    ArrayList<TempItem> items= new ArrayList<TempItem>();
    ArrayList<TempItem> arrayOfItems = new ArrayList<TempItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
        addItemToMap();

        itemAdapter = new ItemsAdapter(this, arrayOfItems);

        new_req_listView = (ListView) findViewById(R.id.item_list_lv);
        new_req_listView.setAdapter(itemAdapter);


        itemQty = (EditText)findViewById(R.id.item_qty_et);
        itemSpinner = (Spinner)findViewById(R.id.itemlist_spnr);

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                itemName = adapter.getItemAtPosition(position).toString();

                for (Map.Entry<Integer, String> entry : itemMap.entrySet()) {
                    if(entry.getValue().equals(itemName)){
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
                arrayOfItems.add(new TempItem(itemName,qty));
                itemAdapter.notifyDataSetChanged();


//                qty = Integer.parseInt(itemQty.getText().toString());

//                AlertDialog alertDialog = new AlertDialog.Builder(
//                        NewRequisition.this).create();
//
//                alertDialog.setTitle("Alert Dialog");
//                alertDialog.setMessage(itemID + "\n" + qty);
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alertDialog.show();

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

class TempItem{
    String iName;
    int iQty;

    public TempItem(String iName,int iQty){
        this.iName = iName;
        this.iQty = iQty;
    }
}

class ItemsAdapter extends ArrayAdapter<TempItem> {
    public ItemsAdapter(Context context, ArrayList<TempItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TempItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_items, parent, false);
        }

        TextView tvItemName = (TextView) convertView.findViewById(R.id.itemName_txt);
        TextView tvItemQty = (TextView) convertView.findViewById(R.id.itemQty_txt);

        tvItemName.setText(item.iName);
        tvItemQty.setText(Integer.toString(item.iQty));

        return convertView;
    }
}


