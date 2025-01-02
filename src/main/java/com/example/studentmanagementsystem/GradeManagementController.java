package com.example.studentmanagementsystem;

import com.example.studentmanagementsystem.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import java.util.Map;

public class GradeManagementController {

    @FXML
    private ComboBox<Student> studentComboBox; // ComboBox for selecting student

    @FXML
    private TableView<Pair<Course, Double>> coursesTableView; // TableView to display student's courses and grades

    @FXML
    private TableColumn<Pair<Course, Double>, String> courseNameColumn; // Course name column
    @FXML
    private TableColumn<Pair<Course, Double>, Double> currentGradeColumn; // Current grade column

    @FXML
    private TextField gradeInput; // TextField to input the grade

    @FXML
    private Button assignGradeButton; // Button to assign grade

    // Initialize method to populate the ComboBox and setup TableView columns
    @FXML
    public void initialize() {
        // Initialize the ComboBox with the list of students
        ObservableList<Student> studentsList = FXCollections.observableArrayList();
        studentsList.addAll(CourseManagement.getStudents()); // Get list of students

        studentComboBox.setItems(studentsList);

        // Set a StringConverter to display the student id and name, but store the Student object
        studentComboBox.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (student == null || empty) {
                    setText(null);
                } else {
                    setText(student.getId() + " - " + student.getName()); // Display student ID and name
                }
            }
        });

        studentComboBox.setButtonCell(new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (student == null || empty) {
                    setText(null);
                } else {
                    setText(student.getId() + " - " + student.getName()); // Display student ID and name
                }
            }
        });

        // Initialize TableView columns
        courseNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKey().getCourseName()));
        currentGradeColumn.setCellValueFactory(cellData -> {
                    Double grade = cellData.getValue().getValue();
                    return new SimpleDoubleProperty(grade != null ? grade : 0).asObject();
                }
        );

        // Disable the assignGradeButton initially until a course is selected
        assignGradeButton.setDisable(true);
    }

    // Load courses for the selected student
    @FXML
    public void loadStudentCourses() {
        Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Create a list of courses the student is enrolled in, along with their grades
            Map<Course, Double> courses = selectedStudent.getEnrolledCourses();

            ObservableList<Pair<Course, Double>> enrolledCourses = FXCollections.observableArrayList();

            for (Map.Entry<Course, Double> entry : courses.entrySet()) {
                enrolledCourses.add(new Pair<>(entry.getKey(), entry.getValue()));
            }

            coursesTableView.setItems(enrolledCourses);

            // Enable the button only if a course is selected from the table
            coursesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                assignGradeButton.setDisable(newSelection == null);
            });
        }
    }

    // Handle grade assignment when button is clicked
    @FXML
    public void assignGrade() {
        // Get the selected student and course
        Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
        Course selectedCourse = coursesTableView.getSelectionModel().getSelectedItem().getKey();

        if (selectedStudent != null && selectedCourse != null) {

            // Get the grade from the input field
            String gradeInputText = gradeInput.getText();

            if (gradeInputText != null && !gradeInputText.isEmpty()) {
                try {
                    // Parse the input grade and assign it to the student
                    double grade = Double.parseDouble(gradeInputText);
                    CourseManagement.assignGrade(selectedStudent, selectedCourse, grade);

                    // Refresh the table to show updated grade
                    loadStudentCourses();

                    showConfirmation("Grade assigned successfully to " + selectedStudent.getName() + " for course " + selectedCourse.getCourseName());
                } catch (NumberFormatException e) {
                    showError("Invalid grade input. Please enter a valid numeric value.");
                }
            } else {
                showError("Please enter a grade.");
            }
        }
    }

    // Utility method to show error messages
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Utility method to show confirmation messages
    private void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Operation Successful");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

