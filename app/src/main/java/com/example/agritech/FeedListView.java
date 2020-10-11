package com.example.agritech;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

// Using class as foundation for FeedListView
public class FeedListView extends AppCompatActivity {

    private String jsonURL = "http://192.168.1.123/form1.php";
    private final int jsoncode = 1;
    private android.widget.ListView listView;
    ArrayList<FeedModel> tennisModelArrayList;
    private FeedAdapter tennisAdapter;
    EditText edittext;

    private String name;
    private static ProgressDialog mProgressDialog;

//    private Button mAscButton;
//    private Button mDescButton;
//
//    private boolean sortAscending = true;
//    private boolean unSorted = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list_view);


        listView = findViewById(R.id.lv3);
        edittext = findViewById(R.id.search2);
//        mAscButton = (Button) findViewById(R.id.asc_button);

        Intent intent = getIntent();
        name = intent.getExtras().getString("Name");

        fetchJSON();


    }


    @SuppressLint("StaticFieldLeak")
    private void fetchJSON() {

        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest("http://www.grstfarms.in/cowfeed_view.php?Name=" + Uri.encode(name));
//                    HttpRequest req = new HttpRequest("http://www.goldenhillsindia.com/farm2.php?Name=" + Uri.encode(name));
                    // HttpRequest req = new HttpRequest("http://www.goldenhillsindia.com/referral.php?School="+ Uri.encode(school)); here the key id should be done so that only that specific cow ID is chosen
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }

            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss", result);
                onTaskCompleted(result, jsoncode);
            }
        }.execute();
    }


    public void onTaskCompleted(final String response, int serviceCode) {
        Log.d("responsejson", response.toString());
//        switch (serviceCode) {
//            case jsoncode:

//                if (isSuccess(response)) {
        removeSimpleProgressDialog();  //will remove progress dialog
        tennisModelArrayList = getInfo(response);
        tennisAdapter = new FeedAdapter(this, tennisModelArrayList);
        listView.setAdapter(tennisAdapter);


        // Search bar:
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

    public ArrayList<FeedModel> getInfo(String response) {
        ArrayList<FeedModel> tennisModelArrayList = new ArrayList<>();
        try {
            JSONArray jsonObject = new JSONArray(response);
//            if (jsonObject.getString("status").equals("true")) {

//                JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonObject.length(); i++) {

                JSONObject dataobj = jsonObject.getJSONObject(i);
                FeedModel playersModel = new FeedModel();
                playersModel.setFeed(dataobj.getString("Feed"));
                playersModel.setQuantity(dataobj.getInt("Quantity"));
                playersModel.setPurchase(dataobj.getString("Purchase"));
                playersModel.setPrice(dataobj.getInt("Price"));

                tennisModelArrayList.add(playersModel); // this is where the list view is being populated.

            }

            Collections.sort(tennisModelArrayList, SortFeed.ASCENDING_COMPARATOR);


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

// once progress dialog appears, we must remove it
    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        }
        //catch possible exceptions
        catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

// method to indicate user of background process running
    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }
// dialog box should only show when background process is running otherwise it must be cancelled
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
// catch handling errors for example incorrect arguments, runtime exceptions.
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
