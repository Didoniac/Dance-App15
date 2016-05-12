package com.example.umyhpuscdi.danceapp15;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    protected ReadCourse dialogFragReadCourse;
    protected FloatingActionButton FAB;
    MenuItem menuLogIn;
    MenuItem menuLogOut;

    protected FragmentManager fm;
    static protected boolean loggedIn;
    public final String KEY_NAME = "KEY_NAME";
    public static final String SAVE_AND_LOAD_LOGIN = "SAVE_AND_LOAD_LOGIN";
    String teacherString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity activity = this;
        loadData();


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

        //dialogFrag = new CreateEditCourse(); // Denna bortkommenterad när Mats testar
        //dialogFrag = new ReadCourse(); // Denna bortkommenterad när Mats testar

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (loggedIn) {
                    Log.i("TAG_FRAG","boolean"+ loggedIn);
     //               dialogFragCreateEditCourse = new CreateEditCourse(); // ska vara ReadCousrse om man ej är inloggad
       //             Toast.makeText(activity, "Vald kurs:\n" + courses.get(position), Toast.LENGTH_LONG).show();
     //               Bundle bundle = new Bundle();
     //               bundle.putInt("KEY", position);
     //               dialogFragCreateEditCourse.setArguments(bundle);
       //             dialogFragCreateEditCourse.show(fm, "dialogFragCreateEditCourse");

                    Intent intent = new Intent(MainActivity.this, AdminDetailActivity.class);
                    intent.putExtra("id", courses.get(position).getId());
                    startActivity(intent);

                }
                else {

                    Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                    intent.putExtra("id", courses.get(position).getId());
                    startActivity(intent);

             /* GAMMALT
                    Log.i("TAG_FRAG","boolean"+ loggedIn);
                    dialogFragReadCourse = new ReadCourse();
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY", position);
                    dialogFragReadCourse.setArguments(bundle);
                    dialogFragReadCourse.show(fm, "dialogFragReadCourse");
*/
                }




            }
        });


        ////////////Här lägger Mats in kod-ovan ////////////////

        FAB = (FloatingActionButton) findViewById(R.id.addCourseFAB);
        Log.i("TAG_FRAG","boolean i logged in"+ loggedIn);
        if (!loggedIn)
            FAB.hide();


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
                asynk.execute("POST", "lists/" + "2" + "/tasks/"); ///Det här är String... params-arrayen
                */

        //        dialogFragCreateEditCourse = new CreateEditCourse();
        //        dialogFragCreateEditCourse.show(fm, "DialogFrag");

                Intent intent = new Intent(MainActivity.this, AdminDetailActivity.class);
                startActivity(intent);

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

        menuLogOut = menu.findItem(R.id.action_log_out);
        menuLogIn = menu.findItem(R.id.action_log_in);

        menuLogOut.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /*
        if (id == R.id.action_settings) {
            return true;
        }
        */if (id == R.id.action_log_in) {

            loginDialogFragment.show(fm,"logindialogfrag");
        }else if(id == R.id.action_log_out){

            loggedIn = false;
            FAB.hide();
            invalidateOptionsMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);

        if(loggedIn){
            menuLogIn.setVisible(false);
            menuLogOut.setVisible(true);
        }else{
            menuLogIn.setVisible(true);
            menuLogOut.setVisible(false);
        }
        return true;
    }

    public void getCoursesFromServer() {
        AsyncCourse asyncCourse = new AsyncCourse(this, null, 0);
        asyncCourse.execute("GET","lists/2/tasks/");
    }

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
            TextView levelTextView = (TextView)blowupmenu.findViewById(R.id.levelTextView);
            TextView datesTextView = (TextView)blowupmenu.findViewById(R.id.datesTextView);
            danceCourseTitle.setText(courses.get(position).getTitle());

            danceStyleTextView.setText(courses.get(position).getDanceStyle());
            statusTextView.setText(courses.get(position).getStatus());
            levelTextView.setText(courses.get(position).getLevel());
            datesTextView.setText(courses.get(position).getDates().get(0));

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < courses.get(position).getTeacher().size(); i++){
                builder.append(courses.get(position).getTeacher().get(i).toString());
                builder.append(", ");
            }

            builder.delete(builder.length()-2, builder.length()-1);
            teacherString = builder.toString();
            teacherTextView.setText(teacherString);

            return blowupmenu;                                                      // Här returnerars menuitemblowup.xmls root-layout
        }
    }

    public void saveData(){                                                         // Sparar undan den boolan som visar om jag är inloggad
        SharedPreferences prefs = getSharedPreferences(SAVE_AND_LOAD_LOGIN,0);
        SharedPreferences.Editor editor = prefs.edit();                             // Skapar ett Editor-objekt
        editor.putBoolean(KEY_NAME, loggedIn);                                        // Skapar post
        editor.commit();                                                            // Sparar till fil
    }


    public void loadData(){                                                             // Laddar den boolan som visar om jag är inloggad
        SharedPreferences prefs = getSharedPreferences(SAVE_AND_LOAD_LOGIN, 0);           // Prefs får filnamnet där SAVE_AND_LOAD_LOGIN sparas
        loggedIn = prefs.getBoolean(KEY_NAME, false);
        saveData();
    }


    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("TAG_FRAG"," onDestroy() körs");
    }


}
