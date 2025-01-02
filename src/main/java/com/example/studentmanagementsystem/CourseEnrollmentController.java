package com.example.studentmanagementsystem;

import com.example.studentmanagementsystem.model.Course;
import com.example.studentmanagementsystem.model.CourseManagement;
import com.example.studentmanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseEnrollmentController {
    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private VBox studentsCheckBoxContainer;

    @FXML
    private Button enrollButton;

    @FXML
    public void initialize() {
        // Load courses into the ComboBox
        ObservableList<String> courses = FXCollections.observableArrayList();
        courses.addAll(CourseManagement.getAllCourses().stream().map(Course::getCourseName).collect(Collectors.toList()));
        courseComboBox.setItems(courses);
    }

    @FXML
    public void loadStudents() {
        studentsCheckBoxContainer.getChildren().clear();

        String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
        Course course = CourseManagement.getCourseByName(selectedCourse);

        if (course != null) {
            ObservableList<Student> eligibleStudents = FXCollections.observableArrayList(CourseManagement.getStudents());

            for (Student student : eligibleStudents) {
                CheckBox checkBox = new CheckBox(student.getName());
                checkBox.setUserData(student); // Store the student object in the CheckBox

                if (student.getEnrolledCourses().containsKey(course)) {
                    checkBox.setSelected(true);
                }

                studentsCheckBoxContainer.getChildren().add(checkBox);
            }
        }
    }

    @FXML
    public void enrollStudent() {
        String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {

            Course course = CourseManagement.getCourseByName(selectedCourse);

            List<Student> selectedStudents = getSelectedStudents();

            if (selectedStudents != null && !selectedStudents.isEmpty()) {
                for (Student student : selectedStudents) {
                    student.enrollCourse(course);
                }
                showConfirmation("Enrolled successfully!");
            } else {
                showError("Please select students.");
            }

        } else {
            showError("Please select a course.");
        }
    }

    public List<Student> getSelectedStudents() {
        List<Student> selectedStudents = new ArrayList<>();

        for (Node node : studentsCheckBoxContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    selectedStudents.add((Student) checkBox.getUserData());
                }
            }
        }
        return selectedStudents;
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
