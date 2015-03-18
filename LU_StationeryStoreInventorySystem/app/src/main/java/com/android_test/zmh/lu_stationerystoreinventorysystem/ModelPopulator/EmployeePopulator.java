package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import android.util.Log;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IEmployee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RequisitionDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
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
    String empGetURL = empURL + "/get";
    String empPostURL = empURL + "/edit";
    String changePwdURL = empURL + "/changepassword";
    String empLoginURL = empURL + "/login";

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
    public Employee LoginEmployee(String empNo, String empPwd) {

        String loginurl = empLoginURL+"?emp_no="+empNo+"&emp_pwd="+empPwd;


            Employee emp=null;
          //  JSONObject result = JSONParser.getJSONFromUrl(String.format("%s/%s/%s", empLoginURL, empNo,empPwd));
         JSONObject result = JSONParser.getJSONFromUrl(loginurl);

        System.out.println("RESULT");
        System.out.println(result);

            try {
                JSONObject obj = result;
                    emp = new Employee(
                    obj.getInt("employee_id"),
                    obj.getString("employee_type").toString(),
                    obj.getString("employee_name").toString(),
                    //obj.getString("employee_gender").toString(),
                    obj.getString("employee_number").toString(),
                    obj.getString("employee_email").toString(),
                    obj.getString("employee_phone").toString(),
                    obj.getString("employee_password").toString(),
                    obj.getInt("department_department_id"));

            } catch (Exception e) {
                Log.e("list", "JSONArray error");
            }

        System.out.println("EMPLOYEE");
        System.out.println(emp);
        return  emp;
    }

    @Override
    public String updateEmployeeProfile(Employee emp) {
        String result = null;
        try{
            String jsonString;
            JSONObject empObj = new JSONObject();
            empObj.put("emp_id",emp.getId());
            empObj.put("emp_type",emp.getType());
            empObj.put("emp_name",emp.getName());
            empObj.put("emp_gender",emp.getGender());
            empObj.put("emp_number",emp.getEmp_number());
            empObj.put("emp_email",emp.getEmail());
            empObj.put("emp_phone",emp.getPhone());
            empObj.put("emp_pwd",emp.getPassword());
//            empObj.put("emp_deptID",emp.getDepartmentID());
            String json = empObj.toString();
            result = JSONParser.postStream(String.format("%s",empPostURL),json);

        }catch(Exception e){
            Log.e("Update Employee Profile","JSON Error");
        }
        return result;
    }

    @Override
    public String changePassword(int empID, String newPwd) {

        String result = null;
        try{
            JSONObject obj = new JSONObject();
            obj.put("emp_id",empID);
            obj.put("new_pwd",newPwd);
            String json = obj.toString();
            result = JSONParser.postStream(String.format("%s",changePwdURL),json);
        }catch(Exception e){
            Log.e("Change Password","JSON Error");
        }
        return result;
    }


}
