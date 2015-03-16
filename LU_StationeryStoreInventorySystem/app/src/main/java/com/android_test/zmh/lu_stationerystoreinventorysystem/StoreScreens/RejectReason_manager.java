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
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.ManagerMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.SupervisorMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 14/3/15.
 */
public class RejectReason_manager extends Activity implements AdapterView.OnItemSelectedListener

{
    String reason;
    String reason1;
    String text;
    String approveUrl = UrlManager.APIROOTURL +"stockAdjustmentApi/approve";
    private RequestQueue mRequestQueue;
    String voucherId;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private Button btn;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_reason);
        et = (EditText) findViewById(R.id.et_reason);
        btn =(Button) findViewById(R.id.btnSave);
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.reasons,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        if (getIntent()!= null) {

            voucherId = getIntent().getSerializableExtra("Voucher").toString();

        }



        mRequestQueue = Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = et.getText().toString().trim();

                if(reason.equals("reason")&&text!="")
                    reason1 =text;
                else if(reason.equals("reason")&&text.equals(""))
                { et.setError("Please give a reason...");
                    return;}

                else
                {reason1 = reason;
                    Toast.makeText(getApplicationContext(), " Voucher# "+voucherId+" has been rejected!",
                            Toast.LENGTH_LONG).show();}



                Map<String,String > map = new HashMap<String, String>();
                map.put("voucherID" ,voucherId);
                map.put("outcome" ,"reject");
                map.put("remark" ,reason1);
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
                Intent i = new Intent(RejectReason_manager.this, ManagerMainScreen.class);

                startActivity(i);
            }
        });



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        reason = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this,"You select"+reason, Toast.LENGTH_LONG).show();
        System.out.println(reason);

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // reason = text;

    }
}

