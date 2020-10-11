package com.example.agritech;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class form_1_cow_information extends AppCompatActivity {
    EditText ID,sex,age,milkingstatus;
    EditText farmsince;
    private DatePickerDialog mDatePickerDialog;

    String farm,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_1_cow_information);
        ID =(EditText) findViewById(R.id.text_ID);
        sex = (EditText) findViewById(R.id.text_sex);
        age = (EditText) findViewById(R.id.text_age);
        milkingstatus = (EditText) findViewById(R.id.milking_status);
        farmsince = (EditText)findViewById(R.id.farm_since);
        Intent intent = getIntent();

//        username= intent.getExtras().getString("Username");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        age.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "99" )});


        setDateTimeField();
        farmsince.setOnTouchListener(new View.OnTouchListener() {
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

                farmsince.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void onClick(View view) {
        String str_name =ID.getText().toString();
        String str_sex = sex.getText().toString();
        String str_age = age.getText().toString();
        String str_milking_status = milkingstatus.getText().toString();
        String str_farm_since = farmsince.getText().toString();


        Intent intent = new Intent(this, screen1.class);
        intent.putExtra("Username",str_name);

        startActivity(intent);


        String type = "table";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_age,str_sex,str_farm_since,str_milking_status);
        closeKeyboard();


    }
    public void onBack(View view) {


        Intent intent = new Intent(this, screen1.class);
        startActivity(intent);
    }



    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }




//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


//    }
//    public void onNothingSelected(AdapterView<?> arg0) {
//          //TODO Auto-generated method stub }


}
