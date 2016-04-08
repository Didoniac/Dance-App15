package com.example.umyhpuscdi.danceapp15;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by umyhpuscdi on 2016-04-08.
 */
public class AdminActivity extends AppCompatActivity{

    String contextTestJohan = "Hej";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final AdminActivity activity = this;

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addCourseFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json = new JSONObject();
                String tmpTitle = "Vår första danskurs";
                try {
                    json.put("title",tmpTitle);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AsyncCourse asynk = new AsyncCourse(activity, json, 0);
                asynk.execute("POST", "lists/" + "258" + "/tasks/"); ///Det här är String... params-arrayen

            }
        });
    }
}
