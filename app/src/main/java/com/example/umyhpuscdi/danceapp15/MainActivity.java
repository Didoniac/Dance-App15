package com.example.umyhpuscdi.danceapp15;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rely_dialog_container;

    LayoutInflater inflater;
    FragmentManager fm;

    login_Dialog_Fragment loginDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String kanAllaSeDet = "Hej önskar Johan";
        String str_dhana = "Hej alla Dance grupp";

        fm = getSupportFragmentManager();
        rely_dialog_container = (RelativeLayout)findViewById(R.id.rely_dialog_container);
        inflater = getLayoutInflater();
        loginDialogFragment = new login_Dialog_Fragment();


       // View view = inflater.inflate(R.layout.fragment_login__dialog_,null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_log_in) {

            loginDialogFragment.show(fm,"logindialogfrag");


        }

        return super.onOptionsItemSelected(item);
    }
}
