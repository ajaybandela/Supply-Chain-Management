module com.example.supplychain1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychain1 to javafx.fxml;
    exports com.example.supplychain1;
}