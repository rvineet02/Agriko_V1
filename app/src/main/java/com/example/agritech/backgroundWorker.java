package com.example.agritech;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

//packages to import to establish connection with mysql database over the internet
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class backgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    public backgroundWorker(Context ctx) { context = ctx;}

    @Override
    protected String doInBackground (String... params) {
        String type = params[0];
        String type1 = params[0];
        String type2 = params[0];
        String type3 = params[0];

        String login_url_1 = "http://www.goldenhillsindia.com/login.php";
        String register_url_1 = "http://192.168.1.123/register.php";
        String table_url_1 = "http://www.grstfarms.in/cowinfo.php";
        String table_url = "http://www.grstfarms.in/cowmedical.php";
        String table_url_2 = "http://www.grstfarms.in/cowmilk.php";
        String table_url_3 = "http://www.grstfarms.in/cowcalf.php";
        String table_url_4 = "http://www.grstfarms.in/cowfeed.php";


// add else if statement every time you want add to new page which needs to store information into the database
// the screen is identified based on the name of the table. The string values which contain the link to the online database

        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url_1);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  else if(type.equals("register")) {
            try {
                String name = params[1];
                String surname = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];

                URL url = new URL(register_url_1);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(surname,"UTF-8")+"&" +
                        URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&" +
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   else if(type.equals("table")) {
            try {
                String ID = params[1];
                String sex = params[3];
                String age = params[2];
                String milkingstatus = params[5];
                String onfarmsince = params[4];
                URL url = new URL(table_url_1);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(ID,"UTF-8")+"&"+
                        URLEncoder.encode("Sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")+"&" +
                        URLEncoder.encode("Age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&" +
                        URLEncoder.encode("Milking_Status","UTF-8")+"="+URLEncoder.encode(milkingstatus,"UTF-8")+"&" +
                        URLEncoder.encode("Farm_Since","UTF-8")+"="+URLEncoder.encode(onfarmsince,"UTF-8")+"&" ;

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        else if(type.equals("table1")) {
            try {
                String ID = params[1];
                String condition_1 = params[2];
                String disease = params[3];
                String description = params[4];
                String medication = params[5];
                String height = params[6];
                String weight = params[7];
                URL url = new URL(table_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(ID,"UTF-8")+"&"+
                        URLEncoder.encode("Condition_1","UTF-8")+"="+URLEncoder.encode(condition_1,"UTF-8")+"&" +
                        URLEncoder.encode("Disease","UTF-8")+"="+URLEncoder.encode(disease,"UTF-8")+"&" +
                        URLEncoder.encode("Description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&" +
                        URLEncoder.encode("Medication","UTF-8")+"="+URLEncoder.encode(medication,"UTF-8")+"&" +
                        URLEncoder.encode("Height","UTF-8")+"="+URLEncoder.encode(height,"UTF-8")+"&" +
                        URLEncoder.encode("Weight","UTF-8")+"="+URLEncoder.encode(weight,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        else if(type.equals("table2")) {
            try {
                String date = params[1];
                String name = params[2];
                String morning = params[3];
                String evening = params[4];
                URL url = new URL(table_url_2);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"+
                        URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&" +
                        URLEncoder.encode("Morning","UTF-8")+"="+URLEncoder.encode(morning,"UTF-8")+"&" +
                        URLEncoder.encode("Evening","UTF-8")+"="+URLEncoder.encode(evening,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if(type.equals("table3")) {
            try {
                String calf = params[1];
                String name = params[2];
                String born = params[3];
                String sex = params[4];
                URL url = new URL(table_url_3);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Calf","UTF-8")+"="+URLEncoder.encode(calf,"UTF-8")+"&"+
                        URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&" +
                        URLEncoder.encode("Born","UTF-8")+"="+URLEncoder.encode(born,"UTF-8")+"&" +
                        URLEncoder.encode("Sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
// data entry for Feed information
        else if(type.equals("table4")) {
            try {
                // list of actions that could lead to exception arising
                // list of variables in the table and the name in the app
                // the names must be the same

                String feed = params[1];
                String quantity = params[2];
                String purchase = params[3];
                String price = params[4];
                URL url = new URL(table_url_4);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Feed","UTF-8")+"="+URLEncoder.encode(feed,"UTF-8")+"&"+
                        URLEncoder.encode("Quantity","UTF-8")+"="+URLEncoder.encode(quantity,"UTF-8")+"&" +
                        URLEncoder.encode("Purchase","UTF-8")+"="+URLEncoder.encode(purchase,"UTF-8")+"&" +
                        URLEncoder.encode("Price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            //there are two types of exceptions. Print the stack trace.
            catch (MalformedURLException e) { // trying to catch these exceptions
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }





        return null;
    }






    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

//         if(result.contains("Data inserted successfully")) {
//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//
//        }






    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}



























