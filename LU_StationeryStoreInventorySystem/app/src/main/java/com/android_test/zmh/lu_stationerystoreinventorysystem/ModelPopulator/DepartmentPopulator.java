package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDepartment;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Department;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 12/3/15.
 */
public class DepartmentPopulator implements IDepartment {
    public final static String baseurl = UrlManager.APIROOTURL;
    public final static String departmentURL = baseurl + "departmentsApi";

    @Override
    public String getDepartmentName(int deptID) {
        String deptName = null;
        JSONObject obj = JSONParser.getJSONFromUrl(String.format("%s/%s", departmentURL, deptID));
        try{
            deptName = obj.getString("department_name").toString();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return(deptName);
    }

    @Override
    public List<Department> getDepartmentList() {
        List<Department> deptList = new ArrayList<Department>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",departmentURL));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Department dept = new Department();
                dept.setId(obj.getInt("department_id"));
                dept.setCode(obj.getString("department_code").toString());
                dept.setName(obj.getString("department_name").toString());
                dept.setContactNumber(obj.getString("department_tel").toString());
                dept.setFax(obj.getString("department_fax").toString());
                dept.setRepresentative(obj.getString("department_contact_name").toString());
//                int collectionPointID = obj.getInt("");
                //dept.setCollection_point(obj.getString("").toString());
                deptList.add(dept);
            }

        } catch (Exception e) {
            Log.e("Department List", "JSONArray error");
        }
        return deptList;
    }

    @Override
    public List<String> getDepartmentNameList() {
        List<String> deptNameList = new ArrayList<String>();
        JSONArray arr = JSONParser.getJSONArrayFromUrl(String.format("%s",departmentURL));

        try {

            for (int i =0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                deptNameList.add(obj.getString("department_name").toString());
            }

        } catch (Exception e) {
            Log.e("Department Name list", "JSONArray error");
        }
        return deptNameList;
    }




}
