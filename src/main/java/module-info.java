module com.mycompany.pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.mycompany.pokemon to javafx.fxml;
    exports com.mycompany.pokemon;
}
