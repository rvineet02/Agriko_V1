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


public class FeedSingleItemView extends Activity {

    TextView txtfeed;
    TextView txtquantity;
    TextView txtpurchase;
    TextView txtprice;
    ImageView imgflag;
    String feed;
    int quantity;
    String purchase;
    int price;
    String farm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_single_item_view);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        feed= i.getStringExtra("Feed");
        // Get the results of country
        quantity = i.getIntExtra("Quantity", -1);
        // Get the results of population
        purchase = i.getStringExtra("Purchase");
        // Get the results of flag
        price = i.getIntExtra("Price", -1);

        // Locate the TextViews in singleitemview.xml
        txtfeed = (TextView) findViewById(R.id.feed2);
        txtquantity = (TextView) findViewById(R.id.country3);
        txtpurchase = (TextView) findViewById(R.id.purchase);
        txtprice =(TextView) findViewById(R.id.price2);
        // Locate the ImageView in singleitemview.xml


        // Load the results into the TextViews
        txtfeed.setText(feed);
        txtquantity.setText(Integer.toString(quantity));
        txtpurchase.setText(purchase);
        txtprice.setText(Integer.toString(price));
        // Load the image into the ImageView

    }






}
