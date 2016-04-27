package com.example.umyhpuscdi.danceapp15;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEditCourse extends DialogFragment {

    EditText editTitle, durationOfOneCourse, priceOfCourse, editLocation, editDescription;
    Button buttonDone, buttonCancel;
    RelativeLayout fragment;
    String teacherName,courseStatus,courseLevel,danceStyle;


    MainActivity mainActivity;

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

    public CreateEditCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mainActivity = (MainActivity) getActivity();

        String courseTitle = "";

        Bundle bundle = getArguments();
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
                    descriptionObject.put("location", editLocation.getText().toString());
                    descriptionObject.put("price", priceOfCourse.getText().toString());
                    descriptionObject.put("courseDurationInMinutes",
                            Integer.parseInt(durationOfOneCourse.getText().toString()));

                    //TODO add multiple dates functionality.
                    ArrayList<String> dates = new ArrayList<>();
                    dates.add(buttonTime.getText().toString());
                    JSONArray tempDatesJsonArray = new JSONArray();
                    for (int i=0; i<dates.size(); i++) {
                        tempDatesJsonArray.put(dates.get(i));
                    }
                    descriptionObject.put("dates",tempDatesJsonArray);

                    //TODO add actual participants
                    ArrayList<String> participants = new ArrayList<>();
                    dates.add(buttonTime.getText().toString());
                    JSONArray tempParticipantsJsonArray = new JSONArray();
                    for (int i=0; i<participants.size(); i++) {
                        tempParticipantsJsonArray.put(participants.get(i));
                    }
                    descriptionObject.put("courseParticipants",tempParticipantsJsonArray);

                    object.put("description",descriptionObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //TODO check if the course has an id already and if so, PUT to its URI instead of POST.
                AsyncCourse asyncCourse = new AsyncCourse(mainActivity, object,0);
                asyncCourse.execute("POST", "lists/" + "258/" + "tasks/");
                dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                //adminActivity.getSupportFragmentManager().popBackStack();
            }
        });



        return v;
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
            String time = ""+year+"/"+monthOfYear+"/"+dayOfMonth+"  ";
            buttonTime.setText(time);
        }
    }



}
