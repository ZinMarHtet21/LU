package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.SupervisorMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.PurchaseOrderPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApproveRejectPurchaseOrderDetail extends ActionBarActivity {

    double amount=0;
    PurchaseOrder model = new PurchaseOrder();

    PurchaseOrderPopulator pop = new PurchaseOrderPopulator();

    String baseurl = UrlManager.APIROOTURL + "purchase_orderApi/detail/";
    String approveUrl = UrlManager.APIROOTURL +"purchase_orderApi/approve";
    String po;
    private RequestQueue mRequestQueue;
    Button btn1;
    Button btn2;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_purchase_order_detail);
        final ListView lv = (ListView) findViewById(R.id.lv_sorder);

        tv2 = (TextView) findViewById(R.id.Amount);
        btn1 = (Button) findViewById(R.id.btnApprove);
        btn2 = (Button) findViewById(R.id.btnReject);
        mRequestQueue = Volley.newRequestQueue(this);

        if (getIntent()!= null) {

            po = getIntent().getSerializableExtra("PO_Id").toString();

        }


        new AsyncTask<Void, Void, List<PurchaseOrderDetail>>() {
            @Override
            protected List<PurchaseOrderDetail> doInBackground(Void... params) {

                List<PurchaseOrderDetail> details = pop.PopulatePurchaseOrderDetailFromWcf(baseurl+po );
                model.setPurchaseOrderDetail(details);

                return details;

            }

            @Override
            protected void onPostExecute(List<PurchaseOrderDetail > result) {
                SimpleAdapter mysimpleAdapter = new SimpleAdapter(ApproveRejectPurchaseOrderDetail.this,
                        convertModelToHashMapModel(model),
                        R.layout.row_orderdetail,
                        new String[]{"itemName", "qty", "price"},
                        new int[]{R.id.itemName,R.id.qty,R.id.price});
                lv.setAdapter(mysimpleAdapter);
                tv2.setText(String.valueOf(amount));



                btn1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                               Map<String,String > map = new HashMap<String, String>();
                               map.put("orderID" ,po);
                               map.put("outcome" ,"approve");
                               map.put("remark" ,"3");
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
                       Intent intent = new Intent(ApproveRejectPurchaseOrderDetail.this,SupervisorMainScreen.class);
                       //intent.putExtra("status",response);
                       Toast.makeText(getApplicationContext(), " Purchase order : " + po + " has been approved!",
                               Toast.LENGTH_LONG).show();
                       startActivity(intent);



                           }
               });


            }
        }.execute();



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApproveRejectPurchaseOrderDetail.this, RejectReason_order.class);
                i.putExtra("Po",po);
                startActivity(i);


            }
        });
    }
        /*if (getIntent()!= null) {
            model = (PurchaseOrder) getIntent().getSerializableExtra("PurchaseOrder");
        }


        ListView lv = (ListView) findViewById(R.id.lv_sorder);
        SimpleAdapter mysimpleAdapter = new SimpleAdapter(this, convertModelToHashMapModel(model),
                R.layout.row_orderdetail,
                new String[]{"itemName", "qty", "price"},
                new int[]{R.id.itemName,R.id.qty,R.id.price});
        lv.setAdapter(mysimpleAdapter);

        TextView tv1 = (TextView) findViewById(R.id.poNumber);
        TextView tv2 = (TextView) findViewById(R.id.Amount);
        Button btn1 = (Button) findViewById(R.id.btnApprove);
        Button btn2 = (Button) findViewById(R.id.btnReject);
        tv1.setText(model.getNumber());
        tv2.setText(String.valueOf(amount));


    }*/

    public ArrayList<hash> convertModelToHashMapModel(PurchaseOrder model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (PurchaseOrderDetail pd : model.getPurchaseOrderDetail()) {

            amount=amount+pd.getPrice()*pd.getQty();
            String qty = ""+pd.getQty();
            String price = ""+pd.getPrice();
            hashlist.add(new hash(pd.getItemName(),qty,price));
        }
        return hashlist;

    }


    public  class hash extends HashMap<String,String> {
        public hash(String item,String qty,String price) {
            put("itemName", item);
            put("qty", qty);
            put("price",price);
        }
    }
}
