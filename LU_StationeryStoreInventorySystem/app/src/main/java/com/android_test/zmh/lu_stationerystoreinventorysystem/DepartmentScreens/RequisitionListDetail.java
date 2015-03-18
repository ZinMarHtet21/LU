package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequisitionListDetail extends ActionBarActivity implements View.OnClickListener {

    final Context context = this;
    RequisitionPopulator reqPopulator = new RequisitionPopulator();
    String baseurl;
    String remark = null;
    String outcome = null;
    String jsonUpdateResult = null;
    int emp_id;
    String req_id;
    String req_date;
    Requisition model;
    ListView lv;
    Button approve_btn, reject_btn;
    TextView req_list_date;
    SimpleAdapter mysimpleAdapter;
    Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_list_detail);
        lv = (ListView) findViewById(R.id.listView_ReqDetails);
        approve_btn = (Button)findViewById(R.id.button_approve);
        reject_btn = (Button)findViewById(R.id.button_reject);
        req_list_date = (TextView)findViewById(R.id.req_list_date);


        model = new Requisition();
        Bundle extras = getIntent().getExtras();
        if(getIntent()!= null) {
            req_id = extras.getString("req_id").toString();
            req_date = getIntent().getStringExtra("req_date").toString();
            String dateText = "Date : " + req_date;
            req_list_date.setText(dateText);
            baseurl = UrlManager.APIROOTURL + "requisition_detailApi/new/";
        }

        //model.setRequisitionDetails();
        // creat a new async task to pull all the requisition details..
        new AsyncTask<Void, Void, List<RequisitionDetail>>() {
            @Override
            protected List<RequisitionDetail> doInBackground(Void... params) {

                List<RequisitionDetail> listdetail = reqPopulator.getRequisitionDetail(req_id, baseurl);
                model.setRequisitionDetails(listdetail);
                return listdetail;

            }
            @Override
            protected void onPostExecute(List<RequisitionDetail> result) {

                myadapter = new Myadapter(RequisitionListDetail.this,result);
                lv.setAdapter(myadapter);
            }
        }.execute();

       approve_btn.setOnClickListener(this);
       reject_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_approve:{
                outcome = "Approved";
                sendRequisitionRespond(outcome);
                break;
            }

            case R.id.button_reject:{
                outcome = "Rejected";
                showPopUpForRemark();
                break;
            }
        }
        myadapter.notifyDataSetChanged();
    }

    private void sendRequisitionRespond(final String outcome) {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                jsonUpdateResult = reqPopulator.sendRequisitionResponseByHOD(req_id, remark, outcome);
                return jsonUpdateResult;
            }

            @Override
            protected void onPostExecute(String result) {
                System.out.println("response got from approve reject req");
                if(!result.equals(null)){
                    finish();
                    Toast.makeText(RequisitionListDetail.this,"Approve Requisition Successfully!",Toast.LENGTH_LONG).show();
                }else{
                    finish();
                    Toast.makeText(RequisitionListDetail.this,"Reject Requisition Successfully!",Toast.LENGTH_LONG).show();
                }
            }

        }.execute();

    }

    private void showPopUpForRemark() {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_remark, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Remark");

        final EditText remarkET = (EditText) promptsView
                .findViewById(R.id.remark_et);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        remark = remarkET.getText().toString();
                        sendRequisitionRespond(outcome);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                sendRequisitionRespond(outcome);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public class Myadapter extends BaseAdapter {

        // starts here...
        List<RequisitionDetail> list = new ArrayList<RequisitionDetail>();
        Context ctx;
        LayoutInflater inflater;

        public Myadapter(Context contxt, List<RequisitionDetail> listData) {
            ctx = contxt;
            list = listData;
            inflater = LayoutInflater.from(this.ctx);
        }
        // ends here...

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = inflater.inflate(R.layout.row_items, null, true);
            TextView tv1 = (TextView) v.findViewById(R.id.itemName_txt);
            TextView tv2 = (TextView) v.findViewById(R.id.itemQty_et);

            tv1.setText(list.get(position).getItemName());
            tv2.setText(Integer.toString(list.get(position).getItem_detail_qty()));

            return v;
        }
    }
}