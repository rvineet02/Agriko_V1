package com.example.agritech;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    public void OpenReg(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, username, password);

        if(UsernameEt.getText().length()==0)
        {
            UsernameEt.setError("Please Enter Name");
        }
        else if (PasswordEt.getText().length()==0)
        {
            PasswordEt.setError("Please Enter Password");
        }
        else if(username.equals("farm") && password.equals("farm") )
        {
            Intent i = new Intent(this,screen1.class);
            startActivity(i);
        }

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