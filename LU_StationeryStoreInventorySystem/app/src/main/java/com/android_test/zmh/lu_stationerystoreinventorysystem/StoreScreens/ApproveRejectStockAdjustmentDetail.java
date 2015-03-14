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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.AdjustmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucher;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.AdjustmentVoucherDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApproveRejectStockAdjustmentDetail extends ActionBarActivity {

    AdjustmentVoucher model =new AdjustmentVoucher();
    AdjustmentPopulator ap = new AdjustmentPopulator();
    String baseurl = UrlManager.APIROOTURL + "stockAdjustmentApi/detail/";
    String approveUrl = UrlManager.APIROOTURL +"stockAdjustmentApi/approve";
    private RequestQueue mRequestQueue;
    String vi;
    Button btn1;
    Button btn2;
    TextView tv1 ;
    TextView tv2;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_reject_stock_adjustment_detail);
        btn1 = (Button) findViewById(R.id.btApprove);
        btn2 = (Button) findViewById(R.id.btReject);
        tv1 = (TextView) findViewById(R.id.tv_date);
        tv2 = (TextView) findViewById(R.id.voucher_id);
        mRequestQueue = Volley.newRequestQueue(this);
        lv = (ListView) findViewById(R.id.lv_adDetail);


        if (getIntent()!= null) {

            vi= getIntent().getSerializableExtra("VoucherNo").toString();

        }


        new AsyncTask<Void, Void, List<AdjustmentVoucherDetail>>() {
            @Override
            protected List<AdjustmentVoucherDetail> doInBackground(Void... params) {

                List<AdjustmentVoucherDetail> details = ap.populateAdjustmentDetailFromWcf(baseurl + vi);
                model.setAdjustmentVoucherDetails(details);


                return details;

            }

            @Override
            protected void onPostExecute(List<AdjustmentVoucherDetail> adjustmentVoucherDetails) {
                super.onPostExecute(adjustmentVoucherDetails);
                SimpleAdapter mysimpleAdapter = new SimpleAdapter(ApproveRejectStockAdjustmentDetail.this,
                        convertModelToHashMapModel(model),
                        R.layout.row_adjustment_detail,
                        new String[]{"itemName", "qty"},
                        new int[]{R.id.tv_itemName,R.id.tv_Qty});
                lv.setAdapter(mysimpleAdapter);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Map<String,String > map = new HashMap<String, String>();
                        map.put("orderId" ,vi);
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
                    }
                });


                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ApproveRejectStockAdjustmentDetail.this, RejectReason_supervisor.class);
                        i.putExtra("Voucher",vi);
                        startActivity(i);


                    }
                });


            }




            //            @Override
//            protected void onPostExecute(List<PurchaseOrderDetail> result) {
//                SimpleAdapter mysimpleAdapter = new SimpleAdapter(ApproveRejectStockAdjustmentDetail.this,
//                        convertModelToHashMapModel(model),
//                        R.layout.row_adjustment_detail,
//                        new String[]{"itemName", "qty"},
//                        new int[]{R.id.tv_itemName,R.id.tv_Qty});
//                lv.setAdapter(mysimpleAdapter);
//                }


        }.execute();
   }




    public ArrayList<hash> convertModelToHashMapModel(AdjustmentVoucher model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (AdjustmentVoucherDetail ad : model.getAdjustmentVoucherDetails()) {


            String qty = ""+ad.getQty();

            hashlist.add(new hash(ad.getItemName(),qty));
            System.out.println(ad.getItemName());
        }
        return hashlist;
    }


    public  class hash extends HashMap<String,String> {
        public hash(String item,String qty) {
            put("itemName", item);
            put("qty", qty);

        }
    }
}
