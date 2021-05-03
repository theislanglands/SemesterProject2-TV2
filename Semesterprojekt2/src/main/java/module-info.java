module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.presentation to javafx.fxml;
    opens domain to javafx.base;
    exports org.presentation;
}


