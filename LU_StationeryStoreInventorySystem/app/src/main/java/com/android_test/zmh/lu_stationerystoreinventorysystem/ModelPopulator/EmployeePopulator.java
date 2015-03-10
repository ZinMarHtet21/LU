package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IEmployee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class EmployeePopulator implements IEmployee {

    @Override
    public List<Employee> PopulateEmployee() {
        // popluate the employee object ,
        // Add it to the list
       List<Employee> emp_list = new LinkedList<Employee>();
        for (int i=0;i<10;i++) {
            Employee e = new Employee("1", "test", "test", "test", "test", "test", "test", "test", "test");
            emp_list.add(e);
        }
        return emp_list;
    }

    @Override
    public List<Employee> PopulateEmployeeFromWcf() {
        return null;
    }

}
