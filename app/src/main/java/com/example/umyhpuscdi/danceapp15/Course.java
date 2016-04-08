package com.example.umyhpuscdi.danceapp15;

/**
 * Created by umyhlarsle on 2016-04-08.
 */
public class Course {

    boolean done; ///Ej bestämt vad denna ska användas till
    String title; ///Den här använder vi för namnet på kursen
    String description; ///Den här byter vi ut mot
    int id; ///Används för att komma åt kursen via HTTP-anrop
    String teacher;
    String location;
    String level;
    String timeAndDate;

    public Course(){}

    public Course(boolean done, String title, String description, int id){

        this.done = done;
        this.title = title;
        this.description = description;
        this.id = id;
    }


}
