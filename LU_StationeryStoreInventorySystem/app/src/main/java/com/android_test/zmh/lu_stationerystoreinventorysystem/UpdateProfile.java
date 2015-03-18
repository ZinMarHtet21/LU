package com.android_test.zmh.lu_stationerystoreinventorysystem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.DepartmentPopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.EmployeePopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Network.CallWebApi;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UpdateProfile extends Activity {

    EmployeePopulator empPopulator;
    DepartmentPopulator deptPopulator;
    Employee emp = new Employee();
    int dept_id;

    String selectedDeptName;
    String dept_name;

    int emp_id;
    String emp_name;
    String emp_type;
    String emp_no;
    String emp_ph;
    String emp_email;
    int emp_deptID;

    String jsonString;
    String url;

    EditText e_no;
    EditText e_type;
    EditText e_ph;
    EditText e_email;
    EditText e_name;
    Button updateBtn;
    Button changePwdBtn;
    String jsonUpdateResult = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        empPopulator = new EmployeePopulator();
        deptPopulator = new DepartmentPopulator();

        e_no = (EditText) findViewById(R.id.upd_empNumber_txt);
        e_name = (EditText) findViewById(R.id.upd_empName_txt);
        e_type = (EditText) findViewById(R.id.upd_empType_txt);
        e_ph = (EditText) findViewById(R.id.upd_empPh_txt);
        e_email = (EditText) findViewById(R.id.upd_empEmail_txt);
        updateBtn = (Button) findViewById(R.id.button_update_profile);
        changePwdBtn = (Button)findViewById(R.id.button_change_pwd);

        e_no.setText(MainActivity.emp.getEmp_number());
        e_name.setText(MainActivity.emp.getName());
        e_type.setText(MainActivity.emp.getType());
        e_ph.setText(MainActivity.emp.getPhone());
        e_email.setText(MainActivity.emp.getEmail());

        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // collect all the values from the edit text...
                emp_id = MainActivity.emp.getId();
                emp_type = e_type.getText().toString();
                emp_name = e_name.getText().toString();
                emp_no = e_no.getText().toString();
                emp_email = e_email.getText().toString();
                emp_ph = e_ph.getText().toString();


                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        jsonUpdateResult = empPopulator.updateEmployeeProfile(new Employee(emp_id, emp_type, emp_name, emp_no, emp_email, emp_ph));
                        return jsonUpdateResult;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if(!result.equals(null)){
                            MainActivity.emp.setId(emp_id);
                            MainActivity.emp.setType(emp_type);
                            MainActivity.emp.setEmp_number(emp_no);
                            MainActivity.emp.setName(emp_name);
                            MainActivity.emp.setEmail(emp_email);
                            MainActivity.emp.setPhone(emp_ph);
                            finish();
                            Toast.makeText(UpdateProfile.this,"Update Profile Successfully!",Toast.LENGTH_SHORT).show();
                        }

                    }

                }.execute();
            }



        });

        changePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateProfile.this, ChangePassword.class));
            }
        });


    }
}








