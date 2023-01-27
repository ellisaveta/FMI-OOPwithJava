module remote.obj {
    requires java.rmi;
    requires java.xml;

    opens com to java.rmi;
    exports com;
}