module com.example.tictactoefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens Game to javafx.fxml;
    exports Game;
}