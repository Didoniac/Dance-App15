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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadCourse extends Fragment {

    TextView level,danceStyle,
            courseDate,durationOfOneCourse,numberOfCourses,
            priceOfCourse,status,location,
            teacher,description;

    String courseTitleString,courseLevelString,danceStyleString,
            courseDateString,courseDurationString,numberOfCoursesString,
            priceString,courseStatusString,locationString,
            teacherNameString,descriptionString;

    Button courseAttendButton;

    int courseDurationInt;
    float price;



    UserDetailActivity userDetailActivity;
    private View v;

    static Button buttonTime;

    FragmentManager fm;

    public ReadCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_read_course, container, false);
        userDetailActivity = (UserDetailActivity) getActivity();

        /////////////////////////////////////////////////////////////////

        courseAttendButton = (Button) v.findViewById(R.id.courseAttendButton);
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

        courseTitleString = "";
        DecimalFormat df = new DecimalFormat();

        courseAttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Todo anmälan till kurs
            }
        });



        if (userDetailActivity.course != null) {
            fillInfo();
        }

        return v;
    }


    public void fillInfo() {
        assert userDetailActivity.getSupportActionBar() != null;
        userDetailActivity.getSupportActionBar().setTitle(userDetailActivity.course.getTitle());

        courseTitleString = userDetailActivity.course.getTitle();
        courseLevelString = userDetailActivity.course.getLevel();
        danceStyleString = userDetailActivity.course.getDanceStyle();

        courseDateString = userDetailActivity.course.getDates().get(0);
        courseDurationInt = userDetailActivity.course.getCourseDurationInMinutes();
        courseDurationString = String.valueOf(courseDurationInt);
        //numberOfCourses

        price = userDetailActivity.course.getPrice();
       // priceString = String.valueOf(price);

        DecimalFormat df = new DecimalFormat();

        if (Math.floor(price)== price) {
            df.setMaximumFractionDigits(0);
            priceString =  df.format(price);
        }
        else{
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(2);
            priceString =  df.format(price);
        }

        courseStatusString = userDetailActivity.course.getStatus();
        locationString = userDetailActivity.course.getLocation();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < userDetailActivity.course.getTeacher().size(); i++){
            builder.append(userDetailActivity.course.getTeacher().get(i));
            builder.append(", ");
        }

        builder.delete(builder.length()-2, builder.length()-1);
        teacherNameString = builder.toString();

        descriptionString = userDetailActivity.course.getDescription();

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
    }




}
