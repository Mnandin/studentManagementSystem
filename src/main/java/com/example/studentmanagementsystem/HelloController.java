package com.example.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.studentmanagementsystem.model.Student;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}