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

public class MedicalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MedicalModel> tennisModelArrayList;
    private ArrayList<MedicalModel> arraylist;
    public MedicalAdapter(Context context, ArrayList<MedicalModel> tennisModelArrayList) {

        this.context = context;
        this.tennisModelArrayList = tennisModelArrayList;
        this.arraylist = new ArrayList<MedicalModel>();
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
        MedicalAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new MedicalAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_medical, null, true);

//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.calf1);
            holder.tvcondition_1 = (TextView) convertView.findViewById(R.id.country2);
            holder.tvdisease = (TextView) convertView.findViewById(R.id.city2);



            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (MedicalAdapter.ViewHolder)convertView.getTag();
        }

//        Picasso.get().load(tennisModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvname.setText("Name: "+tennisModelArrayList.get(position).getName());
        holder.tvcondition_1.setText("Condition: "+tennisModelArrayList.get(position).getCondition_1());
        holder.tvdisease.setText("Disease: "+tennisModelArrayList.get(position).getDisease());








        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, MedicalSingleItemView.class);
                // Pass all data rank
                intent.putExtra("Name",
                        (tennisModelArrayList.get(position).getName()));
                // Pass all data country
                intent.putExtra("Condition_1",
                        (tennisModelArrayList.get(position).getCondition_1()));
                // Pass all data population
                intent.putExtra("Disease",
                        (tennisModelArrayList.get(position).getDisease()));

                intent.putExtra("Description",
                        (tennisModelArrayList.get(position).getDescription()));
                intent.putExtra("Medication",
                        (tennisModelArrayList.get(position).getMedication()));
                intent.putExtra("Height",
                        (tennisModelArrayList.get(position).getHeight()));
                intent.putExtra("Weight",
                        (tennisModelArrayList.get(position).getWeight()));

                // Pass all data flag

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        return convertView;


    }


    private class ViewHolder {

        protected TextView tvname, tvcondition_1, tvdisease, tvdescription, tvmedication, tvheight, tvweight;
//        protected ImageView iv;
    }

}
