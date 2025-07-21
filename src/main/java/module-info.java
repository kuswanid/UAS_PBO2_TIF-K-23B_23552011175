module org.kuswanid.propertease {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.kuswanid.propertease to javafx.fxml;
    exports org.kuswanid.propertease;
}