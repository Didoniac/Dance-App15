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

    TextView titleOfCourse,durationOfOneCourse, priceOfCourse, location, description,status,level,danceStyle,teacher;
    String courseTitle;
   // Button butt onDone, buttonCancel;
    RelativeLayout fragment;

    String courseTitleString, priceString, locationString, teacherNameString, danceStyleString, courseStatusString,courseLevelString;

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

        courseTitle = "";

        Bundle bundle = getArguments();
        if (bundle != null) {
            int i = bundle.getInt("KEY");
            courseTitleString = mainActivity.courses.get(i).getTitle();
            price = mainActivity.courses.get(i).getPrice();
            priceString = String.valueOf(price );
            locationString = mainActivity.courses.get(i).getLocation();
            teacherNameString = mainActivity.teacherString;
            courseStatusString = mainActivity.courses.get(i).getStatus();
            courseLevelString = mainActivity.courses.get(i).getLevel();
            danceStyleString = mainActivity.courses.get(i).getDanceStyle();
        }

        View v = inflater.inflate(R.layout.fragment_read_course, container, false);

        durationOfOneCourse = (TextView) v.findViewById(R.id.durationOfOneCourse);
        priceOfCourse = (TextView) v.findViewById(R.id.priceOfCourse);
        description = (TextView) v.findViewById(R.id.description);

        teacher = (TextView) v.findViewById(R.id.teacher);
        status = (TextView) v.findViewById(R.id.status);
        level = (TextView) v.findViewById(R.id.level);
        danceStyle = (TextView) v.findViewById(R.id.danceStyle);

        location = (TextView) v.findViewById(R.id.place);
        titleOfCourse = (TextView) v.findViewById(R.id.title);

        titleOfCourse.setText(courseTitle);
        priceOfCourse.setText(priceString);
        location.setText(locationString);
        teacher.setText(teacherNameString);
        status.setText(courseStatusString);
        level.setText(courseLevelString);
        danceStyle.setText(danceStyleString);


        return v;
    }







}
