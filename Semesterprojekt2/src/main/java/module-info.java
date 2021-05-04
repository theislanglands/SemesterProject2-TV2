module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens org.presentation to javafx.fxml;
    opens domain to javafx.base;
    exports org.presentation;
}


