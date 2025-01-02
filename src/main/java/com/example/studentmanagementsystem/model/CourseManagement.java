package com.example.studentmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class CourseManagement {

    private static List<Course> courses = new ArrayList<Course>();
    private static List<Student> students = new ArrayList<Student>();

    public static void addCourse(String code, String name, int maxCapacity) {
        Course course = new Course(code, name, maxCapacity);
        courses.add(course);
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                break;
            }
        }
    }

    public static void enrollStudent(Student student, Course course) {
        if (!students.contains(student)) {
            students.add(student);
        }
        student.enrollCourse(course);
    }

    public static void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public static void assignGrade(Student student, Course course, double grade) {
        student.updateGrade(course, grade);
    }

    public static Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public static Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public static Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public static Course getCourseByName(String name) {
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                return course;
            }
        }
        return null;
    }


    public static void initializeRandomCourses() {
        if (courses.isEmpty()) {
            addCourse("CS101", "Computer Science 101", 30);
            addCourse("MATH101", "Mathematics 101", 25);
            addCourse("BIO101", "Biology 101", 35);
            addCourse("ENG101", "English 101", 40);
            addCourse("HIST101", "History 101", 50);
        }
    }

    public static List<Course> getAllCourses() {
        initializeRandomCourses();
        return courses;
    }
}

