package com.android_test.zmh.lu_stationerystoreinventorysystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Main.MainActivity;
import com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator.EmployeePopulator;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Employee;


public class ChangePassword extends ActionBarActivity {
    final Context context = this;

    EmployeePopulator empPopulator = new EmployeePopulator();
    EditText oldPwd;
    EditText newPwd;
    EditText confirmPwd;
    Button loginBtn;

    String existingPwd;
    String old_pwd;
    String new_pwd;
    String confirm_pwd;
    String jsonUpdateResult;
    Boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        existingPwd = MainActivity.emp.getPassword();
        oldPwd = (EditText) findViewById(R.id.old_pwd_et);
        newPwd = (EditText) findViewById(R.id.new_pwd_et);
        confirmPwd = (EditText) findViewById(R.id.confirm_pwd_et);
        loginBtn = (Button) findViewById(R.id.button_change_pwd);

        newPwd.setEnabled(false);
        confirmPwd.setEnabled(false);

        oldPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //SAVE THE DATA
                    old_pwd = oldPwd.getText().toString();
                    System.out.println("OLD PWD : " + MainActivity.emp.getPassword());
                    System.out.println(old_pwd.getClass());
                    System.out.println(existingPwd.getClass());
                    System.out.println("TYPED : " + old_pwd);

                    if (!old_pwd.equals(existingPwd)) {

                        System.out.println("not same");
                        flag = false;
                        AlertDialog alertDialog = new AlertDialog.Builder(ChangePassword.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setCancelable(true);
                        alertDialog.setMessage("Wrong password! Try Again!");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        alertDialog.show();
                        oldPwd.setText("");
                        newPwd.setEnabled(false);
                        confirmPwd.setEnabled(false);
                    } else {
                        flag = true;
                        oldPwd.setEnabled(false);
                        newPwd.setEnabled(true);
                        confirmPwd.setEnabled(true);

                    }
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_pwd = newPwd.getText().toString();
                confirm_pwd = confirmPwd.getText().toString();
                if((oldPwd.getText().toString().equals("")) || (newPwd.getText().toString().equals("")) || (confirmPwd.getText().toString().equals(""))){
                    Toast.makeText(ChangePassword.this,"Password fields are required!",Toast.LENGTH_SHORT).show();
                }else{
                    if (flag) {
                        if (new_pwd.equals(confirm_pwd)) {

                            new AsyncTask<Void, Void, String>() {
                                @Override
                                protected String doInBackground(Void... params) {
                                    jsonUpdateResult = empPopulator.changePassword(MainActivity.emp.getId(),new_pwd);
                                    return jsonUpdateResult;
                                }

                                @Override
                                protected void onPostExecute(String result) {
                                    if(!result.equals(null)){
                                        System.out.println("RESULT");
                                        System.out.println(result);
                                        finish();
                                        Toast.makeText(ChangePassword.this,"Change Password Successfully!",Toast.LENGTH_SHORT).show();
                                    }

                                }

                            }.execute();


                        } else {
                            System.out.println("Passwords mismatch");
                            Toast.makeText(ChangePassword.this, "Password mismatch! Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
