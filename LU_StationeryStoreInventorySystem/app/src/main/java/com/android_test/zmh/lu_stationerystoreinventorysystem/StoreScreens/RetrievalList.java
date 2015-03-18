package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.DisbursementDepratmentListAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.RetrivalListAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItemDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RetrievalList extends ActionBarActivity {
    private RequestQueue mRequestQueue;
    private ArrayList<RetrivalItem> items;
    private String url = UrlManager.APIROOTURL+"retrivalformApi/pendingcollect";
    private String url2 = UrlManager.APIROOTURL+"retrivalformApi/confirmcollect";
    private ListView list;
    private Button btn_confirmCollect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_retrieval_list);
        btn_confirmCollect =(Button)findViewById(R.id.button);
        list  = (ListView) findViewById(R.id.listView);
        setBtn_confirmCollectOnClick();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRequestQueue.add(getRequest());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrieval_list, menu);
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
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setBtn_confirmCollectOnClick(){
        btn_confirmCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RetrievalList.this).setTitle("Confirm")
                        .setMessage("Confirm Collecting All the Item?").setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmCollect();
                        Toast.makeText(RetrievalList.this,"Confirm Successfully!",Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("Cancel",null).show();

            }

        });
    }

    private void confirmCollect(){
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

        items = new ArrayList<RetrivalItem>();
        JsonArrayRequest jr = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject o = jsonArray.getJSONObject(i);
                        RetrivalItem item =new RetrivalItem();
                        item.setItem_actual(o.getString("item_actual"));
                        item.setItem_code(o.getString("item_code"));
                        item.setItem_desc(o.getString("item_desc"));
                        item.setItem_qty(o.getString("item_qty"));
                        items.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RetrivalListAdapter adapter = new RetrivalListAdapter(RetrievalList.this,items);
                list.setAdapter(adapter);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RetrivalItem ri = (RetrivalItem) parent.getAdapter().getItem(position);
                        System.out.print(ri.getItem_code());
                        Intent i = new Intent(view.getContext(), RetrievalListDetail.class);
                        i.putExtra("retrivalitem", ri);
                        startActivity(i);
                    }
                });
                if (jsonArray.length() == 0){

                    Toast.makeText(RetrievalList.this,"No retrieval form to be confirmed!",Toast.LENGTH_SHORT).show();
                    btn_confirmCollect.setBackgroundColor(Color.LTGRAY);
                }


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
       return jr;
    }

}

