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

    final Context context = this;

    Button sendReqBtn, addBtn;
    EditText itemQty;
    Spinner itemSpinner;
    ItemsAdapter itemAdapter;

    String itemID;
    String itemDesc;
    int qty;
    int pos;

    ItemPopulator iPop = new ItemPopulator();
    public ListView new_req_listView;
    HashMap<String, String> itemMap = new HashMap<String, String>();
    public static ArrayList<TempItem> arrayOfItems = new ArrayList<TempItem>();
    public static ArrayList<TempItem> finalitems = new ArrayList<TempItem>();

    ArrayList itemDescList = new ArrayList();
    AlertDialog builder;

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
                pos = position;
                System.out.println("LIST ITEM POSITION");
                System.out.println(pos);
                itemDesc = parent.getItemAtPosition(position).toString();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewRequisition.this);
                alertDialog.setTitle("Action");
                alertDialog.setMessage("What would you like to do?");

                alertDialog.setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), "You clicked on EDIT", Toast.LENGTH_SHORT).show();
                                // get prompts.xml view
                                LayoutInflater li = LayoutInflater.from(context);
                                View promptsView = li.inflate(R.layout.prompt, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setView(promptsView);
                                alertDialogBuilder.setTitle("Edit Quantity");

                                final EditText new_qty = (EditText) promptsView
                                        .findViewById(R.id.upd_empType_txt);

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,int id) {
                                                        // get user input and set it to result
                                                        // edit text
//                                                        System.out.println("INSIDE PROMPT");
//                                                        System.out.println(pos);
                                                        TempItem element = (TempItem)itemAdapter.getItem(pos);
                                                        element.iQty = 35;
                                                        itemAdapter.notifyDataSetChanged();



                                                        Toast.makeText(getApplicationContext(), "Edited" , Toast.LENGTH_SHORT).show();
//                                                        text.setText();
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
                                itemAdapter = new ItemsAdapter(NewRequisition.this, arrayOfItems);
                                new_req_listView.setAdapter(itemAdapter);
                                itemAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Item Deleted Successfully!", Toast.LENGTH_SHORT).show();

                            }
                        });

                alertDialog.show();

                return false;

            }
        });


//                AlertDialog alertDialog = new AlertDialog.Builder(NewRequisition.this).create();
//                alertDialog.setTitle("Delete Item");
//                alertDialog.setMessage("Actions for ");

//                alertDialog.setButton("Edit", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
////                        arrayOfItems.remove(pos);
////                        itemAdapter = new ItemsAdapter(NewRequisition.this, arrayOfItems);
////                        new_req_listView.setAdapter(itemAdapter);
////                        itemAdapter.notifyDataSetChanged();
//                        Toast.makeText(getApplicationContext(), "Item Deleted Successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alertDialog.setButton("Delete", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
////                        arrayOfItems.remove(pos);
////                        itemAdapter = new ItemsAdapter(NewRequisition.this, arrayOfItems);
////                        new_req_listView.setAdapter(itemAdapter);
////                        itemAdapter.notifyDataSetChanged();
//                        Toast.makeText(getApplicationContext(), "Item Deleted Successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                //alertDialog.setCancelable(true);
//                alertDialog.show();






        itemQty = (EditText) findViewById(R.id.item_qty_et);

        addBtn = (Button) findViewById(R.id.button_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = Integer.parseInt(itemQty.getText().toString());
                TempItem it = new TempItem(itemDesc, qty);
                arrayOfItems.add(it);
                finalitems.add(it);
                itemAdapter.notifyDataSetChanged();

//                System.out.println("Before Edit Qty");
//                System.out.println("<< arrayOfItems >>");
//                for(TempItem i:arrayOfItems){
//                    System.out.println(i.iName + " " + i.iQty);
//
//                }
//                System.out.println("<< finalItems >>");
//                for(TempItem i:finalitems){
//                    System.out.println(i.iName + " " + i.iQty);
//
//                }

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

//                System.out.println("After Edit Qty");
//                System.out.println("arrayOfItems");
//                System.out.println(arrayOfItems.toString());
//                System.out.println("finalItems");
//                System.out.println(finalitems.toString());

            }
        });

    }


    class TempItem {
        String iName;
        int iQty;

        public TempItem(String iName, int iQty) {
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

//            tvItemQty.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    try {
//                        finalitems.get(position).iQty = Integer.parseInt(tvItemQty.getText().toString());
//                        getItem(position).iQty = Integer.parseInt(tvItemQty.getText().toString());
//
//                    } catch (Exception e) {
//
//                    }
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });


            tvItemName.setText(item.iName);
            tvItemQty.setText(Integer.toString(item.iQty));

            return convertView;
        }
    }

}

