package com.example.umyhpuscdi.danceapp15;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rely_dialog_container;

    LayoutInflater inflater;

    login_Dialog_Fragment loginDialogFragment;

    protected ArrayList<Course> courses = new ArrayList<>();

    protected ArrayAdapter adapterDanceListView;
    protected ListView listView;
   // protected CreateEditCourse dialogFrag;
   protected ReadCourse dialogFrag;
    protected FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity activity = this;

        String kanAllaSeDet = "Hej önskar Johan";
        String str_dhana = "Hej alla Dance grupp";

        fm = getSupportFragmentManager();
        rely_dialog_container = (RelativeLayout)findViewById(R.id.rely_dialog_container);
        inflater = getLayoutInflater();
        loginDialogFragment = new login_Dialog_Fragment();


       // View view = inflater.inflate(R.layout.fragment_login__dialog_,null);

        ////////////Här lägger Mats in kod-nedan ////////////////
        listView =  (ListView)findViewById(R.id.listView);
        courses = new ArrayList<>();

        buildDanceCourseList();
        // Temporär danslista för att testa
        adapterDanceListView.notifyDataSetChanged();

        fm = getSupportFragmentManager();

       // dialogFrag = new CreateEditCourse(); // Denna bortkommenterad när Mats testar
        dialogFrag = new ReadCourse(); // Denna bortkommenterad när Mats testar

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(activity, "Vald kurs:\n" + courses.get(position), Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putInt("KEY", position);
                dialogFrag.setArguments(bundle);

                dialogFrag.show(fm, "DialogFrag");


            }
        });


        ////////////Här lägger Mats in kod-ovan ////////////////

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addCourseFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*JSONObject json = new JSONObject();
                String tmpTitle = "Vår första danskurs";
                try {
                    json.put("title",tmpTitle);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AsyncCourse asynk = new AsyncCourse(activity, json, 0);
                asynk.execute("POST", "lists/" + "258" + "/tasks/"); ///Det här är String... params-arrayen
                */

                dialogFrag.show(fm, "DialogFrag");

            }
        });
    }

    protected void onResume() {
        super.onResume();
        getCoursesFromServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_log_in) {

            loginDialogFragment.show(fm,"logindialogfrag");


        }

        return super.onOptionsItemSelected(item);
    }

    public void getCoursesFromServer() {
        AsyncCourse asyncCourse = new AsyncCourse(this, null, 0);
        asyncCourse.execute("GET","lists/258/tasks/");
    }

    ////////////Här lägger Mats in kod-nedan ////////////////
    private void buildDanceCourseList(){
        adapterDanceListView = new MyArrayAdapter(this,R.layout.dance_list_item, courses);// Initsierar en arrayadapter
        listView.setAdapter(adapterDanceListView);
    }

    private class MyArrayAdapter extends ArrayAdapter<Course> { // Custom Arrayadapter, borde brytas ut
        public MyArrayAdapter(Context context, int resource, ArrayList<Course> danceCourseList) {
            super(context, resource, danceCourseList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View blowupmenu = inflater.inflate(R.layout.dance_list_item, listView, false); //Bestämmer vad som ska blåsas upp och vart


            TextView danceCourseTitle = (TextView)blowupmenu.findViewById(R.id.titleTextView);  // Namnet på danskursen
            TextView teacherTextView = (TextView)blowupmenu.findViewById(R.id.teacherTextView);
            TextView danceStyleTextView = (TextView)blowupmenu.findViewById(R.id.danceStyleTextView);
            TextView statusTextView = (TextView)blowupmenu.findViewById(R.id.statusTextView);
            danceCourseTitle.setText(courses.get(position).getTitle());
            teacherTextView.setText(courses.get(position).getTeacher());
            danceStyleTextView.setText(courses.get(position).getDanceStyle());
            statusTextView.setText(courses.get(position).getStatus());

            return blowupmenu;                                                      // Här returnerars menuitemblowup.xmls root-layout
        }
    }
////////////Här lägger Mats in kod-ovan ////////////////
}
