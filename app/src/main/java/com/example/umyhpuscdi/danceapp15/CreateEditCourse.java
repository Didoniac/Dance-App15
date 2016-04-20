package com.example.umyhpuscdi.danceapp15;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
public class CreateEditCourse extends DialogFragment {

    EditText editTitle, editTeacher, editTimeAndDate, editLocation, editDescription, editLevel;
    Button buttonDone, buttonCancel;
    MainActivity mainActivity;
    String contextTestTitle;


    public CreateEditCourse() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mainActivity = (MainActivity)getActivity();

        String s = "";

        Bundle bundle = getArguments();
        if (bundle != null) {
            int i = bundle.getInt("KEY");
            s = mainActivity.courses.get(i).title;
        }


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
                JSONObject descriptionObject = new JSONObject();
                try {
                    object.put("title", editTitle.getText().toString());

                    //TODO ändra till riktigt värde
                    object.put("done", true);

                    descriptionObject.put("teacher", editTeacher.getText().toString());
                    descriptionObject.put("description", editDescription.getText().toString());
                    descriptionObject.put("level", editLevel.getText().toString());
                    descriptionObject.put("location", editLocation.getText().toString());
                    descriptionObject.put("timeAndDate", editTimeAndDate.getText().toString());

                    object.put("description",descriptionObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //TODO check if the course has an id already and if so, PUT to its URI instead of POST.
                AsyncCourse asyncCourse = new AsyncCourse(mainActivity, object,0);
                asyncCourse.execute("POST", "lists/" + "258/" + "tasks/");
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


}
