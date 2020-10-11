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
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Form_3_Daily_Miliking extends AppCompatActivity {
    EditText date,name, morning, evening;
    String farm, username;
    private DatePickerDialog mDatePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_3__daily__miliking);
        date = (EditText) findViewById(R.id.ref_date);
        name = (EditText) findViewById(R.id.name);
        morning = (EditText) findViewById(R.id.mor_milk_count);
        evening = (EditText) findViewById(R.id.eve_milk_count);

        morning.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "20" )});
        evening.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "20" )});


        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setDateTimeField();
        date.setOnTouchListener(new View.OnTouchListener() {
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

                date.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void AddMilk(View view) {
        String str_date =date.getText().toString();
        String str_ID = name.getText().toString();
        String str_morning = morning.getText().toString();
        String str_evening = evening.getText().toString();


        Intent intent = new Intent(this, screen1.class);
        intent.putExtra("Username",str_ID);

        startActivity(intent);


        String type = "table2";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, str_date, str_ID,str_morning,str_evening);
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
