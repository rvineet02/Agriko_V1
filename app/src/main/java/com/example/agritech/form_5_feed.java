package com.example.agritech;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class form_5_feed extends AppCompatActivity {
    EditText feed, quantity, purchase,price;
    String farm, username;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_5_feed);
        feed  =(EditText) findViewById(R.id.feed_type);
        quantity = (EditText) findViewById(R.id.int_quantity);
        purchase = (EditText) findViewById(R.id.purchase_date);
        price = (EditText) findViewById(R.id.int_price);

        Intent intent = getIntent();


        setDateTimeField();
         purchase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
                return false;
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

                purchase.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }


    public void AddPurchase(View view) {
        String str_feed =feed.getText().toString();
        String str_quantity = quantity.getText().toString();
        String str_purchase = purchase.getText().toString();
        String str_price = price.getText().toString();


        Intent intent = new Intent(this, screen1.class);
        intent.putExtra("Username",str_feed);

        startActivity(intent);


        String type = "table4";
        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, str_feed, str_quantity,str_purchase,str_price);
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


