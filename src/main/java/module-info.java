module com.example.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.example.studentmanagementsystem.model to javafx.base;
    opens com.example.studentmanagementsystem to javafx.fxml;
    exports com.example.studentmanagementsystem;
}