package com.android_test.zmh.lu_stationerystoreinventorysystem;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.usernameET);
        password = (EditText)findViewById(R.id.passswordET);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(username.getText().toString().equals("clerk") || username.getText().toString().equals("Clerk")){
            startActivity(new Intent(this, ClerkMainScreen.class));
        }else if(username.getText().toString().equals("employee") || username.getText().toString().equals("Employee")){
            startActivity(new Intent(this, EmployeeMainScreen.class));
        }else if(username.getText().toString().equals("hod") || username.getText().toString().equals("HOD")){
            startActivity(new Intent(this, HODMainScreen.class));
        }else if(username.getText().toString().equals("manager") || username.getText().toString().equals("Manager")){
            startActivity(new Intent(this, ManagerMainScreen.class));
        }else if(username.getText().toString().equals("representative") || username.getText().toString().equals("Representative")){
            startActivity(new Intent(this, RepresentativeMainScreen.class));
        }else if(username.getText().toString().equals("supervisor") || username.getText().toString().equals("Supervisor")){
            startActivity(new Intent(this, SupervisorMainScreen.class));
        }
        else if(username.getText().toString().equals("test")){
            startActivity(new Intent(this, test.class));
        }
    }
}
