package com.example.agritech;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MedicalListView extends AppCompatActivity {

    private String jsonURL = "http://192.168.1.123/form1.php";
    private final int jsoncode = 1;
    private android.widget.ListView listView;
    ArrayList<MedicalModel> tennisModelArrayList;
    private MedicalAdapter tennisAdapter;

    private String name;
    private static ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list_view);


        listView = findViewById(R.id.lv3);

        Intent intent = getIntent();
        name= intent.getExtras().getString("Name");

        fetchJSON();

    }


    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest("http://www.grstfarms.in/cowmedical_view.php?Name=" + Uri.encode(name));
//                    HttpRequest req = new HttpRequest("http://www.goldenhillsindia.com/farm3.php?Name=" + Uri.encode(name));
                    // HttpRequest req = new HttpRequest("http://www.goldenhillsindia.com/referral.php?School="+ Uri.encode(school)); here the key id should be done so that only that specific cow ID is chosen
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }


    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
//        switch (serviceCode) {
//            case jsoncode:

//                if (isSuccess(response)) {
        removeSimpleProgressDialog();  //will remove progress dialog
        tennisModelArrayList = getInfo(response);
        tennisAdapter = new MedicalAdapter(this,tennisModelArrayList);
        listView.setAdapter(tennisAdapter);

//                }else {
//                    Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
//                }

    }


    public ArrayList<MedicalModel> getInfo(String response) {
        ArrayList<MedicalModel> tennisModelArrayList = new ArrayList<>();
        try {
            JSONArray jsonObject = new JSONArray(response);
//            if (jsonObject.getString("status").equals("true")) {

//                JSONArray dataArray = jsonObject.getJSONArray("data");


            //iterate through the each row of the table.
            //get length using json object
            for (int i = 0; i < jsonObject.length(); i++) {

                JSONObject dataobj = jsonObject.getJSONObject(i);
                MedicalModel playersModel = new MedicalModel();
                playersModel.setName(dataobj.getString("Name"));
                playersModel.setCondition_1(dataobj.getString("Condition_1"));
                playersModel.setDisease(dataobj.getString("Disease"));
                playersModel.setDescription(dataobj.getString("Description"));
                playersModel.setMedication(dataobj.getString("Medication"));
                playersModel.setHeight(dataobj.getInt("Height"));
                playersModel.setWeight(dataobj.getInt("Weight"));

                tennisModelArrayList.add(playersModel); //arraylist is populated with each row from the database

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tennisModelArrayList;
    }


    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }



    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
