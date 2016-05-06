package com.example.umyhpuscdi.danceapp15;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Info_TabView extends Fragment {


    EditText editTitle, durationOfOneCourse, priceOfCourse, editLocation, editDescription;
    Button buttonDone, buttonCancel;
    RelativeLayout fragment;
    String teacherName,courseStatus,courseLevel,danceStyle;


    AdminDetailActivity adminDetailActivity;

    static Button buttonTime;
    Spinner statusSpinner, levelSpinner,danceStyleSpinner,teacherSpinner;

    ArrayAdapter<String> adapterForTeacherSpinner;
    ArrayList<String> teacherList = new ArrayList<>();

    ArrayAdapter<String> adapterForStatusSpinner;
    ArrayList<String> statusList = new ArrayList<>();

    ArrayAdapter<String> adapterForLevelSpinner;
    ArrayList<String> levelList = new ArrayList<>();

    ArrayAdapter<String> adapterForDanceStyleSpinner;
    ArrayList<String> danceStyleList = new ArrayList<>();

    FragmentManager fm;

    public Info_TabView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        // Inflate the layout for this fragment
        bundle = getArguments();

        adminDetailActivity = (AdminDetailActivity) getActivity();

        String courseTitle = "";
        if (bundle != null) {
            courseTitle = adminDetailActivity.course.getTitle(); //Borde gå att hämta från intetet
            Log.i("TAG", courseTitle);
        }

        View v = inflater.inflate(R.layout.fragment_create_edit_course, container, false);
        //editTeacher = (EditText)v.findViewById(R.id.teacher);
        editDescription = (EditText) v.findViewById(R.id.description);
        durationOfOneCourse = (EditText) v.findViewById(R.id.durationOfOneCourse);
        priceOfCourse = (EditText) v.findViewById(R.id.priceOfCourse);

        teacherSpinner = (Spinner) v.findViewById(R.id.teacherSpinner);
        statusSpinner = (Spinner) v.findViewById(R.id.statusSpinner);
        levelSpinner = (Spinner) v.findViewById(R.id.levelSpinner);
        danceStyleSpinner = (Spinner) v.findViewById(R.id.danceStyleSpinner);

        editLocation = (EditText) v.findViewById(R.id.place);
        //editTimeAndDate = (EditText)v.findViewById(R.id.timeAndDate);
        editTitle = (EditText) v.findViewById(R.id.title);

        buttonCancel = (Button) v.findViewById(R.id.buttonCancel);
        buttonDone = (Button) v.findViewById(R.id.buttonDone);
        buttonTime = (Button) v.findViewById(R.id.dateTimeButton);
        fragment = (RelativeLayout) v.findViewById(R.id.fragment);



        teacherList.add("Danslärare");
        teacherList.add("Anna Andersson");
        teacherList.add("Bea Bernaisesås");
        teacherList.add("Cecilia Citron");

        statusList.add("Kursstatus");
        statusList.add("Inställd");
        statusList.add("Pågående");
        statusList.add("Kommande");
        statusList.add("Avslutad");

        levelList.add("Nivå");
        levelList.add("Nybörjare");
        levelList.add("Nybörjarmedel");
        levelList.add("Medelavancerad");
        levelList.add("Avancerad");
        levelList.add("Invitational");

        danceStyleList.add("Dansstil");
        danceStyleList.add("Lindyhop");
        danceStyleList.add("Balboa");
        danceStyleList.add("Slow");
        danceStyleList.add("Autentisk jazz");

        //  adapterForTeacherSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, teacherList);
        adapterForTeacherSpinner = new ArrayAdapter(getActivity(), R.layout.spinnerlayout, teacherList);
        adapterForTeacherSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //   adapterForTeacherSpinner.setDropDownViewResource(R.layout.spinnerlayout);
        teacherSpinner.setAdapter(adapterForTeacherSpinner);

        // adapterForStatusSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, statusList);
        adapterForStatusSpinner = new ArrayAdapter(getActivity(), R.layout.spinnerlayout, statusList);
        adapterForStatusSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapterForStatusSpinner);

        // adapterForLevelSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, levelList);
        adapterForLevelSpinner = new ArrayAdapter(getActivity(), R.layout.spinnerlayout, levelList);
        adapterForLevelSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapterForLevelSpinner);

        //adapterForDanceStyleSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danceStyleList);
        adapterForDanceStyleSpinner = new ArrayAdapter(getActivity(), R.layout.spinnerlayout, danceStyleList);
        adapterForDanceStyleSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        danceStyleSpinner.setAdapter(adapterForDanceStyleSpinner);

        //fragment.setClickable(true); // För att testa

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager)adminDetailActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(fragment.getWindowToken(), 0);

                // Här ska tgt-bord döljas
                Log.i("TAG", "TGT ska döljas");

            }
        });

        editTitle.setText(courseTitle);

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog(v);
            }
        });

        teacherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                     @Override
                                                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                         teacherName = parent.getItemAtPosition(position).toString();

                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> parent) {

                                                     }
                                                 }
        );

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        courseStatus = parent.getItemAtPosition(position).toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                }
        );

        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       courseLevel = parent.getItemAtPosition(position).toString();
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {

                                                   }
                                               }
        );

        danceStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            danceStyle = parent.getItemAtPosition(position).toString();
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    }
        );




        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldsAreFilled()) {
                    JSONObject object = new JSONObject();
                    JSONObject descriptionObject = new JSONObject();
                    try {
                        object.put("title", editTitle.getText().toString());

                        descriptionObject.put("teacher", teacherName);
                        descriptionObject.put("description", editDescription.getText().toString());
                        descriptionObject.put("level", courseLevel);
                        descriptionObject.put("location", editLocation.getText().toString());
                        descriptionObject.put("status", courseStatus);
                        descriptionObject.put("danceStyle", danceStyle);
                        descriptionObject.put("price", priceOfCourse.getText().toString());
                        descriptionObject.put("courseDurationInMinutes",
                                Integer.parseInt(durationOfOneCourse.getText().toString()));

                        //TODO add multiple dates functionality.
                        ArrayList<String> dates = new ArrayList<>();
                        dates.add(buttonTime.getText().toString());
                        JSONArray tempDatesJsonArray = new JSONArray();
                        for (int i = 0; i < dates.size(); i++) {
                            tempDatesJsonArray.put(dates.get(i));
                        }
                        descriptionObject.put("dates", tempDatesJsonArray);

                        //TODO add actual participants
                        ArrayList<String> participants = new ArrayList<>();
                        dates.add(buttonTime.getText().toString());
                        JSONArray tempParticipantsJsonArray = new JSONArray();
                        for (int i = 0; i < participants.size(); i++) {
                            tempParticipantsJsonArray.put(participants.get(i));
                        }
                        descriptionObject.put("courseParticipants", tempParticipantsJsonArray);

                        object.put("description", descriptionObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (adminDetailActivity.course == null) {
                        Log.i("TAG", "POST");
                        AsyncCourse asyncCourse = new AsyncCourse(adminDetailActivity, object, 0);
                        asyncCourse.execute("POST", "lists/" + "258/" + "tasks/");
                        adminDetailActivity.onBackPressed();
                    } else {
                        Log.i("TAG", "PUT to id: " + adminDetailActivity.course.getId());
                        AsyncCourse asyncCourse = new AsyncCourse(adminDetailActivity, object, 0);
                        asyncCourse.execute("PUT", "lists/" + "258/" + "tasks/" + adminDetailActivity.course.getId() + "/");
                        adminDetailActivity.onBackPressed();
                    }
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminDetailActivity.onBackPressed();
                //adminActivity.getSupportFragmentManager().popBackStack();
            }
        });

        if (adminDetailActivity.course != null) {
            fillInfo();
        }

        return v;
    }

    private boolean fieldsAreFilled() {
        String errorString = "Du måste fylla i dessa fält:";
        boolean allFieldsAreFilled = true;
   /*   editTitle
        teacherName
        editDescription
        courseLevel
        editLocation
        courseStatus
        danceStyle
        editLocation
        priceOfCourse
        durationOfOneCourse
        buttonTime
        */

        if (editTitle.getText().toString().length() == 0) {
            errorString += "\nDanstitel";
            allFieldsAreFilled = false;
        }

        if (buttonTime.getText().toString().length() == 0) {
            errorString += "\nTid/Datum";
            allFieldsAreFilled = false;
        }

        if(durationOfOneCourse.getText().toString().length() == 0) {
            errorString += "\nLängd på kurstillfällen";
            allFieldsAreFilled = false;
        }

        if (priceOfCourse.getText().toString().length() == 0) {
            errorString += "\nKurspris";
            allFieldsAreFilled = false;
        }

        if (editLocation.getText().toString().length() == 0) {
            errorString += "\nPlats";
            allFieldsAreFilled = false;
        }

        if (teacherSpinner.getSelectedItem() == null
                || teacherSpinner.getSelectedItem().equals("Danslärare")) {
            errorString += "\nDanslärare";
            allFieldsAreFilled = false;
        }

        if (statusSpinner.getSelectedItem() == null
                || statusSpinner.getSelectedItem().equals("Kursstatus")) {
            errorString += "\nKursstatus";
            allFieldsAreFilled = false;
        }

        if (levelSpinner.getSelectedItem() == null
                || levelSpinner.getSelectedItem().equals("Nivå")) {
            errorString += "\nNivå";
            allFieldsAreFilled = false;
        }

        if (danceStyleSpinner.getSelectedItem() == null
                || danceStyleSpinner.getSelectedItem().equals("Dansstil")) {
            errorString += "\nDansstil";
            allFieldsAreFilled = false;
        }

        if (editDescription.getText().toString().length() == 0) {
            errorString += "\nBeskrivning";
            allFieldsAreFilled = false;
        }

        if (!allFieldsAreFilled) {
            Toast.makeText(adminDetailActivity, errorString, Toast.LENGTH_LONG).show();
        }
        return allFieldsAreFilled;
    }

    public void showDatePickerDialog(View v) {
        fm = getActivity().getSupportFragmentManager();// Hämtar fragmentManager från aktiviteten
        DialogFragment newFragment = new DatePicker();
        newFragment.show(fm,"datePicker");
    }

    public static class DatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        /*
         Button buttonTime;
         public DatePicker(Button buttonTime){
             this.buttonTime = buttonTime;
         }
     */
        public DatePicker(){
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // Do something with the date chosen by the user
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(year, monthOfYear, dayOfMonth, 0, 0);
            Date date = calendar.getTime();

            Format formatter =
                    new SimpleDateFormat("E yyyy-MM-dd", getResources().getConfiguration().locale); //TODO HH:mm:ss

            String time = formatter.format(date);
            buttonTime.setText(time);

            TimePicker timePicker = new TimePicker();
            timePicker.show(getFragmentManager(),"");
        }
    }

    public static class TimePicker extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnDismissListener {

        private boolean isDataSet = false;
        private String chosenTime;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default time in the picker
            final Calendar c = Calendar.getInstance();
            int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            isDataSet = true;
            chosenTime = buttonTime.getText().toString() + " " + hourOfDay + ":" + minute;
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            if (isDataSet) {  // [IF time was picked
                //set text of button, time now selected
                buttonTime.setText(chosenTime);
            } else {
                //show hint on the button again and remove its text, dialog cancelled or exited
                buttonTime.setText(null);
            }
        }
    }

    public void fillInfo() {
        editTitle.setText(adminDetailActivity.course.getTitle());
        editDescription.setText(adminDetailActivity.course.getDescription());
        editLocation.setText(adminDetailActivity.course.getLocation());
        String s = "" + adminDetailActivity.course.getPrice();
        priceOfCourse.setText(s);
        s = "" + adminDetailActivity.course.getCourseDurationInMinutes();
        durationOfOneCourse.setText(s);
        buttonTime.setText(adminDetailActivity.course.getDates().get(0));
        int i;
        for (i=0; i<levelSpinner.getCount(); i++) {
            if (levelSpinner.getItemAtPosition(i).equals(adminDetailActivity.course.getLevel())) {
                levelSpinner.setSelection(i);
            }
        }
        for (i=0; i<danceStyleSpinner.getCount(); i++) {
            if (danceStyleSpinner.getItemAtPosition(i).equals(adminDetailActivity.course.getDanceStyle())) {
                danceStyleSpinner.setSelection(i);
            }
        }
        for (i=0; i<statusSpinner.getCount(); i++) {
            if (statusSpinner.getItemAtPosition(i).equals(adminDetailActivity.course.getStatus())) {
                statusSpinner.setSelection(i);
            }
        }
        for (i=0; i<teacherSpinner.getCount(); i++) {
            if (teacherSpinner.getItemAtPosition(i).equals(adminDetailActivity.course.getTeacher())) {
                teacherSpinner.setSelection(i);
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        adminDetailActivity = (AdminDetailActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
