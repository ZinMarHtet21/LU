package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Department;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by student on 8/3/15.
 */
public class DepartmentDisbursementListPopulator implements IDepartmentDisbursementList {
    @Override
    public ArrayList<DepartmentDisbursementList> populateDepartmentDisbursementList(JSONArray ja) {
        ArrayList <DepartmentDisbursementList> list = new ArrayList<DepartmentDisbursementList>();
        for (int i = 0; i<ja.length();i++){
            try {
                JSONObject jo = ja.getJSONObject(i);
                DepartmentDisbursementList ddl = new DepartmentDisbursementList();
                //department info
                Department department = new Department();
                department.setName("departmentName");
                department.setRepresentative("RepresentativeName");
                department.setCollection_point("collection point");
                ddl.setDepartment(department);
                //department related disbursement
                ArrayList <Disbursement> disbursementlist = new ArrayList<Disbursement>();
                for (int j = 0; j < 100; j++) {
                    Disbursement disbursement = new Disbursement();
                    disbursement.setItemName("item name");
                    disbursement.setQty(2);
                    disbursement.setActualQty(4);
                    disbursementlist.add(disbursement);
                }


                ddl.setDisbursementList(disbursementlist);
                list.add(ddl);
                // list.add(new (jo.getString("id"),jo.getString("server"),jo.getString("server")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
