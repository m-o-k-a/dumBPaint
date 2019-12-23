package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private MenuBar menuBar;

    public void menuOpacityFalse(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.8; -fx-border-color: transparent;"); }

    public void menuOpacitytrue(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.4; -fx-border-color: transparent;"); }
}
