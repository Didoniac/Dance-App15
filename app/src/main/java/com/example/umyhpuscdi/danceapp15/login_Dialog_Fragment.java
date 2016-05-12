package com.example.umyhpuscdi.danceapp15;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class login_Dialog_Fragment extends DialogFragment {


    public login_Dialog_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_login__dialog_, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        RelativeLayout rely_diagfrag = (RelativeLayout)view.findViewById(R.id.rely_dig_frag);
        final EditText username = (EditText)view.findViewById(R.id.edittxt_användernamn);
        final EditText password = (EditText)view.findViewById(R.id.edittxt_lösenord);
        Button btn_login = (Button)view.findViewById(R.id.btn_loggain);
        Button btn_cancel = (Button)view.findViewById(R.id.btn_avbryt);

        // Logga in
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals("") && password.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Skrive användarnamn och lösenord ...", Toast.LENGTH_SHORT).show();

                }else if (username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {

                    Toast.makeText(getContext(), "Logged In...", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).loggedIn = true;

                    ((MainActivity)getActivity()).FAB.show();
                    //TODO change to admin view.

                    dismiss();


                } else {

                    Toast.makeText(getContext(), "Fel användernämn och lösenord...", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }


               // dismiss();

            }

        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

      return view;
    }


}
