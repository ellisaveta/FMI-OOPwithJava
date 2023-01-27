module com.hw09problem3and4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires hw09;


    opens com.hw09problem3and4 to javafx.fxml;
    exports com.hw09problem3and4;
}