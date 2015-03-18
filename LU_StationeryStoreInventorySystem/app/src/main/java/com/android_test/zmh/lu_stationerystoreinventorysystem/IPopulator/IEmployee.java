package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;

import org.json.JSONException;

import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public interface IEmployee {

    public List<Employee> PopulateEmployee();

//    public Employee populateEmployeByUname(String empNo, String empPwd);

    public Employee LoginEmployee(String empNo,String empPwd);

//    public Employee getEmployeeObjByEmpNo(String empNo);

//    public DisbursementItemList<Employee> PopulateEmployeeFromWcf();

    //String convertToJSONObj(Employee emp) throws JSONException;

    public String updateEmployeeProfile(Employee emp);

    public String changePassword(int empID, String newPwd);



}
