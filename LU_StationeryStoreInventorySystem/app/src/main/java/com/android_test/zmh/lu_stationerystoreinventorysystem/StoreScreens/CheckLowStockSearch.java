package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CheckLowStockSearch extends ActionBarActivity {
    private RequestQueue mRequestQueue;
    private IItem itemPopulator;
    private String categoryUrl = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";
    private String itemUrl = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";
    private String reportUrl = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";
    private Spinner spinner_category;
    private Spinner spinner_description;
    private TextView text_category;
    private TextView text_description;
    private TextView text_reorderlevel;
    private TextView text_balance;
    private Button btn_submit;
    private EditText et_newBalance;
    private EditText et_reason;


    private String[] category = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj"};
    private Item i;
    private ArrayAdapter<String> categoryAdapter = null;
    private ArrayAdapter<String> descriptionAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        itemPopulator = new ItemPopulator();
        setContentView(R.layout.activity_check_low_stock_search);
        findview();
        Intent intent = getIntent();
        if (intent.getIntExtra("from", 1) == 2) {
            spinner_description.setVisibility(View.INVISIBLE);
            spinner_category.setVisibility(View.INVISIBLE);
            i = (Item) intent.getExtras().getSerializable("item");
            this.setTitle(i.getDescription());
            text_category.setText(i.getCategory());
            text_description.setText(i.getDescription());
            text_reorderlevel.setText(String.valueOf(i.getReorderLevel()));
            text_balance.setText(String.valueOf(i.getBalance()));
        } else {
            categoryAdapter = new ArrayAdapter<String>(CheckLowStockSearch.this,
                    android.R.layout.simple_dropdown_item_1line, category);
            spinner_category.setAdapter(categoryAdapter);
            setSpinnerOnSelectedListener();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_low_stock_search, menu);
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


    public void findview() {
        spinner_category = (Spinner) findViewById(R.id.category);
        spinner_description = (Spinner) findViewById(R.id.description);
        text_category = (TextView) findViewById(R.id.text_category);
        text_description = (TextView) findViewById(R.id.text_description);
        text_reorderlevel = (TextView) findViewById(R.id.text_reorderlevel);
        text_balance = (TextView) findViewById(R.id.text_balance);
        btn_submit = (Button) findViewById(R.id.submit);
        et_newBalance = (EditText) findViewById(R.id.newbalance);
        et_reason = (EditText) findViewById(R.id.reason);

    }

    public void getCategoryList() {

    }

    public void setSpinnerOnSelectedListener() {
        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getItemList(parent.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_description.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // getItemDetail(parent.getAdapter().getItem(position).toString());
                getItemDetail(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("aaa");
                StringRequest postRequest = new StringRequest(Request.Method.POST, reportUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(CheckLowStockSearch.this, "Submit Successfully!", Toast.LENGTH_LONG).show();
                                Log.v("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CheckLowStockSearch.this, "Error, Please Try Again!", Toast.LENGTH_LONG).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("item", text_description.getText().toString());
                        params.put("newBalance", et_newBalance.getText().toString());
                        params.put("Reason", et_reason.getText().toString());

                        return params;
                    }
                };
                mRequestQueue.add(postRequest);
            }
        });

    }


    public void getItemList(final String category) {

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, categoryUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(category);
                System.out.println(response.toString());
                spinner_description.setAdapter(categoryAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG, error.getMessage());
            }
        });

        mRequestQueue.add(jr);
    }

    public void getItemDetail(final int description) {
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, categoryUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    i = itemPopulator.populateItemDetail(response.getJSONObject("photos").getJSONArray("photo").getJSONObject(description));
                    text_category.setText(i.getCategory());
                    text_description.setText(i.getDescription());
                    text_reorderlevel.setText(String.valueOf(i.getReorderLevel()));
                    text_balance.setText(String.valueOf(i.getBalance()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG, error.getMessage());
            }
        });

        mRequestQueue.add(jr);
    }
}
