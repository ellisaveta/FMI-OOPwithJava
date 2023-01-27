module client {
    requires java.rmi;
    requires remote.obj;

    requires javafx.controls;
    opens cipher to javafx.graphics;
}