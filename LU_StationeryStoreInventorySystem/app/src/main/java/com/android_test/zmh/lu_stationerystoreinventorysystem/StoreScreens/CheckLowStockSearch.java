package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.ItemPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CheckLowStockSearch extends ActionBarActivity {
    private RequestQueue mRequestQueue;
    private IItem itemPopulator;
    private String categoriesUrl = UrlManager.APIROOTURL+"itemApi/categories";
    private String itemsUrl = UrlManager.APIROOTURL+"itemApi/category/";
    private String itemDetailUrl = UrlManager.APIROOTURL+"itemApi/item";
    private String supplierlUrl = UrlManager.APIROOTURL+"itemApi/suppliers/";
    private String reportUrl = UrlManager.APIROOTURL+"stockAdjustmentApi/create";
    private Spinner spinner_category;
    private Spinner spinner_description;
    private TextView text_item;
    private TextView text_uom;
    private TextView text_reorderlevel;
    private TextView text_balance;
    private Button btn_submit;
    private EditText et_newBalance;
    private EditText et_reason;



  //  private String[] category = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj"};
    private String[] categories;
    private String[] items;
    private String[] suppliers;
    private Item i;
    private ArrayAdapter<String> categoryAdapter = null;
    private ArrayAdapter<String> itemsAdapter = null;
    private String selectedSupplier;




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
            System.out.print(i.getId());
            this.setTitle(i.getDescription());
            text_item.setText(i.getDescription());
            text_uom.setText(i.getUom());
            text_reorderlevel.setText(String.valueOf(i.getReorderLevel()));
            text_balance.setText(String.valueOf(i.getBalance()));
            setButtonOnclickListener();
        } else {
            getCategoryList();
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
        text_item = (TextView) findViewById(R.id.text_item);
        text_uom = (TextView) findViewById(R.id.text_uom);
        text_reorderlevel = (TextView) findViewById(R.id.text_reorderlevel);
        text_balance = (TextView) findViewById(R.id.text_balance);
        btn_submit = (Button) findViewById(R.id.submit);
        et_newBalance = (EditText) findViewById(R.id.newbalance);
        et_reason = (EditText) findViewById(R.id.reason);

    }

    public void getCategoryList() {

        JsonArrayRequest jar  = new JsonArrayRequest(categoriesUrl,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                ArrayList<String> a = new ArrayList<String>();
                for (int i=0;i<jsonArray.length();i++){

                    try {
                        a.add(jsonArray.getJSONObject(i).getString("category_name"));
                        System.out.println(jsonArray.getJSONObject(i).getString("category_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                categories = new String[a.size()];
                categories  = a.toArray(categories);
//                for (int j = 0; j<categories.length;j++){
//                    System.out.println(categories[j]);
//                }
                categoryAdapter = new ArrayAdapter<String>(CheckLowStockSearch.this,
                        android.R.layout.simple_dropdown_item_1line,categories);
                spinner_category.setAdapter(categoryAdapter);
                setSpinnerOnSelectedListener();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(jar);


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
                System.out.println("aa");

                // getItemDetail(parent.getAdapter().getItem(position).toString());
                getItemDetail(parent.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setButtonOnclickListener();


    }

    public void setButtonOnclickListener(){

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(et_reason.getText().toString().trim()) || "".equals(et_newBalance.getText().toString().trim())){

                    Toast.makeText(CheckLowStockSearch.this,"Please Sumbit new balance and reason ^ ^",Toast.LENGTH_SHORT).show();

                } else {
                    getSuppliersAndConfirmSubmit();
                }
            }
        });
    }


    public void getItemList(final String category) {

        JsonArrayRequest jar  = new JsonArrayRequest(itemsUrl+category,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                ArrayList<String> a = new ArrayList<String>();
                for (int i=0;i<jsonArray.length();i++){

                    try {
                        a.add(jsonArray.getJSONObject(i).getString("item_description"));
                        System.out.println(jsonArray.getJSONObject(i).getString("item_description"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                items = new String[a.size()];
                items  = a.toArray(items);
                for (int j = 0; j<items.length;j++){
                    System.out.println(items[j]);
                }
                itemsAdapter = new ArrayAdapter<String>(CheckLowStockSearch.this,
                        android.R.layout.simple_dropdown_item_1line,items);
                spinner_description.setAdapter(itemsAdapter);
              //  setSpinnerOnSelectedListener();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(jar);
    }

    public void getItemDetail(final String description) {

        Map<String,String> a = new HashMap<String,String>();
        a.put("description",description);
        JSONObject jo = new JSONObject(a);

        JsonRequest<JSONObject> jr = new JsonObjectRequest(Request.Method.POST, itemDetailUrl, jo,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.print(response);
                try {
                    i = itemPopulator.populateItemDetail(response);
                    text_item.setText(i.getDescription());
                    text_uom.setText(i.getUom());
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
        }){

        };

        mRequestQueue.add(jr);
    }


    public void getSuppliersAndConfirmSubmit() {

        JsonArrayRequest jar  = new JsonArrayRequest(supplierlUrl+i.getId(),new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                System.out.print("supplier"+jsonArray);

        if (jsonArray.length() == 0 ){
            Toast.makeText(CheckLowStockSearch.this,"Sorry,this item has no supplier.",Toast.LENGTH_SHORT).show();
            //System.out.print("hehe");
            }else {
            ArrayList<String> a = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    a.add(jsonArray.getJSONObject(i).getString("supplier_name"));
                    System.out.println(jsonArray.getJSONObject(i).getString("supplier_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            suppliers = new String[a.size()];
            suppliers = a.toArray(suppliers);
            selectedSupplier = suppliers[0];
            System.out.println(selectedSupplier);


            new AlertDialog.Builder(CheckLowStockSearch.this).setTitle("Please Select A Supplier").setSingleChoiceItems(suppliers, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    selectedSupplier = suppliers[which];


                }
            }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    confirmSubmit();

                }
            }).setNegativeButton("Cancel", null).show();
        }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.networkResponse);

            }
        });


        mRequestQueue.add(jar);
    }

    private void confirmSubmit(){
        System.out.print("hehe");
        Map<String, String> params = new HashMap<String, String>();
        params.put("itemDescription", i.getDescription());
        params.put("newBalance", et_newBalance.getText().toString());
        params.put("reason", et_reason.getText().toString());
        params.put("suppliername",selectedSupplier);
        JSONObject jo = new JSONObject(params);

        System.out.print(jo);

        JsonRequest<JSONObject> postRequest = new JsonObjectRequest(Request.Method.POST, reportUrl,jo,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.print(response);
                        Toast.makeText(CheckLowStockSearch.this, "Submit Successfully!", Toast.LENGTH_LONG).show();
                        et_newBalance.setText("");
                        et_reason.setText("");
                     //   Log.v("Response", String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckLowStockSearch.this, "Error, Please Try Again!", Toast.LENGTH_LONG).show();
                    }
                }
        ) {

        };
        mRequestQueue.add(postRequest);

    }




}
