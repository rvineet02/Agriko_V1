package com.example.agritech;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


public class login_1 extends AppCompatActivity {
    EditText UsernameEt,PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);
        UsernameEt =(EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);

    }
    public void OpenReg(View view) {
        startActivity(new Intent(this, com.example.agritech.Register.class));
    }
    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, username, password);

        closeKeyboard();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }





}
