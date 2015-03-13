package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewRequisition extends ActionBarActivity {

    Button sendReqBtn, addBtn;
    EditText itemQty;
    Spinner itemSpinner;
    ItemsAdapter itemAdapter;

    String itemID;
    String itemDesc;
    int qty;

    ItemPopulator iPop = new ItemPopulator();
    ListView new_req_listView;
    HashMap<String, String> itemMap = new HashMap<String, String>();
    ArrayList<TempItem> arrayOfItems = new ArrayList<TempItem>();
    ArrayList itemDescList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);

        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... params) {
                return iPop.getItemList();
            }

            @Override
            protected void onPostExecute(List<Item> result) {

                for (Item i : result) {
                    itemDescList.add(i.getDescription());
                    itemMap.put(i.getId().toString(), i.getDescription());
                }

                itemSpinner = (Spinner) findViewById(R.id.itemlist_spnr);
                ArrayAdapter dataAdapter = new ArrayAdapter(NewRequisition.this, android.R.layout.simple_spinner_item, itemDescList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                itemSpinner.setAdapter(dataAdapter);

                itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                        itemDesc = adapter.getItemAtPosition(position).toString();

                        for (Map.Entry<String, String> entry : itemMap.entrySet()) {
                            if (entry.getValue().equals(itemDesc)) {
                                itemID = entry.getKey();
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        }.execute();

        itemAdapter = new ItemsAdapter(this, arrayOfItems);

        new_req_listView = (ListView) findViewById(R.id.item_list_lv);
        new_req_listView.setAdapter(itemAdapter);
        new_req_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                itemDesc = parent.getItemAtPosition(position).toString();

                AlertDialog alertDialog = new AlertDialog.Builder(NewRequisition.this).create();
                alertDialog.setTitle("Delete Item");
                alertDialog.setMessage("Are you sure to delete?");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        arrayOfItems.remove(pos);
                        itemAdapter = new ItemsAdapter(NewRequisition.this, arrayOfItems);
                        new_req_listView.setAdapter(itemAdapter);
                        itemAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Item Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setCancelable(true);
                alertDialog.show();


                return false;
            }
        });

        itemQty = (EditText) findViewById(R.id.item_qty_et);

        addBtn = (Button) findViewById(R.id.button_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = Integer.parseInt(itemQty.getText().toString());
                arrayOfItems.add(new TempItem(itemDesc, qty));
                itemAdapter.notifyDataSetChanged();

//                AlertDialog alertDialog = new AlertDialog.Builder(NewRequisition.this).create();
//                alertDialog.setTitle("Alert Dialog");
//                alertDialog.setMessage(itemID + "\n" + itemDesc + "\n" + qty);
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alertDialog.show();
            }
        });

        sendReqBtn = (Button) findViewById(R.id.button_send_req);
        sendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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


