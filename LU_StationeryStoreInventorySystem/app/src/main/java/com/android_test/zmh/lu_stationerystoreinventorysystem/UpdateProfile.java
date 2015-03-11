package com.android_test.zmh.lu_stationerystoreinventorysystem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.EmployeePopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Network.CallWebApi;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;


public class UpdateProfile extends Activity {

    //EditText e_no;
    //String emp_no, emp_name, emp_pwd, emp_ph, emp_email;
    int dept_id;
    Spinner upd_dept_spnr, upd_type_spnr;
    Button updateBtn;
    String dept, type;
    Employee emp = new Employee();
    EmployeePopulator empPop;
    String emp_no,emp_pwd,emp_ph,emp_email,emp_name;
    EditText e_no,e_pwd,e_ph,e_email;
    EditText e_name;
    String josnString;
    String url;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        empPop = new EmployeePopulator();

         e_no = (EditText) findViewById(R.id.upd_empNumber_txt);
         e_pwd = (EditText) findViewById(R.id.upd_empPwd_txt);
         e_ph = (EditText) findViewById(R.id.upd_empPh_txt);
         e_email = (EditText) findViewById(R.id.upd_empEmail_txt);


        e_name = (EditText) findViewById(R.id.upd_empName_txt);


        e_no.setText(MainActivity.emp.getEmp_number());
        e_pwd.setText(MainActivity.emp.getPassword());
         e_ph.setText(MainActivity.emp.getPhone());
         e_email.setText(MainActivity.emp.getEmail());
         e_name.setText(MainActivity.emp.getName());


        upd_dept_spnr = (Spinner) findViewById(R.id.upd_empDept_Spnr);
        upd_type_spnr = (Spinner) findViewById(R.id.upd_empType_Spnr);
        updateBtn = (Button) findViewById(R.id.button_update_profile);

        upd_dept_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                dept = adapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upd_type_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                type = adapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //TESTING

        //emp.setType(type);
        //emp.setName(emp_name);
        //emp.setEmp_number(emp_no);
        //emp.setName(emp_name);
        //emp.setEmail(emp_email);
        //emp.setPassword(emp_pwd);


        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                // collect all the values from the edit text...
                emp_no = e_no.getText().toString();
                emp_pwd =  e_pwd.getText().toString();
                emp_ph = e_ph.getText().toString();
                emp_email = e_email.getText().toString();
                emp_name = e_name.getText().toString();

                MainActivity.emp.setPassword(emp_pwd);
                MainActivity.emp.setPhone(emp_ph);

                MainActivity.emp.setEmail(emp_email);



                 josnString  =  empPop.convertToJSONObj(MainActivity.emp);
                 url = UrlManager.APIROOTURL+"employeeApi/"+MainActivity.emp.getId();


                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        (new CallWebApi()).Put(url, josnString);
                        return null;
                    }
                }.execute();


                // empid_pk >> id url/id
                // jsonString...
                // call the employee populator
                //empPop.populateEmployeByName(uname);


               AlertDialog alertDialog = new AlertDialog.Builder(UpdateProfile.this).create();

                        alertDialog.setTitle("Alert Dialog");
                        alertDialog.setMessage(emp_no);
                       // alertDialog.setMessage(emp_no + "\n" + emp_name + "\n" + emp_pwd + "\n" + dept + "\n" + type + "\n" + emp_ph + "\n" + emp_email);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alertDialog.show();
                    }
                });

    }

}


