package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.SupervisorMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 8/3/15.
 */
public class RejectReason_order extends Activity implements AdapterView.OnItemSelectedListener

    {
        String reason;
        String text;
        String approveUrl = UrlManager.APIROOTURL +"purchase_orderApi/approve";
        private RequestQueue mRequestQueue;
        String poNumber;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_reason);
            EditText et = (EditText) findViewById(R.id.et_reason);
            text=et.getText().toString();
            if (getIntent()!= null) {

                poNumber = getIntent().getSerializableExtra("Po").toString();

            }

            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);


            Button btn =(Button) findViewById(R.id.btnSave);
            mRequestQueue = Volley.newRequestQueue(this);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String,String > map = new HashMap<String, String>();
                    map.put("orderId" ,poNumber);
                    map.put("outcome" ,"reject");
                    map.put("remark" ,reason);
                    map.put("approvedby" ,"27");
                    final JSONObject jsonobject = new JSONObject(map);


                    JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,approveUrl,jsonobject,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            System.out.println(jsonObject);
                        }

                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }) {

                    };

                    mRequestQueue.add(jsonRequest);

                    Intent i = new Intent(RejectReason_order.this, SupervisorMainScreen.class);
                    Toast.makeText(getApplicationContext(), " Voucher#" + poNumber + "has been rejected!",
                            Toast.LENGTH_LONG).show();

                    startActivity(i);
                }
            });




    }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            reason = parent.getItemAtPosition(position).toString();
           // System.out.println(reason);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {


                reason = text;
            //System.out.println(reason);



        }


    }
