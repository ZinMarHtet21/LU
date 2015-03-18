package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        String reason1;
        String text;
        String approveUrl = UrlManager.APIROOTURL +"purchase_orderApi/approve";
        private RequestQueue mRequestQueue;
        String poNumber;
        private Spinner spinner;
        private EditText et;
        private Button btn;
        private AlertDialog.Builder builder;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_reason);
         et = (EditText) findViewById(R.id.et_reason);
         spinner = (Spinner)findViewById(R.id.spinner);
         btn =(Button) findViewById(R.id.btnSave);

         ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons,android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
         spinner.setOnItemSelectedListener(this);

         mRequestQueue = Volley.newRequestQueue(this);
         builder = new AlertDialog.Builder(this);
         builder.setTitle("Tooltip");
         builder.setMessage("Are you sure to approve/reject it?");
         builder.setIcon(R.drawable.message);
         builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {

          @Override


           public void onClick(DialogInterface dialog, int which) {
              Intent intent = new Intent(RejectReason_order.this,SupervisorMainScreen.class);
              if(reason.equals("reason")&&text!="")
                  reason1 =text;
              else if(reason.equals("reason")&&text.equals(""))
              { et.setError("Please give a reason...");
                  return;}

              else
              {reason1 = reason;
                  Toast.makeText(getApplicationContext(), " Purchase order: " + poNumber + " has been rejected!",
                          Toast.LENGTH_LONG).show();}



                    startActivity(intent);
                }
            });


            builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Cancel it!",
                            Toast.LENGTH_LONG).show();
                }
            });



            if (getIntent()!= null) {

                poNumber = getIntent().getSerializableExtra("Po").toString();

            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    text=et.getText().toString();
                    Map<String,String > map = new HashMap<String, String>();
                    map.put("orderID" ,poNumber);
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
                    builder.show();


                }
            });




    }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            reason = parent.getItemAtPosition(position).toString();


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {






        }


    }
