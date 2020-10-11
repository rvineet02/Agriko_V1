package com.example.agritech;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Form_4_Calf extends AppCompatActivity {
    EditText calf,name,born,sex;
    String farm, username;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_4__calf);
        calf = (EditText) findViewById(R.id.calf_ID);
        name = (EditText) findViewById(R.id.parent_ID);
        born = (EditText) findViewById(R.id.date_born);
        sex = (EditText) findViewById(R.id.text_sex);

        Intent intent = getIntent();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setDateTimeField();
        born.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
                return false;
            }
        });




    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                born.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }


    public void AddCalf(View view) {
        String str_calf =calf.getText().toString();
        String str_parent = name.getText().toString();
        String str_born = born.getText().toString();
        String str_sex = sex.getText().toString();


        Intent intent = new Intent(this, screen1.class);
        intent.putExtra("Username",str_calf);

        startActivity(intent);


        String type = "table3";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, str_calf, str_parent,str_born,str_sex);
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
