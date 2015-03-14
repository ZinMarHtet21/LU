package com.android_test.zmh.lu_stationerystoreinventorysystem.StoreScreens;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.PurchaseOrderPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrder;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.PurchaseOrderDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PurchaseOrderDetailUI extends ActionBarActivity {

    PurchaseOrder model ;
    PurchaseOrderPopulator pop = new PurchaseOrderPopulator();
    String baseurl = UrlManager.APIROOTURL + "purchase_orderApi/detail/";
    String po;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order_detail);
        model=new PurchaseOrder();
        lv = (ListView) findViewById(R.id.lv_OrderDetail);

        if (getIntent()!= null) {
            po = getIntent().getSerializableExtra("PurchaseOrderId").toString();
           // po=getIntent().getSerializableExtra("PONumber").toString();
            //model = (PurchaseOrder) getIntent().getSerializableExtra("PurchaseOrderId");
            //po=model.getId();
        }

//convert model to hash map
        //ListView lv = (ListView) findViewById(R.id.lv_OrderDetail);
        new AsyncTask<Void, Void, List<PurchaseOrderDetail>>() {
            @Override
            protected List<PurchaseOrderDetail> doInBackground(Void... params) {

                List<PurchaseOrderDetail> details = pop.PopulatePurchaseOrderDetailFromWcf(baseurl+po);
                model.setPurchaseOrderDetail(details);

                return details;

            }

        @Override
        protected void onPostExecute(List<PurchaseOrderDetail > result) {
            SimpleAdapter mysimpleAdapter = new SimpleAdapter(PurchaseOrderDetailUI.this,
                    convertModelToHashMapModel(model),
                    R.layout.row_orderdetail,
                    new String[]{"itemName", "qty", "price"},
                    new int[]{R.id.itemName,R.id.qty,R.id.price});
            lv.setAdapter(mysimpleAdapter);

        }
       }.execute();
    }


    /*SimpleAdapter mysimpleAdapter = new SimpleAdapter(this, convertModelToHashMapModel(model),
                R.layout.row_orderdetail,
                new String[]{"itemName", "qty", "price"},
                new int[]{R.id.itemName,R.id.qty,R.id.price});
        lv.setAdapter(mysimpleAdapter);
*/


    public ArrayList<hash> convertModelToHashMapModel(PurchaseOrder model) {
        ArrayList<hash> hashlist = new ArrayList<hash>();
        for (PurchaseOrderDetail pd : model.getPurchaseOrderDetail()) {
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
