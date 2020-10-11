package com.example.agritech;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;




public class SingleItemView extends Activity {

    // Declare Variables
    TextView txtname;
    TextView txtage;
    TextView txtsex;
    TextView txtmilking;
    ImageView imgflag;
    String name;
    String age;
    String sex;
    String milking;
    String farm;
    TextView txttfarm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        name= i.getStringExtra("Name");
        // Get the results of country
        age = i.getStringExtra("Age");
        // Get the results of population
        sex = i.getStringExtra("Sex");
        // Get the results of flag
        milking = i.getStringExtra("Milking");
        farm = i.getStringExtra("Farm");

        // Locate the TextViews in singleitemview.xml
        txtname = (TextView) findViewById(R.id.name);
        txtage = (TextView) findViewById(R.id.country);
        txtsex = (TextView) findViewById(R.id.sex);
        txtmilking =(TextView) findViewById(R.id.milking);
        txttfarm = (TextView) findViewById(R.id.farm);
        // Locate the ImageView in singleitemview.xml


        // Load the results into the TextViews
        txtname.setText(name);
        txtage.setText(age);
        txtsex.setText(sex);
        txtmilking.setText(milking);
        txttfarm.setText(farm);

    }

    public void onHome(View view) {
        Intent i = new Intent(this,screen1.class);
        startActivity(i);
    }






}
