module registrationform.hw11 {
    requires javafx.controls;
    requires javafx.fxml;


    opens registrationform.hw11 to javafx.fxml;
    exports registrationform.hw11;
}