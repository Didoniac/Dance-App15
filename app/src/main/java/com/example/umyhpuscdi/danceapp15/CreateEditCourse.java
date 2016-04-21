package com.example.umyhpuscdi.danceapp15;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
            static Button buttonTime;
    Spinner statusSpinner, levelSpinner,danceStyleSpinner,teacherSpinner;
    AdminActivity adminActivity;
    String contextTestTitle;
    TextView spinnerText;

    ArrayAdapter<String> adapterForTeacherSpinner;
    ArrayList<String> teacherList = new ArrayList<>();

    ArrayAdapter<String> adapterForStatusSpinner;
    ArrayList<String> statusList = new ArrayList<>();

    ArrayAdapter<String> adapterForLevelSpinner;
    ArrayList<String> levelList = new ArrayList<>();

    ArrayAdapter<String> adapterForDanceStyleSpinner;
    ArrayList<String> danceStyleList = new ArrayList<>();

    FragmentManager fm;

    DatePicker datePicker = new DatePicker();

    public CreateEditCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        adminActivity = (AdminActivity)getActivity();

        Bundle bundle = getArguments();
        int i = bundle.getInt("KEY");

        String s = adminActivity.danceCourseList.get(i);

        View v = inflater.inflate(R.layout.fragment_create_edit_course, container, false);
        //editTeacher = (EditText)v.findViewById(R.id.teacher);
        editDescription = (EditText)v.findViewById(R.id.description);
        durationOfOneCourse = (EditText)v.findViewById(R.id.durationOfOneCourse);
        priceOfCourse = (EditText)v.findViewById(R.id.priceOfCourse);

        teacherSpinner = (Spinner)v.findViewById(R.id.teacherSpinner);
        statusSpinner  = (Spinner)v.findViewById(R.id.statusSpinner);
        levelSpinner  = (Spinner)v.findViewById(R.id.levelSpinner);
        danceStyleSpinner  = (Spinner)v.findViewById(R.id.danceStyleSpinner);

        editLocation = (EditText)v.findViewById(R.id.place);
        //editTimeAndDate = (EditText)v.findViewById(R.id.timeAndDate);
        editTitle = (EditText)v.findViewById(R.id.title);

        buttonCancel = (Button)v.findViewById(R.id.buttonCancel);
        buttonDone = (Button)v.findViewById(R.id.buttonDone);
        buttonTime= (Button)v.findViewById(R.id.dateTimeButton);


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

        adapterForTeacherSpinner = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,teacherList);
        adapterForTeacherSpinner.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        teacherSpinner.setAdapter( adapterForTeacherSpinner);

        adapterForStatusSpinner = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,statusList);
        adapterForStatusSpinner.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        statusSpinner.setAdapter( adapterForStatusSpinner);

        adapterForLevelSpinner = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,levelList);
        adapterForLevelSpinner.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        levelSpinner.setAdapter( adapterForLevelSpinner);

        adapterForDanceStyleSpinner = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,danceStyleList);
        adapterForDanceStyleSpinner.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        danceStyleSpinner.setAdapter( adapterForDanceStyleSpinner);




        editTitle.setText(s);

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog(v);

            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject object = new JSONObject();
                try {
                    object.put("title", contextTestTitle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AsyncCourse asyncCourse = new AsyncCourse(adminActivity, object,0);
                asyncCourse.execute("POST", "lists/" + "258/" + "tasks/");
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminActivity.dialogFrag.dismiss();
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

    public void setDate(){
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
            String time = ""+year+"/"+monthOfYear+"/"+dayOfMonth+"  ";
            buttonTime.setText(time);
        }
    }



}
