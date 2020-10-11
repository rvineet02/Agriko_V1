package com.example.agritech;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;


//import com.example.logiin.R;
//import com.example.logiin.SingleItemView;

import java.util.ArrayList;
import java.util.Locale;


public class TennisAdapter extends BaseAdapter {
    // provide activity context
    private Context context;
    // store tennismodel objects
    private ArrayList<TennisModel> tennisModelArrayList;
    //store
    private ArrayList<TennisModel> arraylist;


    public TennisAdapter(Context context, ArrayList<TennisModel> tennisModelArrayList) {

        this.context = context;
        this.tennisModelArrayList = tennisModelArrayList;
        this.arraylist = new ArrayList<TennisModel>();
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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_player, null, true);

//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvage = (TextView) convertView.findViewById(R.id.country);
            holder.tvsex = (TextView) convertView.findViewById(R.id.city);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

//        Picasso.get().load(tennisModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvname.setText("Name: "+tennisModelArrayList.get(position).getName());
        holder.tvage.setText("Age: "+tennisModelArrayList.get(position).getAge());
        holder.tvsex.setText("Sex: "+tennisModelArrayList.get(position).getSex());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("Name",
                        (tennisModelArrayList.get(position).getName()));
                // Pass all data country
                intent.putExtra("Age",
                        (tennisModelArrayList.get(position).getAge()));
                // Pass all data population
                intent.putExtra("Sex",
                        (tennisModelArrayList.get(position).getSex()));

                intent.putExtra("Milking_Status",
                        (tennisModelArrayList.get(position).getMilking_status()));
                intent.putExtra("Farm_Since",
                        (tennisModelArrayList.get(position).getFarm_since()));
                // Pass all data flag

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        return convertView;


    }



    private class ViewHolder {

        protected TextView tvname, tvage, tvsex;
//        protected ImageView iv;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        tennisModelArrayList.clear();
        if (charText.length() == 0) {
            tennisModelArrayList.addAll(arraylist);
        } else {
            for (TennisModel cs : arraylist) {
                if (cs.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    tennisModelArrayList.add(cs);
                }
            }
        }
        notifyDataSetChanged();
    }





}
