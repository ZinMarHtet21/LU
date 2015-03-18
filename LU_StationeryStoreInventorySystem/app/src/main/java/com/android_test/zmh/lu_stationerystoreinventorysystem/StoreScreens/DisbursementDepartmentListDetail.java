package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter.DisbursementDepratmentListDetailAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DepartmentDisbursementListPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;

public class DisbursementDepartmentListDetail extends ActionBarActivity {
   private DepartmentDisbursementList ddl;
    private  RequestQueue mRequestQueue ;
    private  String url = UrlManager.APIROOTURL+"disbursementlistApi/getdepartment/";
    private IDepartmentDisbursementList idl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_disbursement_department_list_detail);
        idl = new DepartmentDisbursementListPopulator();
        final ListView list = (ListView) findViewById(R.id.itemlist);
        TextView text_depratmentName = (TextView) findViewById(R.id.departmentName);
        Intent i = getIntent();
        ddl = (DepartmentDisbursementList) i.getExtras().getSerializable("disbursementlist");
       String path = ddl.getDepartment().getCode();

        text_depratmentName.setText(ddl.getDepartment().getCode());


        JsonArrayRequest jar = new JsonArrayRequest(url+path,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    ddl.setDisbursementList(idl.populateDisbursementForADepartment(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                DisbursementDepratmentListDetailAdapter adapter = new DisbursementDepratmentListDetailAdapter(DisbursementDepartmentListDetail.this, ddl);
                list.setAdapter(adapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(jar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disbursement_department_list_detail, menu);
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
