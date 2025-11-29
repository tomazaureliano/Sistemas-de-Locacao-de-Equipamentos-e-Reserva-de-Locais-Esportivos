module com.lauralima {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.lauralima to javafx.fxml;
    exports com.lauralima;
}
