package com.example.studentmanagementsystem.model;

public class Course {

    private String code;
    private String name;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCourseCode() {
        return code;
    }

    public String getCourseName() {
        return name;
    }


}
