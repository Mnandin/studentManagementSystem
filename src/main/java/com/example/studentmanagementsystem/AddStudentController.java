package com.example.studentmanagementsystem;

import com.example.studentmanagementsystem.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddStudentController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private void addStudent() {
        String name = nameField.getText();
        String id = idField.getText();

        if (name.isEmpty() || id.isEmpty()) {
            showError("All fields are required");
            return;
        }

        Student newStudent = new Student(name, id);
        CourseManagement.addStudent(newStudent);

        nameField.clear();
        idField.clear();

        showConfirmation("Student added successfully");
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Operation Successful");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
