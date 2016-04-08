package com.example.umyhpuscdi.danceapp15;

import android.os.AsyncTask;


import com.example.umyhpuscdi.danceapp15.AdminActivity;
import com.example.umyhpuscdi.danceapp15.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by umyhlarsle on 2016-02-18.
 */
public class AsyncCourse extends AsyncTask<String, Void, String> {

    HttpURLConnection connection;
    URL url;

    AdminActivity adminActivity; ///Namnet på second activity
    JSONObject json;
    int responseCode;
    private String URLEN = "http://api.cmdemo.se/";

    public AsyncCourse(AdminActivity secondActivity, JSONObject json, int removeId){
        this.adminActivity=secondActivity;
        this.json = json;
    }




    @Override //task.execute("POST", "lists/")
    protected String doInBackground(String... params) { ///params[0]=method, t.ex. GET, params[1]=URI, t.ex. "lists/"

        try {
            url = new URL(URLEN + params[1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod(params[0]);
            switch (params[0]) {
                case "GET":
                    connection.setDoOutput(false); //No body = false
                    connection.setDoInput(true); //We want response from server = true
                    connection.setRequestProperty("Accept", "application/json");
                    connection.connect();

                    responseCode = connection.getResponseCode();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    if (params[1].equals("lists/"+"258"+"/tasks/")) {

                       /// JSONArray lists = new JSONArray(response.toString());

                      /*  adminActivity.tasklist.clear();
                        for (int i = 0; i < lists.length(); i++) {
                            JSONObject thelist = (JSONObject) lists.get(i);
                            Course mCourse = new Course();
                            mCourse.description = thelist.getString("description");
                            mCourse.id = thelist.getInt("id");
                            mCourse.title = thelist.getString("title");
                            mCourse.done = thelist.getBoolean("done");
                            adminActivity.tasklist.add(mTask);

                        }
                        */
                    }

                    connection.disconnect();

                    break;
                case "POST":
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");

                    connection.connect();
                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    ///String outputString = "{ \"title\":\"lennis ändrade lista\" }";
                    String outputString = json.toString();
                    writer.write(outputString);
                    writer.flush();
                    writer.close();
                    os.close();

                    BufferedReader in2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine2;
                    StringBuffer response2 = new StringBuffer();

                    while ((inputLine2 = in2.readLine()) != null) {
                        response2.append(inputLine2);
                    }
                    in2.close();
                    responseCode = connection.getResponseCode();
                    break;

                case "PUT":
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");

                    connection.connect();
                    OutputStream os2 = connection.getOutputStream();
                    BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(os2, "UTF-8"));
                    String outputString2 = json.toString();
                    writer2.write(outputString2);
                    writer2.flush();
                    writer2.close();
                    os2.close();

                    BufferedReader in3 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine3;
                    StringBuffer response3 = new StringBuffer();

                    while ((inputLine3 = in3.readLine()) != null) {
                        response3.append(inputLine3);
                    }
                    in3.close();
                    responseCode = connection.getResponseCode();

                    break;
                case "DELETE":
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.connect();
                    BufferedReader in4 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine4;
                    StringBuffer response4 = new StringBuffer();

                    while ((inputLine4 = in4.readLine()) != null) {
                        response4.append(inputLine4);
                    }
                    in4.close();
                    responseCode = connection.getResponseCode();


                    break;

                default:
                    break;
            }


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ///catch (JSONException e) {
           /// e.printStackTrace();
        ///}

        return null;
    }

    @Override
    protected void onPostExecute (String result){

        //uppdatera gränssnittet här
        ///adminActivity.showServerErrors(responseCode); ///Egen metod som visar fel
       /// adminActivity.adapter.notifyDataSetChanged(); ///Uppdaatera adapter till ListView
    }



}
