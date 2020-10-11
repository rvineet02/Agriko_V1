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

public class CalfAdapter extends BaseAdapter {


    // provide activity context
    private Context context;
    // store calfmodel objects
    private ArrayList<CalfModel> tennisModelArrayList;
    private ArrayList<CalfModel> arraylist;

    //constructor
    public CalfAdapter(Context context, ArrayList<CalfModel> tennisModelArrayList) {

        this.context = context;
        this.tennisModelArrayList = tennisModelArrayList;
        this.arraylist = new ArrayList<CalfModel>();
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

// size
    @Override
    public int getCount() {
        return tennisModelArrayList.size();
    }
// row position
    @Override
    public Object getItem(int position) {
        return tennisModelArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


// to hold in the convert view
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CalfAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new CalfAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_calf, null, true);

//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvcalf = (TextView) convertView.findViewById(R.id.calf1);
            holder.tvname = (TextView) convertView.findViewById(R.id.country2);
            holder.tvborn = (TextView) convertView.findViewById(R.id.city2);
            holder.tvsex = (TextView) convertView.findViewById(R.id.colony2);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (CalfAdapter.ViewHolder)convertView.getTag();
        }

// from position, use getter methods to get them
        holder.tvcalf.setText("Calf: "+tennisModelArrayList.get(position).getCalf());
        holder.tvname.setText("Name: "+tennisModelArrayList.get(position).getName());
        holder.tvborn.setText("Born: "+tennisModelArrayList.get(position).getBorn());
        holder.tvsex.setText("Sex: "+tennisModelArrayList.get(position).getSex());



        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, CalfSingleItemView.class);
                // Pass all data rank
                intent.putExtra("Calf",
                        (tennisModelArrayList.get(position).getCalf()));
                // Pass all data country
                intent.putExtra("Name",
                        (tennisModelArrayList.get(position).getName()));
                // Pass all data population
                intent.putExtra("Born",
                        (tennisModelArrayList.get(position).getBorn()));

                intent.putExtra("Sex",
                        (tennisModelArrayList.get(position).getSex()));
                // Pass all data flag

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        return convertView;

    }


    private class ViewHolder {

        protected TextView tvcalf, tvname, tvborn, tvsex;
//        protected ImageView iv;
    }




}
