package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.DisbursementDepratmentListAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DepartmentDisbursementListPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;

import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisbursementDepartmentList extends ActionBarActivity {
    private IDepartmentDisbursementList departmentDisbursementListPopulator;
    private RequestQueue mRequestQueue;
    private ArrayList<DepartmentDisbursementList> disbursementDepartmentList;
    private String url = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=7c5c19eba3c21dc8bb16f00829b2be41&date=2015-02-09&format=json&nojsoncallback=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        departmentDisbursementListPopulator = new DepartmentDisbursementListPopulator();
        setContentView(R.layout.activity_disbursement_department_list);


        mRequestQueue = Volley.newRequestQueue(this);
        final ListView list = (ListView) findViewById(R.id.departmentlist);

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    disbursementDepartmentList = departmentDisbursementListPopulator.populateDepartmentDisbursementList(response.getJSONObject("photos").getJSONArray("photo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(disbursementDepartmentList.get(1).toString());
//                SimpleAdapter adapter = new SimpleAdapter(DisbursementDepartmentList.this,
//                        disbursementDepartmentList, R.layout.row_disbursement_department,
//                        new String[]{"name", "representative","collectionpoint"},
//                        new int[]{R.id.department, R.id.representative,R.id.collectionpoint});
                DisbursementDepratmentListAdapter adapter = new DisbursementDepratmentListAdapter(DisbursementDepartmentList.this, disbursementDepartmentList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DepartmentDisbursementList dd = (DepartmentDisbursementList) parent.getAdapter().getItem(position);
                        Intent i = new Intent(view.getContext(), DisbursementDepartmentListDetail.class);
                        i.putExtra("disbursementlist", dd);
                        startActivity(i);

                    }
                });

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
        getMenuInflater().inflate(R.menu.menu_disbursement_department_list, menu);
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
