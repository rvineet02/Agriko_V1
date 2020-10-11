package com.example.agritech;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


// creating screen
public class MedicalSingleItemView extends Activity {
    TextView txtname;
    TextView txtcondition_1;
    TextView txtdisease;
    TextView txtdescripton;
    TextView txtmedication;
    TextView txtheight;
    TextView txtweight;


    ImageView imgflag;
    String name;
    String condition_1;
    String disease;
    String descritpion;
    String medication;
    int height;
    int weight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_single_item_view);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        name= i.getStringExtra("Name");
        // Get the results of country
        condition_1 = i.getStringExtra("Condition_1");
        // Get the results of population
        disease = i.getStringExtra("Disease");
        // Get the results of flag
        descritpion = i.getStringExtra("Description");
        medication= i.getStringExtra("Medication");
        height= i.getIntExtra("Height", -1);
        weight= i.getIntExtra("Weight", -1);


        // Locate the TextViews in singleitemview.xml
        txtname = (TextView) findViewById(R.id.name3);
        txtcondition_1 = (TextView) findViewById(R.id.country3);
        txtdisease = (TextView) findViewById(R.id.disease3);
        txtdescripton =(TextView) findViewById(R.id.description3);
        txtmedication =(TextView) findViewById(R.id.medication3);
        txtheight =(TextView) findViewById(R.id.height3);
        txtweight =(TextView) findViewById(R.id.weight3);




        // Locate the ImageView in singleitemview.xml


        // Load the results into the TextViews
        txtname.setText(name);
        txtcondition_1.setText(condition_1);
        txtdisease.setText(disease);
        txtdescripton.setText(descritpion);
        txtmedication.setText(medication);
        txtheight.setText(Integer.toString(height));
        txtweight.setText(Integer.toString(weight));

        // Load the image into the ImageView

    }

}
