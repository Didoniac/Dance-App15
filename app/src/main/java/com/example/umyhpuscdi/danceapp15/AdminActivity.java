package com.example.umyhpuscdi.danceapp15;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by umyhpuscdi on 2016-04-08.
 */
public class AdminActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> danceCourseList;
    ArrayAdapter adapterDanceListView;

    String contextTestJohan = "Hej";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final AdminActivity activity = this;

         ////////////Här lägger Mats in kod-nedan ////////////////
        listView =  (ListView)findViewById(R.id.listView);
        danceCourseList = new ArrayList<>();

        buildDanceCourseList();
        // Temporär danslista för att testa
        danceCourseList.add("Tango");
        danceCourseList.add("Foxtrot");
        danceCourseList.add("Vals");
        danceCourseList.add("HumpaBumpa");
        adapterDanceListView.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(activity, "Vald kurs:\n" + danceCourseList.get(position), Toast.LENGTH_LONG).show();

            }
        });


        ////////////Här lägger Mats in kod-ovan ////////////////

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

    ////////////Här lägger Mats in kod-nedan ////////////////
    private void buildDanceCourseList(){
        adapterDanceListView = new MyArrayAdapter(this,R.layout.dance_list_item, danceCourseList);// Initsierar en arrayadapter
        listView.setAdapter(adapterDanceListView);
    }

    private class MyArrayAdapter extends ArrayAdapter<String> { // Custom Arrayadapter, borde brytas ut
        public MyArrayAdapter(Context context, int resource, ArrayList<String > danceCourseList) {
            super(context, resource, danceCourseList);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View blowupmenu = inflater.inflate(R.layout.dance_list_item, listView, false); //Bestämmer vad som ska blåsas upp och vart

            TextView danceCourseTitle = (TextView)blowupmenu.findViewById(R.id.textView);  // Namnet på danskursen
            danceCourseTitle.setText(danceCourseList.get(position));

            return blowupmenu;                                                      // Här returnerars menuitemblowup.xmls root-layout
        }
    }
////////////Här lägger Mats in kod-ovan ////////////////
}
