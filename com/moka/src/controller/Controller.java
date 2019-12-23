package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Controller {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Canvas canvas;
    @FXML
    public GraphicsContext gc;

    private BrushEnum brush = BrushEnum.PENCIL;
    private Color color = Color.BLACK;
    private int size = 1;

    public void menuOpacityFalse(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.8; -fx-border-color: transparent;"); }
    public void menuOpacitytrue(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.4; -fx-border-color: transparent;"); }

    public void draw(MouseEvent mouseEvent) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(mouseEvent.getX(), mouseEvent.getY(),size, size);
    }

    public void clear(ActionEvent actionEvent) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
    }

    public void setSmall(ActionEvent actionEvent) { size = 1; }
    public void setMedium(ActionEvent actionEvent) { size = 5; }
    public void setLarge(ActionEvent actionEvent) { size = 10; }

    private enum BrushEnum {
        PENCIL,
        BUCKET,
        ERASE,
        SQUARE,
        CIRCLE,
        TRIANGLE
    }
}
