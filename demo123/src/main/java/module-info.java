module com.example.demo123 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires arangodb.spark.commons;
    requires arangodb.java.driver;
    requires arangodb.spark.datasource;

    opens com.example.demo123 to javafx.fxml;
    exports com.example.demo123;
}