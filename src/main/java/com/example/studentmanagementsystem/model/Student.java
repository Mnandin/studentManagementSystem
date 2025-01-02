package com.example.studentmanagementsystem.model;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private String name;
    private String id;
    private Map<Course, Double> enrolledCourses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        if (enrolledCourses.containsKey(course)) {
            System.out.println("The student already enrolled in this course");
        } else {
            enrolledCourses.put(course, null);
            course.incrementEnrolledStudents();
        }
    }

    public void updateGrade(Course course, Double grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        } else {
            System.out.println("The student is not enrolled in this course");
        }
    }

    public double calculateOverallGrade() {
        double totalGrade = 0;
        int count = 0;
        for (Double grade : enrolledCourses.values()) {
            if (grade != null) {
                totalGrade += grade;
                count++;
            }
        }
        return count > 0 ? totalGrade / count : 0;
    }
}
