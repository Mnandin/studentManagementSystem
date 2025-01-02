package com.example.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainController {
    @FXML
    private VBox contentArea;

    @FXML
    private void showAddStudentForm() throws IOException {
        loadView("add-student.fxml");
    }

    @FXML
    private void showUpdateStudentForm() throws IOException {
        loadView("update-student.fxml");
    }

    @FXML
    private void showViewStudentDetails() throws IOException {
        loadView("view-student-details.fxml");
    }

    @FXML
    private void showEnrollStudentForm() throws IOException {
        loadView("enroll-student.fxml");
    }

    @FXML
    private void showAssignGradeForm() throws IOException {
        loadView("assign-grade.fxml");
    }

    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        VBox newView = loader.load();

        contentArea.getChildren().setAll(newView);
    }
}
