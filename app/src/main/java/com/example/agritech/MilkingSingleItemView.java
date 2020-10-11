package com.example.agritech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MilkingSingleItemView extends Activity {
    TextView txtdate;
    TextView txtname;
    TextView txtmorning;
    TextView txtevening;
    ImageView imgflag;
    String date;
    String name;
    int morning;
    int evening;
    String farm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milking_single_item_view);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        date= i.getStringExtra("Date");
        // Get the results of country
        name = i.getStringExtra("Name");
        // Get the results of population
        morning = i.getIntExtra("Morning", -1);
        // Get the results of flag
        evening = i.getIntExtra("Evening", -1);

        // Locate the TextViews in singleitemview.xml
        txtdate = (TextView) findViewById(R.id.date1);
        txtname = (TextView) findViewById(R.id.country1);
        txtmorning = (TextView) findViewById(R.id.morning);
        txtevening =(TextView) findViewById(R.id.evening);
        // Locate the ImageView in singleitemview.xml


        // Load the results into the TextViews
        txtdate.setText(date);
        txtname.setText(name);
        txtmorning.setText(Integer.toString(morning));
        txtevening.setText(Integer.toString(evening));
        // Load the image into the ImageView

    }


}
