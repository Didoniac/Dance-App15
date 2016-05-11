package com.example.umyhpuscdi.danceapp15;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
 * A simple {@link DialogFragment} subclass.
 */
public class CreateEditCourse extends DialogFragment {

    EditText editTitle, durationOfOneCourse, priceOfCourse, editLocation, editDescription, numberOfCourses;
    Button buttonDone, buttonCancel;
    RelativeLayout fragment;

    String teacherName,courseStatus,courseLevel,danceStyle;

    CheckBox bea, anna, cecilia, fia;
    Boolean beaCheck, annaCheck, ceciliaCheck, fiaCheck;
    ArrayList<String> checkedTeachers = new ArrayList<>();



    MainActivity mainActivity;

            static Button buttonTime;
    Spinner statusSpinner, levelSpinner,danceStyleSpinner;



    ArrayAdapter<String> adapterForStatusSpinner;
    ArrayList<String> statusList = new ArrayList<>();

    ArrayAdapter<String> adapterForLevelSpinner;
    ArrayList<String> levelList = new ArrayList<>();

    ArrayAdapter<String> adapterForDanceStyleSpinner;
    ArrayList<String> danceStyleList = new ArrayList<>();

    FragmentManager fm;

    private Course course;

    public CreateEditCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        // Inflate the layout for this fragment
        bundle = getArguments();
        annaCheck = false;
        beaCheck = false;
        ceciliaCheck = false;
        fiaCheck = false;

        mainActivity = (MainActivity) getActivity();

        String courseTitle = "";
        if (bundle != null) {
            int i = bundle.getInt("KEY");
            courseTitle = mainActivity.courses.get(i).getTitle(); //Borde gå att hämta från intetet
            Log.i("TAG", courseTitle);
        }




        View v = inflater.inflate(R.layout.fragment_create_edit_course, container, false);
        //editTeacher = (EditText)v.findViewById(R.id.teacher);
        editDescription = (EditText) v.findViewById(R.id.description);
        durationOfOneCourse = (EditText) v.findViewById(R.id.durationOfOneCourse);
        priceOfCourse = (EditText) v.findViewById(R.id.priceOfCourse);
        numberOfCourses = (EditText)v.findViewById(R.id.numberOfCourses);

        fia = (CheckBox)v.findViewById(R.id.fiaDubbelsnurr);
        anna = (CheckBox)v.findViewById(R.id.annaAndersson);
        cecilia = (CheckBox)v.findViewById(R.id.ceciliaCitron);
        bea = (CheckBox)v.findViewById(R.id.beaBearnaisesås);

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
                ((InputMethodManager)mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(fragment.getWindowToken(), 0);

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


                        descriptionObject.put("description", editDescription.getText().toString());
                        descriptionObject.put("level", courseLevel);
                        descriptionObject.put("location", editLocation.getText().toString());
                        descriptionObject.put("status", courseStatus);
                        descriptionObject.put("danceStyle", danceStyle);
                        descriptionObject.put("price", priceOfCourse.getText().toString());
                        descriptionObject.put("courseDurationInMinutes",
                                Integer.parseInt(durationOfOneCourse.getText().toString()));
                        descriptionObject.put("numberOfCourses", Integer.parseInt(numberOfCourses.getText().toString()));


                        if(anna.isChecked()){
                            checkedTeachers.add("Anna");
                        }else {//Do nothing
                        }
                        if(cecilia.isChecked()){
                            checkedTeachers.add("Cecilia");
                        }else{//Do nothing
                        }
                        if(fia.isChecked()){
                            checkedTeachers.add("Fia");
                        }else{//Do nothing
                        }
                        if(bea.isChecked()){
                            checkedTeachers.add("Bea");
                        }else{//Do nothing
                        }

                        JSONArray tempTeachers = new JSONArray();
                        for (int i = 0; i < checkedTeachers.size(); i++){

                            tempTeachers.put(checkedTeachers.get(i));

                        }
                        descriptionObject.put("teacher", tempTeachers);


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

                    if (course == null) {
                        Log.i("TAG", "POST");
                        AsyncCourse asyncCourse = new AsyncCourse(mainActivity, object, 0);
                        asyncCourse.execute("POST", "lists/" + "258/" + "tasks/");
                                dismiss();
                    } else {
                        Log.i("TAG", "PUT to id: " + course.getId());
                        AsyncCourse asyncCourse = new AsyncCourse(mainActivity, object, 0);
                        asyncCourse.execute("PUT", "lists/" + "258/" + "tasks/" + course.getId() + "/");
                        dismiss();
                    }
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                //adminActivity.getSupportFragmentManager().popBackStack();
            }
        });

        if (bundle != null) {
            int i = bundle.getInt("KEY");
            course = mainActivity.courses.get(i);
            fillInfo();
        }

        cecilia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(), "Cecilia checkad", Toast.LENGTH_SHORT).show();
                    ceciliaCheck = isChecked;
                }else{
                    ceciliaCheck = isChecked;
                }

            }
        });
        anna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
                    annaCheck = isChecked;

                    Toast.makeText(getActivity(), "Anna checkad", Toast.LENGTH_SHORT).show();
                }else{

                    annaCheck = isChecked;
                }


            }
        });
        bea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    beaCheck = isChecked;
                    Toast.makeText(getActivity(), "Bea checkad", Toast.LENGTH_SHORT).show();
                }else{
                    beaCheck = isChecked;
                }


            }
        });
        fia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fiaCheck = isChecked;
                    Toast.makeText(getActivity(), "Fia checkad", Toast.LENGTH_SHORT).show();
                }else{
                    fiaCheck = isChecked;
                }


            }
        });

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

        if(numberOfCourses.getText().toString().length() == 0){
            errorString += "\nAntal tillfällen";
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

        if (!annaCheck && !fiaCheck && !ceciliaCheck && !beaCheck) {
            errorString += "\nLärare";
            allFieldsAreFilled = false;
        }

        if (!allFieldsAreFilled) {
            Toast.makeText(mainActivity, errorString, Toast.LENGTH_LONG).show();
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
        }
    }

    public void fillInfo() {
        editTitle.setText(course.getTitle());
        editDescription.setText(course.getDescription());
        editLocation.setText(course.getLocation());
        String s = "" + course.getPrice();
        priceOfCourse.setText(s);
        s = "" + course.getCourseDurationInMinutes();
        durationOfOneCourse.setText(s);
        String tmpNumberOfCourses = Integer.toString (course.getNumberOfCourses());
        numberOfCourses.setText(tmpNumberOfCourses);
        buttonTime.setText(course.getDates().get(0));
        int i;
        for (i=0; i<levelSpinner.getCount(); i++) {
            if (levelSpinner.getItemAtPosition(i).equals(course.getLevel())) {
                levelSpinner.setSelection(i);
            }
        }
        for (i=0; i<danceStyleSpinner.getCount(); i++) {
            if (danceStyleSpinner.getItemAtPosition(i).equals(course.getDanceStyle())) {
                danceStyleSpinner.setSelection(i);
            }
        }
        for (i=0; i<statusSpinner.getCount(); i++) {
            if (statusSpinner.getItemAtPosition(i).equals(course.getStatus())) {
                statusSpinner.setSelection(i);
            }
        }


        for (i = 0; i < course.getTeacher().size(); i++){
            if(course.getTeacher().get(i).toString().equals("Fia")){
                fia.setChecked(true);

            }
            else if(course.getTeacher().get(i).toString().equals("Bea")){
                bea.setChecked(true);

            }
            else if(course.getTeacher().get(i).toString().equals("Cecilia")){
                cecilia.setChecked(true);

            }
            else if(course.getTeacher().get(i).toString().equals("Anna")){
                anna.setChecked(true);

            }
        }

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        mainActivity = (MainActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

