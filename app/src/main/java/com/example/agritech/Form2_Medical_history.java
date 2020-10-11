package com.example.agritech;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Form2_Medical_history extends AppCompatActivity {
    EditText ID,condition,disease,description,medication,height,weight;
    String farm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2__medical_history);
        ID = (EditText) findViewById(R.id.Name);
        condition = (EditText) findViewById(R.id.present_condition);
        disease = (EditText) findViewById(R.id.disease_history);
        description= (EditText) findViewById(R.id.description);
        medication = (EditText) findViewById(R.id.vaccination);
        height = (EditText) findViewById(R.id.last_height);
        weight = (EditText) findViewById(R.id.last_weight);
        Intent intent = getIntent();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onCow(View view) {
        String str_name =ID.getText().toString();
        String str_condition = condition.getText().toString();
        String str_disease = disease.getText().toString();
        String str_description = description.getText().toString();
        String str_medication = medication.getText().toString();
        String str_height = height.getText().toString();
        String str_weight = weight.getText().toString();


        Intent intent = new Intent(this, screen1.class);
        intent.putExtra("Username",str_name);

        startActivity(intent);


        String type = "table1";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, str_name,str_condition,str_disease,str_description,str_medication, str_height, str_weight);
        closeKeyboard();


    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }







    public void onBack(View view) {


        Intent intent = new Intent(this, screen1.class);
        startActivity(intent);
    }

}
