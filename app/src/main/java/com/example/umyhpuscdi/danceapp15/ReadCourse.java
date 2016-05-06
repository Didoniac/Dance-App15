package com.example.umyhpuscdi.danceapp15;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadCourse extends DialogFragment {

    TextView titleOfCourse,level,danceStyle,
            courseDate,durationOfOneCourse,numberOfCourses,
            priceOfCourse,status,location,
            teacher,description;

    String courseTitleString,courseLevelString,danceStyleString,
            courseDateString,courseDurationString,numberOfCoursesString,
            priceString,courseStatusString,locationString,
            teacherNameString,descriptionString;

    int courseDurationInt;
    float price;

    MainActivity mainActivity;

    static Button buttonTime;

    FragmentManager fm;

    public ReadCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mainActivity = (MainActivity) getActivity();

        courseTitleString = "";

        Bundle bundle = getArguments();
        if (bundle != null) {
            int i = bundle.getInt("KEY");

            courseTitleString = mainActivity.courses.get(i).getTitle();
            courseLevelString = mainActivity.courses.get(i).getLevel();
            danceStyleString = mainActivity.courses.get(i).getDanceStyle();

            courseDateString = mainActivity.courses.get(i).getDates().get(0);
            courseDurationInt = mainActivity.courses.get(i).getCourseDurationInMinutes();
            courseDurationString = String.valueOf(courseDurationInt);
            //numberOfCourses

            price = mainActivity.courses.get(i).getPrice();
            priceString = String.valueOf(price);
            courseStatusString = mainActivity.courses.get(i).getStatus();
            locationString = mainActivity.courses.get(i).getLocation();

            teacherNameString = mainActivity.courses.get(i).getTeacher();
            descriptionString = mainActivity.courses.get(i).getDescription();
        }

        View v = inflater.inflate(R.layout.fragment_read_course, container, false);

        /////////////////////////////////////////////////////////////////

        titleOfCourse = (TextView) v.findViewById(R.id.title);
        level = (TextView) v.findViewById(R.id.level);
        danceStyle = (TextView) v.findViewById(R.id.danceStyle);

        courseDate = (TextView) v.findViewById(R.id.courseDate);
        durationOfOneCourse = (TextView) v.findViewById(R.id.durationOfOneCourse);
        numberOfCourses = (TextView) v.findViewById(R.id.numberOfCourses);

        priceOfCourse = (TextView) v.findViewById(R.id.priceOfCourse);
        status = (TextView) v.findViewById(R.id.status);
        location = (TextView) v.findViewById(R.id.place);

        teacher = (TextView) v.findViewById(R.id.teacher);
        description = (TextView) v.findViewById(R.id.description);

/////////////////////////////////////////////////////////////////

        titleOfCourse.setText(courseTitleString); // ska ligga i tabbaren
        level.setText("Nivå: "+courseLevelString);
        danceStyle.setText("Dansstil: "+danceStyleString);

        courseDate.setText("Startdatum: "+courseDateString);
        durationOfOneCourse.setText("Längd per tillfälle: "+courseDurationString+" min.");
        numberOfCourses.setText("Antal tillfällen: ");

        priceOfCourse.setText("Pris: "+priceString+" kr");
        status.setText("Status: "+courseStatusString);
        location.setText("Plats: "+ locationString);

        teacher.setText("Lärare: "+teacherNameString);
        description.setText("Beskrivning: "+descriptionString);

        return v;
    }







}
