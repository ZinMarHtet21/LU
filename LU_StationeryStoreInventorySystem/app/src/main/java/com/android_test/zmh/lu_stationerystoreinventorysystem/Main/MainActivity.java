package com.android_test.zmh.lu_stationerystoreinventorysystem.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.DepartmentScreens.test;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.ClerkMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.EmployeeMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.HODMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.ManagerMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.RepresentativeMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.MainScreens.SupervisorMainScreen;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.EmployeePopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Tools.UrlManager;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    EmployeePopulator empPopulator = new EmployeePopulator();
    EditText empNo;
    EditText empPwd;
    Button loginBtn;
    String emp_no, emp_pwd;
    public static Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empNo = (EditText) findViewById(R.id.empNo_et);
        empPwd = (EditText) findViewById(R.id.empPwd_et);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {

        emp_no = empNo.getText().toString();
        emp_pwd = empPwd.getText().toString();

        if ((emp_no.equals("")) || (emp_pwd.equals(""))) {

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("You need to login!");
            alertDialog.setCancelable(true);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();
        } else {

            System.out.println(emp_no);
            System.out.println(emp_pwd);

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {

                    emp = empPopulator.LoginEmployee(emp_no, emp_pwd);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (emp != null) {
                        System.out.println("GOT EMP OBJ");
                        if (emp.getType().equals("Clerk")) {
                            startActivity(new Intent(MainActivity.this, ClerkMainScreen.class));
                        } else if (emp.getType().equals("Employee")) {
                            startActivity(new Intent(MainActivity.this, EmployeeMainScreen.class));
                        } else if (emp.getType().equals("HOD")) {
                            startActivity(new Intent(MainActivity.this, HODMainScreen.class));
                        } else if (emp.getType().equals("Manager")) {
                            startActivity(new Intent(MainActivity.this, ManagerMainScreen.class));
                        } else if (emp.getType().equals("Representative")) {
                            startActivity(new Intent(MainActivity.this, RepresentativeMainScreen.class));
                        } else if (emp.getType().equals("Supervisor")) {
                            startActivity(new Intent(MainActivity.this, SupervisorMainScreen.class));
                        } else if (emp == null) {
                            startActivity(new Intent(MainActivity.this, test.class));
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User Not Found!", Toast.LENGTH_LONG).show();
                    }
                    empNo.setText("");
                    empPwd.setText("");
                }
            }.execute();
        }
    }
}



