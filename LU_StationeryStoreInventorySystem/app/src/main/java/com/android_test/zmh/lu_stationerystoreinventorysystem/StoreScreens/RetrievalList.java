package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_retrieval_list);
        final ListView list = (ListView) findViewById(R.id.listView);
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
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


        mRequestQueue.add(jr);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrieval_list, menu);
        return true;
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
}
