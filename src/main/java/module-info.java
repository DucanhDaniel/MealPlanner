module main.mealplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;
    requires htmlunit;
    requires java.desktop;
    opens main.mealplanner to javafx.fxml;
    exports data.scraper;
    opens data.scraper to javafx.fxml;
    exports mealplanner;
    opens mealplanner to javafx.fxml;
}