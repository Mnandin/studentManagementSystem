package com.example.studentmanagementsystem.model;

public class Course {

    private String code;
    private String name;
    private int maximumCapacity;
    private int enrolledStudents;
    private static int totalStudents = 0;

    public Course(String code, String name, int maximumCapacity) {
        this.code = code;
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return code;
    }

    public String getCourseName() {
        return name;
    }

    public int getMaxCapacity() {
        return maximumCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public static int getTotalEnrolledStudents() {
        return totalStudents;
    }

    public void incrementEnrolledStudents(){
        if(enrolledStudents < maximumCapacity){
            enrolledStudents++;
            totalStudents++;
        } else {
            System.out.println("The course has reached its maximum capacity.");
        }
    }

}
