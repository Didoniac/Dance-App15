package com.example.umyhpuscdi.danceapp15;

import java.util.ArrayList;

/**
 * Created by umyhlarsle on 2016-04-08.
 */
public class Course {

    private String title; ///Den här använder vi för namnet på kursen
    private String description; ///Den här byter vi ut mot
    private int id; ///Används för att komma åt kursen via HTTP-anrop
    private String teacher;
    private String location;
    private String level;
    private String status;
    private String danceStyle;
    private ArrayList<String> dates = new ArrayList<>();
    private int courseDurationInMinutes;
    private ArrayList<CourseParticipant> courseParticipants = new ArrayList<>();
    private float price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    String timeAndDate;

    public Course(){}

    public Course(boolean done, String title, String description, int id){

        this.title = title;
        this.description = description;
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDanceStyle() {
        return danceStyle;
    }

    public void setDanceStyle(String danceStyle) {
        this.danceStyle = danceStyle;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public int getCourseDurationInMinutes() {
        return courseDurationInMinutes;
    }

    public void setCourseDurationInMinutes(int courseDurationInMinutes) {
        this.courseDurationInMinutes = courseDurationInMinutes;
    }

    public ArrayList<CourseParticipant> getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(ArrayList<CourseParticipant> courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
