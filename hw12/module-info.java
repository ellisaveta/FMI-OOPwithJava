module banks.hw12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens banks.hw12 to javafx.fxml;
    exports banks.hw12;
}