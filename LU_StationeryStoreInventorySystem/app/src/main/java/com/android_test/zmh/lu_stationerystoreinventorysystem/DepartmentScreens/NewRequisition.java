package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.EmployeeMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewRequisition extends ActionBarActivity {

    final Context context = this;

    Button sendReqBtn, addBtn;
    EditText itemQty;
    Spinner itemSpinner;
    ItemsAdapter itemAdapter;

    String itemID;
    String itemDesc;
    int qty;
    int pos;
    String jsonUpdateResult;
    int i=0;

    RequisitionPopulator reqPopulator = new RequisitionPopulator();
    ItemPopulator iPop = new ItemPopulator();
    public ListView new_req_listView;
    HashMap<String, String> itemMap = new HashMap<String, String>();
    public static ArrayList<TempItem> arrayOfItems = new ArrayList<TempItem>();
    ArrayList<RequisitionDetail> reqDetailList = new ArrayList<RequisitionDetail>();
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

        //show item in listview
        itemAdapter = new ItemsAdapter(this, arrayOfItems);

        new_req_listView = (ListView) findViewById(R.id.item_list_lv);
        new_req_listView.setAdapter(itemAdapter);
        new_req_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                itemDesc = parent.getItemAtPosition(position).toString();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewRequisition.this);
                alertDialog.setTitle("Action");
                alertDialog.setMessage("What would you like to do?");

                alertDialog.setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                LayoutInflater li = LayoutInflater.from(context);
                                View promptsView = li.inflate(R.layout.prompt, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setView(promptsView);
                                alertDialogBuilder.setTitle("Edit Quantity");

                                final EditText new_qty = (EditText) promptsView
                                        .findViewById(R.id.new_qty_et);

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,int id) {
                                                        // get user input and set it to result
                                                        // edit text
                                                        TempItem element = (TempItem)itemAdapter.getItem(pos);
                                                        element.iQty = Integer.parseInt(new_qty.getText().toString());
                                                        itemAdapter.notifyDataSetChanged();

                                                        TempItem test = arrayOfItems.get(pos);
//                                                        System.out.println(test.iID + " / " + test.iName+ " / " + test.iQty);
                                                        Toast.makeText(NewRequisition.this,"Item Edited",Toast.LENGTH_SHORT).show();

                                                    }
                                                })
                                        .setNegativeButton("Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        });

                alertDialog.setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                arrayOfItems.remove(pos);
//                                finalitems.remove(pos);
                                itemAdapter = new ItemsAdapter(NewRequisition.this, arrayOfItems);
                                new_req_listView.setAdapter(itemAdapter);
                                itemAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Item Deleted!", Toast.LENGTH_SHORT).show();

                            }
                        });

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
                TempItem it = new TempItem(itemID ,itemDesc, qty);

                //
                if(arrayOfItems.size()>0) {
                    // iterate through items...
                    boolean status = true;
                    for (i = 0; i < arrayOfItems.size(); i++) {
                        TempItem t = arrayOfItems.get(i);

                        if (t.iID.equals(itemID)) {
                            int temp_qty = t.iQty;
                            System.out.println("qty : " + qty + " | existing : " + t.iQty);
                            // qty = qty + t.iQty;
                            t.iQty = t.iQty + qty;
                            // if any problem found... just update and break the loop..
                            status = false;
                            break;

                        }
                    }
                    if(status){
                        // status will be false , if for loops runs and breaked..
                        // satstus will be true.. if the loop runs and no match foun...
                        arrayOfItems.add(it);
                    }
                }
                else{

                    arrayOfItems.add(it);
                }
                itemAdapter.notifyDataSetChanged();
                itemQty.setText("0");

            }
        });

        sendReqBtn = (Button) findViewById(R.id.button_send_req);
        sendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(TempItem i:arrayOfItems){

                    RequisitionDetail rd = new RequisitionDetail();
                    rd.setItem_id(i.iID);
                    rd.setItem_detail_qty(i.iQty);

                    reqDetailList.add(rd);
                }

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        jsonUpdateResult = reqPopulator.sendNewRequisition(MainActivity.emp.getId(), reqDetailList);
                        return jsonUpdateResult;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if(!result.equals(null)){
                            arrayOfItems.clear();
                            finish();
                            Toast.makeText(NewRequisition.this,"Send Request Successfully!",Toast.LENGTH_LONG).show();
                        }
                    }

                }.execute();
            }


        });

    }


    class TempItem {
        String iID;
        String iName;
        int iQty;

        public TempItem(String iID, String iName, int iQty) {
            this.iID = iID;
            this.iName = iName;
            this.iQty = iQty;
        }
    }

    class ItemsAdapter extends ArrayAdapter<TempItem> {
        public ItemsAdapter(Context context, ArrayList<TempItem> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            TempItem item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_items, parent, false);
            }

            TextView tvItemName = (TextView) convertView.findViewById(R.id.itemName_txt);
            TextView tvItemQty = (TextView) convertView.findViewById(R.id.itemQty_et);
            tvItemName.setText(item.iName);
            tvItemQty.setText(Integer.toString(item.iQty));

            return convertView;
        }
    }

}

