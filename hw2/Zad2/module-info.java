module arc.zad2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens arc.zad2 to javafx.fxml;
    exports arc.zad2;
}