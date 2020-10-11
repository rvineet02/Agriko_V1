package com.example.agritech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class screen1 extends AppCompatActivity {
// onCreate method will override the parent onCreate method from the AppCompatActivity class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1); // connection to the xml file for the UI Design
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            }
 // following methods are EditTexts on click methods that will perform some action when the text being displayed on the screen is clicked
    public void onClick(View view) {
        Intent i = new Intent(this,form_1_cow_information.class);
        startActivity(i);
    }

    public void onEclick(View view) {
        Intent i = new Intent(this,Form2_Medical_history.class);
        startActivity(i);
    }

    public void onRclick(View view) {
        Intent i = new Intent(this,Form_4_Calf.class);
        startActivity(i);
    }

    public void onAclick(View view) {
        Intent i = new Intent(this,form_5_feed.class);
        startActivity(i);
    }

    public void onMclick(View view) {
        Intent i = new Intent(this,Form_3_Daily_Miliking.class);
        startActivity(i);
    }

    public void list(View view) {
        Intent i = new Intent(this,screen2.class);
        startActivity(i);
    }


}


