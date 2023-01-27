module com.example.zad1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lines to javafx.fxml;
    exports lines;
}