package com.android_test.zmh.lu_stationerystoreinventorysystem.Main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText username;
    EditText password;
    Button loginBtn;
    public static Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passswordET);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Updated upstream

        // make a call to the webapi...abd fetch emp object...
        if ((username.getText().toString()).equals("Employee")) {
            Intent i = new Intent(MainActivity.this, EmployeeMainScreen.class);
            startActivity(i);

        }
        else if ((username.getText().toString()).equals("Clerk")) {
            Intent y = new Intent(MainActivity.this, ClerkMainScreen.class);
            startActivity(y);
        }
        else if ((username.getText().toString()).equals("Supervisor")) {
            startActivity(new Intent(MainActivity.this, SupervisorMainScreen.class));
        }
        else if ((username.getText().toString()).equals("Manager")) {
            startActivity(new Intent(MainActivity.this, ManagerMainScreen.class));
        }
        else if(username.getText().toString().equals("HOD")) {
            startActivity(new Intent(MainActivity.this, HODMainScreen.class));
        }
        else if(username.getText().toString().equals("Representative")) {
            startActivity(new Intent(MainActivity.this, RepresentativeMainScreen.class));
        }

    }
}
/*
// make a call to the webapi...abd fetch emp object...
>>>>>>> Stashed changes
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                emp = empPopulator.populateEmployeByUname(username.getText().toString());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(emp.getType().equals("Clerk")){
                    startActivity(new Intent(MainActivity.this, ClerkMainScreen.class));
                }else if(emp.getType().equals("Employee")){
                    startActivity(new Intent(MainActivity.this, EmployeeMainScreen.class));
                }else if(emp.getType().equals("HOD")){
                    startActivity(new Intent(MainActivity.this, HODMainScreen.class));
                }else if(emp.getType().equals("Manager")){
                    startActivity(new Intent(MainActivity.this, ManagerMainScreen.class));
                }else if(emp.getType().equals("Representative")){
                    startActivity(new Intent(MainActivity.this, RepresentativeMainScreen.class));
                }else if(emp.getType().equals("Supervisor")){
                    startActivity(new Intent(MainActivity.this, SupervisorMainScreen.class));
                }else if(emp ==null){
                    startActivity(new Intent(MainActivity.this, test.class));
                }
            }
        }.execute();

    }

}*/
