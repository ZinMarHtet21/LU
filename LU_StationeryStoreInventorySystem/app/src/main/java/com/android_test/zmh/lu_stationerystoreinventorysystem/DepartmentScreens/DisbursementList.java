package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DisbursementPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DisbursementItemList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DisbursementList extends ActionBarActivity {

    final Context context = this;
    DisbursementPopulator disbPopulator = new DisbursementPopulator();
    List<DisbursementItem> disbList;
    List<DisbursementItemList> disbItemList;
    ListView disburment_lv;
    String remark;
    public final static String receiveDisbURL = UrlManager.APIROOTURL + "disbursementlistApi" + "/confirmcollect";


    Button receiveBtn;
    Myadapter myadapter;
    String jsonUpdateResult;
   // JSONObject jsonUpdateResult;
   // private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list);
        disburment_lv = (ListView) findViewById(R.id.disb_lv);
        receiveBtn = (Button)findViewById(R.id.button_receive);
      //  requestQueue = Volley.newRequestQueue(this);

        new AsyncTask<Void, Void, List<DisbursementItem>>() {
            @Override
            protected List<DisbursementItem> doInBackground(Void... params) {
                disbList = disbPopulator.getDisbursementList(MainActivity.emp.getDepartmentID());
                return disbList;
            }
            @Override
            protected void onPostExecute(List<DisbursementItem> result) {
                myadapter = new Myadapter(DisbursementList.this, result);
                disburment_lv.setAdapter(myadapter);
            }
        }.execute();

        disburment_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("Edit Quantity");

                final EditText new_qty = (EditText) promptsView
                        .findViewById(R.id.new_qty_et);
                new_qty.setText(Integer.toString(disbList.get(position).getItem_qty()));

                // set dialog message
                alertDialogBuilder
//                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                           DisbursementItem element = (DisbursementItem) myadapter.getItem(position);
                                        element.setItem_qty(Integer.parseInt(new_qty.getText().toString()));

                                       // element.setLblactual(Integer.parseInt(new_qty.getText().toString()));
                                        //new_qty.setText(Integer.toString(element.getItem_actual()));
                                        myadapter.notifyDataSetChanged();
                                        Toast.makeText(DisbursementList.this, "Item Edited", Toast.LENGTH_SHORT).show();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });

        receiveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                disbItemList = changeDisbursementItemToList(disbList);
                showPopUpForRemark();
            }
        });
    }

    public List<DisbursementItemList> changeDisbursementItemToList(List<DisbursementItem> disbItems){
        List<DisbursementItemList> disbItemList = new ArrayList<DisbursementItemList>();
        for(DisbursementItem d:disbItems){
            disbItemList.add(new DisbursementItemList(d.getItem_code(),d.getItem_qty()));
        }
        return disbItemList;
    }

    private void showPopUpForRemark() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_remark, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Remark");

        final EditText remarkET = (EditText) promptsView
                .findViewById(R.id.remark_et);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        remark = remarkET.getText().toString();
                        sendReceiveDisbursementList();
                    }
                });
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int id) {
//                                dialog.cancel();
//                            }
//                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
//    public void sendReceiveDisbursementList(){
//              jsonUpdateResult = disbPopulator.receiveDisbursementList(MainActivity.emp.getDepartmentID(),remark,disbItemList);
//        Log.i("jsonResult",jsonUpdateResult.toString());
////        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,receiveDisbURL, jsonUpdateResult,new Response.Listener<JSONObject>() {
////            @Override
////            public void onResponse(JSONObject jsonObject) {
////                System.out.println(jsonObject);
////            }
////        },new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError volleyError) {
////
////            }
////        })
////        {
////        };
////
////        requestQueue.add(request);
//
//    }



    public void sendReceiveDisbursementList(){
//        Toast.makeText(DisbursementList.this,"receiveBtn",Toast.LENGTH_SHORT).show();
//        jsonUpdateResult = disbPopulator.receiveDisbursementList(MainActivity.emp.getDepartmentID(),remark,disbItemList);
//                        System.out.println("JSON UPDATE");
//                        System.out.println(jsonUpdateResult);

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        jsonUpdateResult = disbPopulator.receiveDisbursementList(MainActivity.emp.getDepartmentID(),remark,disbItemList);
                        System.out.println("JSON UPDATE");
                        System.out.println(jsonUpdateResult);
                        return jsonUpdateResult;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if(!result.equals(null)){
                            finish();
                            Toast.makeText(DisbursementList.this,"Receive Disbursement DisbursementItemList Successfully!",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(DisbursementList.this,"There is no current disbursement list.",Toast.LENGTH_LONG).show();
                        }
                    }

                }.execute();
    }




    public class Myadapter extends BaseAdapter {

        // starts here...
        List<DisbursementItem> list = new ArrayList<DisbursementItem>();
        Context ctx;
        LayoutInflater inflater;

        public Myadapter(Context contxt, List<DisbursementItem> listData) {
            ctx = contxt;
            list = listData;
            inflater = LayoutInflater.from(this.ctx);
        }
        // ends here...

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = inflater.inflate(R.layout.row_items, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.itemName_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.itemQty_et);

            tv1.setText(list.get(position).getItem_desc());
            tv2.setText(Integer.toString(list.get(position).getItem_qty()));

            return v;
        }

    }
}

