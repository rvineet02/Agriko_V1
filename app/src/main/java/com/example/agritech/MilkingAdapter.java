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

public class MilkingAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MilkingModel> tennisModelArrayList;
    private ArrayList<MilkingModel> arraylist;
    public MilkingAdapter(Context context, ArrayList<MilkingModel> tennisModelArrayList) {

        this.context = context;
        this.tennisModelArrayList = tennisModelArrayList;
        this.arraylist = new ArrayList<MilkingModel>();
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
        MilkingAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new MilkingAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_milking, null, true);

//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvdate = (TextView) convertView.findViewById(R.id.name1);
            holder.tvname = (TextView) convertView.findViewById(R.id.country1);
            holder.tvmorning = (TextView) convertView.findViewById(R.id.city1);
            holder.tvevening = (TextView) convertView.findViewById(R.id.colony1);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (MilkingAdapter.ViewHolder)convertView.getTag();
        }

//        Picasso.get().load(tennisModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvdate.setText("Date: "+tennisModelArrayList.get(position).getDate());
        holder.tvname.setText("Name: "+tennisModelArrayList.get(position).getName());
        holder.tvmorning.setText("Morning: "+tennisModelArrayList.get(position).getMorning());
        holder.tvevening.setText("Evening: "+tennisModelArrayList.get(position).getEvening());



        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, MilkingSingleItemView.class);
                // Pass all data rank
                intent.putExtra("Date",
                        (tennisModelArrayList.get(position).getDate()));
                // Pass all data country
                intent.putExtra("Name",
                        (tennisModelArrayList.get(position).getName()));
                // Pass all data population
                intent.putExtra("Morning",
                        (tennisModelArrayList.get(position).getMorning()));

                intent.putExtra("Evening",
                        (tennisModelArrayList.get(position).getEvening()));
                // Pass all data flag

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        return convertView;


    }



    private class ViewHolder {

        protected TextView tvdate, tvname, tvmorning, tvevening;
//        protected ImageView iv;
    }






}
