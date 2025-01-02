package com.example.studentmanagementsystem;

import com.example.studentmanagementsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateStudentController {

    @FXML
    private ComboBox<Student> studentComboBox;  // ComboBox for selecting a student

    @FXML
    private TextField nameField;  // TextField to update name

    @FXML
    private TextField idField;    // TextField to update ID

    @FXML
    private Button updateButton;  // Button to trigger update action

    // Method to initialize the view and populate the ComboBox with students
    @FXML
    public void initialize() {
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

        if (studentsList == null || studentsList.isEmpty()) {
            showError("No students available!");
        }

        studentComboBox.setItems(studentsList);

        studentComboBox.setOnAction(event -> displaySelectedStudentDetails());
    }

    // Method to display selected student details
    private void displaySelectedStudentDetails() {
        Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            nameField.setText(selectedStudent.getName());
            idField.setText(selectedStudent.getId());
        }
    }

    // Method to handle the "Update Student" button click
    @FXML
    private void updateStudent() {
        Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            showError("Please select a student to update.");
            return;
        }

        String updatedName = nameField.getText().trim();
        String updatedId = idField.getText().trim();

        if (updatedName.isEmpty() || updatedId.isEmpty()) {
            showError("All fields must be filled.");
            return;
        }

        selectedStudent.setName(updatedName);
        selectedStudent.setId(updatedId);

        CourseManagement.updateStudent(selectedStudent);

        showConfirmation("Student information updated successfully.");
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
