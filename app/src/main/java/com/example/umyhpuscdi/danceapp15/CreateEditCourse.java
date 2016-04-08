package com.example.umyhpuscdi.danceapp15;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEditCourse extends Fragment {

    EditText editTitle, editTeacher, editTimeAndDate, editLocation, editDescription, editLevel;
    Button buttonDone, buttonCancel;
    AdminActivity adminActivity;
    String contextTestTitle;


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
        editTeacher = (EditText)v.findViewById(R.id.teacher);
        editDescription = (EditText)v.findViewById(R.id.description);
        editLevel = (EditText)v.findViewById(R.id.level);
        editLocation = (EditText)v.findViewById(R.id.place);
        editTimeAndDate = (EditText)v.findViewById(R.id.timeAndDate);
        editTitle = (EditText)v.findViewById(R.id.title);

        buttonCancel = (Button)v.findViewById(R.id.buttonCancel);
        buttonDone = (Button)v.findViewById(R.id.buttonDone);

        editTitle.setText(s);

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

                adminActivity.getSupportFragmentManager().popBackStack();
            }
        });



        return v;
    }


}
