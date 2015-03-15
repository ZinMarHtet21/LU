package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

import java.io.Serializable;

/**
 * Created by student on 4/3/15.
 */
public class Employee  implements Serializable {
    private int id;
    private String type;
    private String name;
    private String gender;
    private String emp_number;
    private String email;
    private String phone;
    private String password;
    private int departmentID;


    public Employee() {
    }

    public Employee(int id, String type, String name, String emp_number, String email, String phone, String password, int departmentID) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.emp_number = emp_number;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.departmentID = departmentID;
    }

    public Employee(int id, String type, String name, String gender, String emp_number, String email, String phone, String password, int departmentID) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.gender = gender;
        this.emp_number = emp_number;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.departmentID = departmentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", emp_number='" + emp_number + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", departmentID='" + departmentID + '\'' +
                '}';
    }

}
