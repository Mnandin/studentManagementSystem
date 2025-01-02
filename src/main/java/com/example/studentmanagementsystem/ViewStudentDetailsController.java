package com.example.studentmanagementsystem;

import com.example.studentmanagementsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class ViewStudentDetailsController {

    @FXML
    private TableView<Student> studentTable; // Table to display student details

    @FXML
    private TableColumn<Student, String> nameColumn; // Column for student name

    @FXML
    private TableColumn<Student, String> idColumn;   // Column for student ID

    @FXML
    private TableColumn<Student, String> coursesAndGradesColumn; // Column for enrolled courses

    // This method is automatically called when the controller is initialized
    @FXML
    public void initialize() {
        // Get the list of students from CourseManagement
        ObservableList<Student> studentsList = FXCollections.observableArrayList(CourseManagement.getStudents());

        // Configure the TableView columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Assuming you want to display a list of courses the student is enrolled in
        coursesAndGradesColumn.setCellValueFactory(cellData -> {
                    // Get the student from the cell data
                    Student student = cellData.getValue();

                    String coursesAndGrades = student.getEnrolledCourses().entrySet().stream()
                            .map(entry ->entry.getKey().getCourseName() + " - " + (entry.getValue() != null ? entry.getValue() : 0))
                            .collect(Collectors.joining("\n"));

                    return new javafx.beans.property.SimpleStringProperty(coursesAndGrades);
                }


        );

        // Set the items for the table
        studentTable.setItems(studentsList); // This will populate the table immediately
    }
}
