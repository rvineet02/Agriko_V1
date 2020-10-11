package com.example.agritech;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

// need to use baseadapter to populate the ListView or List interface and for serialization.
public class FeedAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FeedModel> tennisModelArrayList;
    private ArrayList<FeedModel> arraylist;

    public FeedAdapter(Context context, ArrayList<FeedModel> tennisModelArrayList) {

        this.context = context;
        this.tennisModelArrayList = tennisModelArrayList;
        this.arraylist = new ArrayList<FeedModel>();
        this.arraylist.addAll(tennisModelArrayList);
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return tennisModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tennisModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FeedAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new FeedAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_feed, null, true);

//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvfeed = (TextView) convertView.findViewById(R.id.feed1);
            holder.tvquantity = (TextView) convertView.findViewById(R.id.country4);
            holder.tvpurchase = (TextView) convertView.findViewById(R.id.city5);
            holder.tvprice = (TextView) convertView.findViewById(R.id.colony5);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (FeedAdapter.ViewHolder)convertView.getTag();
        }

//        Picasso.get().load(tennisModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvfeed.setText("Feed: "+tennisModelArrayList.get(position).getFeed());
        holder.tvquantity.setText("Quantity: "+tennisModelArrayList.get(position).getQuantity());
        holder.tvpurchase.setText("Purchase: "+tennisModelArrayList.get(position).getPurchase());
        holder.tvprice.setText("Price: "+tennisModelArrayList.get(position).getPrice());



        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, FeedSingleItemView.class);
                // Pass all data rank
                intent.putExtra("Feed",
                        (tennisModelArrayList.get(position).getFeed()));
                // Pass all data country
                intent.putExtra("Quantity",
                        (tennisModelArrayList.get(position).getQuantity()));
                // Pass all data population
                intent.putExtra("Purchase",
                        (tennisModelArrayList.get(position).getPurchase()));

                intent.putExtra("Price",
                        (tennisModelArrayList.get(position).getPrice()));
                // Pass all data flag

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        return convertView;


    }


    private class ViewHolder {

        protected TextView tvfeed, tvquantity, tvpurchase, tvprice;
//        protected ImageView iv;
    }

// filter method for the search button
// Clear the bar each time we use the search bar

    public  void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        tennisModelArrayList.clear();
        if (charText.length() == 0) {
            tennisModelArrayList.addAll(arraylist);
        } else {
            for (FeedModel cs : arraylist) {
                if (cs.getFeed().toLowerCase(Locale.getDefault()).contains(charText)) {
                    tennisModelArrayList.add(cs);
                }
            }
        }

        //call getView() at positions where the searched for name is true.
        // this is done through the baseadapter funtion notifyDataSetChanged()
        notifyDataSetChanged();
    }


    }

