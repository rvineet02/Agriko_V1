package com.example.agritech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class screen2 extends AppCompatActivity {
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

//        name= intent.getExtras().getString("Name");
    }



    public void Milk(View view) {

        Intent intent = new Intent(this, MilkingListView.class);
        intent.putExtra("Name",name);
        startActivity(intent);
    }

    public void Calf(View view) {
        Intent intent = new Intent(this, CalfListView.class);
        intent.putExtra("Name",name);
        startActivity(intent);
    }

    public void Medical(View view) {
        Intent intent = new Intent(this, MedicalListView.class);
        intent.putExtra("Name",name);
        startActivity(intent);
    }

    public void Cow(View view) {
        Intent intent = new Intent(this, ListView.class);
//        intent.putExtra("Name",name);
        startActivity(intent);
    }


    public void Feed(View view) {
        Intent intent = new Intent(this, FeedListView.class);
        intent.putExtra("Name",name);
        startActivity(intent);
    }


}
