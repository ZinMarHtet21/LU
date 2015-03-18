package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.RetrivalListAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.RetrivalListItemDetailAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItemDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RetrievalListDetail extends ActionBarActivity {
    private RequestQueue mRequestQueue;
    private RetrivalItem item;
    private ArrayList<RetrivalItemDetail> itemDetails;
    private String url = UrlManager.APIROOTURL + "retrivalformApi/getpendingitemdetail/";
    private String saveUrl = UrlManager.APIROOTURL+"retrivalformApi/changeactualqty";
    private ListView list;
    private TextView itemName;

    @Override
    protected void onResume() {
        super.onResume();
        getdata();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrival_list_detail);
        list = (ListView) findViewById(R.id.departmentlist);
        itemName = (TextView) findViewById(R.id.itemName);
        Button btnSave = (Button) findViewById(R.id.Save);

        itemDetails = new ArrayList<RetrivalItemDetail>();
        mRequestQueue = Volley.newRequestQueue(this);
        Intent i = getIntent();
        item = (RetrivalItem) i.getExtras().getSerializable("retrivalitem");
        itemName.setText(item.getItem_desc());



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject tobeSent = collectionResult();

                JsonRequest<JSONObject> request = new JsonObjectRequest(Request.Method.POST,saveUrl,tobeSent,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(RetrievalListDetail.this,"Change Actual Quantity Successfully!",Toast.LENGTH_SHORT).show();
                        getdata();
                        finish();


                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }) {
                };

                mRequestQueue.add(request);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrival_list_detail, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private JSONObject collectionResult(){

        JSONObject jo = new JSONObject();
        JSONArray jar = new JSONArray();
        try {
            jo.put("itemcode", item.getItem_code());

            for (int i = 0; i < list.getAdapter().getCount(); i++) {

                View wantedView = list.getChildAt(i);
                EditText acturalValueText = (EditText) wantedView.findViewById(R.id.actual_qty);
                String acturl = acturalValueText.getText().toString();
                JSONObject o = new JSONObject();
                o.put("Dept", itemDetails.get(i).getDepartmentName());
                o.put("qty", acturl);
                jar.put(o);

                Log.i("List", jar.toString());
            }

            jo.put("list", jar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("total",jo.toString());
        return jo;
    }

    public void getdata(){

        JsonArrayRequest jar = new JsonArrayRequest(url + item.getItem_code(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                System.out.print(jsonArray);

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject o = jsonArray.getJSONObject(i);
                        RetrivalItemDetail itemDetail = new RetrivalItemDetail();
                        itemDetail.setDepartmentName(o.getString("Dept"));
                        itemDetail.setNeededQty(o.getInt("qty"));
                        itemDetails.add(itemDetail);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RetrivalListItemDetailAdapter adapter = new RetrivalListItemDetailAdapter(RetrievalListDetail.this, itemDetails);
                list.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(jar);


    }
}
