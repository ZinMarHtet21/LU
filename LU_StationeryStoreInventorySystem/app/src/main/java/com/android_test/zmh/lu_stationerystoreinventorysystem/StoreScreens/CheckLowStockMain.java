package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.LowStockListAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.StockItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CheckLowStockMain extends ActionBarActivity implements AdapterView.OnItemClickListener {

    IItem itemPopulator;
    TabHost th;
    private RequestQueue mRequestQueue;
    private ArrayList<Item> stocklist;
    private String url = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean useAsync = true;
        itemPopulator = new ItemPopulator();
        setContentView(R.layout.activity_check_low_stock_main);
        mRequestQueue = Volley.newRequestQueue(this);

        final ArrayList<StockItem> photos = new ArrayList<StockItem>();
        final ListView list = (ListView) findViewById(R.id.lowstock_list);
        final ListView list2 = (ListView) findViewById(R.id.allstock_list);

        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();

        TabHost.TabSpec ts = th.newTabSpec("lowStockItems");
        ts.setContent(R.id.lowstock_list);
        ts.setIndicator("Low Stock Items");
        th.addTab(ts);

        ts = th.newTabSpec("allStockItems");
        ts.setContent(R.id.allstock_list);
        ts.setIndicator("All Stock Items");
        th.addTab(ts);

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    stocklist = itemPopulator.populateItemList(response.getJSONObject("photos").getJSONArray("photo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(stocklist.get(1).getBalance());
                LowStockListAdapter adapter = new LowStockListAdapter(CheckLowStockMain.this, stocklist);
                list.setAdapter(adapter);
                list2.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG, error.getMessage());
            }
        });

        mRequestQueue.add(jr);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getAdapter().getItem(position);
                Intent i = new Intent(view.getContext(), CheckLowStockSearch.class);
                i.putExtra("from", 2);
                i.putExtra("item", item);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_low_stock_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // int id = item.getItemId();
//
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, CheckLowStockSearch.class);
                startActivity(i);
        }
//
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StockItem s = (StockItem) parent.getAdapter().getItem(position);
        System.out.println(s.getDescription());
        System.out.println("a");


    }
}
