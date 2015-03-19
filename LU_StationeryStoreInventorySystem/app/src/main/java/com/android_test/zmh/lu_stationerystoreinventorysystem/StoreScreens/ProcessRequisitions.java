package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.ProcessRequisitionAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcessRequisitions extends ActionBarActivity {

    private IItem itemPopulator;
    private RequestQueue mRequestQueue;
    private ArrayList<Item> retrivalItems;
    private String url = UrlManager.APIROOTURL+"retrivalformApi/pendingprocess";
    private String url2 = UrlManager.APIROOTURL+"retrivalformApi/process";
    private Button button ;
    private ListView list;

    @Override
    protected void onResume() {
        super.onResume();
        JsonArrayRequest jr = getRequest();
        mRequestQueue.add(jr);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_requisitions);
        button = (Button)findViewById(R.id.button);


        itemPopulator = new ItemPopulator();
        mRequestQueue = Volley.newRequestQueue(this);
       list = (ListView) findViewById(R.id.item_list);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_process_requisitions, menu);
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

    public void setButtonOnclick(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProcessRequisitions.this).setTitle("Confirm")
                        .setMessage("Confirm Process All the Requisitions?").setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        process();
                        Toast.makeText(ProcessRequisitions.this,"Process Successfully!",Toast.LENGTH_SHORT).show();

                        finish();

                    }
                }).setNegativeButton("Cancel",null).show();

            }

        });
    }

    private void process(){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url2,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(jsonRequest);
        mRequestQueue.add(getRequest());
    }

    private JsonArrayRequest getRequest(){
        JsonArrayRequest jr = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    retrivalItems = itemPopulator.populatePendingProcessedItem(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ProcessRequisitionAdapter adapter = new ProcessRequisitionAdapter(ProcessRequisitions.this, retrivalItems);
                list.setAdapter(adapter);
               if (response.length() == 0){

                   Toast.makeText(ProcessRequisitions.this,"No requisitions to be processed!",Toast.LENGTH_SHORT).show();
                   button.setBackgroundColor(Color.LTGRAY);
               }
                else {
                   setButtonOnclick();
               }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG,error.getMessage());
            }
        });
      //  mRequestQueue.add(jr);
        return jr;

    }
}
