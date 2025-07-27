module org.kuswanid.propertease {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.kuswanid.propertease to javafx.fxml;
    exports org.kuswanid.propertease;
}