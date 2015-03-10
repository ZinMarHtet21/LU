package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.ProcessRequisitionAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProcessRequisitions extends ActionBarActivity {

    IItem itemPopulator;
    private RequestQueue mRequestQueue;
    private ArrayList<Item> retrivalItems;
    private String url = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_requisitions);
        itemPopulator = new ItemPopulator();
        mRequestQueue = Volley.newRequestQueue(this);
        final ListView list = (ListView) findViewById(R.id.item_list);


        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    retrivalItems = itemPopulator.populatePendingProcessedItem(response.getJSONObject("photos").getJSONArray("photo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(retrivalItems.get(1).toString());
//                SimpleAdapter adapter = new SimpleAdapter(ProcessRequisitions.this,
//                        retrivalItems, R.layout.row_retrivalitem,
//                        new String[]{"description", "qty"},
//                        new int[]{R.id.description, R.id.qty});
                ProcessRequisitionAdapter adapter = new ProcessRequisitionAdapter(ProcessRequisitions.this, retrivalItems);
                list.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG,error.getMessage());
            }
        });
        mRequestQueue.add(jr);


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
}
