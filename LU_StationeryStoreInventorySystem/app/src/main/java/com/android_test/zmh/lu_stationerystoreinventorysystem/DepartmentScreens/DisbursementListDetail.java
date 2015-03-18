package com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DisbursementPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.RequisitionPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DisbursementListDetail extends ActionBarActivity {

    DisbursementPopulator disPopulator = new DisbursementPopulator();
    String baseurl = UrlManager.APIROOTURL + "disbursement_listApi/";

    String disb_id;
    Disbursement model;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list_detail);
        lv = (ListView) findViewById(R.id.listView_disbDetails);
        model = new Disbursement();

        if (getIntent() != null) {
            disb_id = getIntent().getStringExtra("disb_id").toString();
        }
    }
}
