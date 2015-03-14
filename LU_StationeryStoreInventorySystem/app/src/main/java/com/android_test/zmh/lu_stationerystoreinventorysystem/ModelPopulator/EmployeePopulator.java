package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IEmployee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class EmployeePopulator implements IEmployee {

    public final static String baseurl = UrlManager.APIROOTURL;
    String empURL = baseurl+"employeeApi";

    @Override
    public List<Employee> PopulateEmployee() {
        // popluate the employee object ,
        // Add it to the list
       List<Employee> emp_list = new LinkedList<Employee>();
        for (int i=0;i<10;i++) {
            Employee e = new Employee();
            emp_list.add(e);
        }
        return emp_list;
    }

    @Override
    public Employee populateEmployeByUname(String name) {
        Employee emp=null;
            JSONObject result = JSONParser.getJSONFromUrl(String.format("%s/%s", empURL, name));

            try {
                JSONObject obj = result;
                    emp = new Employee(
                            obj.getInt("employee_id"),
                            obj.getString("employee_type").toString(),
                            obj.getString("employee_name").toString(),
                            obj.getString("employee_gender").toString(),
                            obj.getString("employee_number").toString(),
                            obj.getString("employee_email").toString(),
                            obj.getString("employee_phone").toString(),
                            obj.getString("employee_password").toString(),
                            obj.getInt("department_department_id"));

            } catch (Exception e) {
                Log.e("list", "JSONArray error");
            }

        return  emp;
    }

    @Override
    public List<Employee> PopulateEmployeeFromWcf() {

        return null;
    }

    @Override
    public String convertToJSONObj(Employee emp) {
        System.out.println("EMPLOYEE OBJECT <=======");
        System.out.println("========================");
        System.out.println(emp.toString());
        Gson gson = new Gson();
        String jsonString = gson.toJson(emp, emp.getClass());
        return jsonString;
    }

}
