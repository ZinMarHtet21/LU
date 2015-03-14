package com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Department;

import java.util.List;

/**
 * Created by student on 12/3/15.
 */
public interface IDepartment {
    public String getDepartmentName(int deptID);
    public List<Department> getDepartmentList();
    public List<String> getDepartmentNameList();
    public int getDepartmentID(String deptName);

}
