module org.example.tipcalculatorchallenge {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.tipcalculatorchallenge to javafx.fxml;
    exports org.example.tipcalculatorchallenge;
}