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

public class CalfSingleItemView extends Activity {
    TextView txtcalf;
    TextView txtname;
    TextView txtborn;
    TextView txtsex;
    ImageView imgflag;
    int calf;
    String name;
    String born;
    String sex;
    String farm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calf_single_item_view);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        calf= i.getIntExtra("Calf", -1);
        // Get the results of country
        name = i.getStringExtra("Name");
        // Get the results of population
        born = i.getStringExtra("Born");
        // Get the results of flag
        sex = i.getStringExtra("Sex");

        // Locate the TextViews in singleitemview.xml
        txtcalf = (TextView) findViewById(R.id.calf2);
        txtname = (TextView) findViewById(R.id.country2);
        txtborn = (TextView) findViewById(R.id.born);
        txtsex =(TextView) findViewById(R.id.sex2);
        // Locate the ImageView in singleitemview.xml


        // Load the results into the TextViews
        txtcalf.setText(Integer.toString(calf));
        txtname.setText(name);
        txtborn.setText(born);
        txtsex.setText(sex);
        // Load the image into the ImageView

    }

}
