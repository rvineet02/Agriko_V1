package com.example.agritech;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;


import com.example.agritech.HttpRequest;
import com.example.agritech.R;
import com.example.agritech.TennisAdapter;
import com.example.agritech.TennisModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;



public class ListView extends AppCompatActivity {
//global variables used throughout the class

    private final int jsoncode = 1;
    private android.widget.ListView listView;
    ArrayList<TennisModel> tennisModelArrayList;
    private TennisAdapter tennisAdapter;
    EditText edittext;
    private static ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.lv);
        edittext= findViewById(R.id.search);

        fetchJSON();

        Intent intent  = getIntent();


    }

    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest("http://www.grstfarms.in/cowinfo_view.php");
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
        tennisAdapter = new TennisAdapter(this,tennisModelArrayList);
        listView.setAdapter(tennisAdapter);

//                }else {
//                    Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
//                }




        // Search Bar:



        edittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = edittext.getText().toString().toLowerCase(Locale.getDefault());
                tennisAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }



    public ArrayList<TennisModel> getInfo(String response) {
        ArrayList<TennisModel> tennisModelArrayList = new ArrayList<>();
        try {
            JSONArray jsonObject = new JSONArray(response);
//            if (jsonObject.getString("status").equals("true")) {

//                JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonObject.length(); i++) {
// we will create a local variable which is playersModel which will be used to retrieve all the information from the online database as it iterates
// through the loop.
                TennisModel playersModel = new TennisModel();
                JSONObject dataobj = jsonObject.getJSONObject(i);
                playersModel.setName(dataobj.getString("Name"));
                playersModel.setAge(dataobj.getString("Sex"));
                playersModel.setSex(dataobj.getString("Age"));
                playersModel.setMilking_status(dataobj.getString("Milking_Status"));
                playersModel.setFarm_since(dataobj.getString("Farm_Since"));

                tennisModelArrayList.add(playersModel);

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



