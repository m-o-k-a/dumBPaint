package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.Random;

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

    double[] dotX;
    double[] dotY;

    public void menuOpacityFalse(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.8; -fx-border-color: transparent;"); }
    public void menuOpacitytrue(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.4; -fx-border-color: transparent;"); }

    public void draw(MouseEvent mouseEvent) {
        gc = canvas.getGraphicsContext2D();
        if (brush == BrushEnum.ERASE) { gc.setFill(Color.WHITE); }
        else { gc.setFill(color); }
        drawShape(mouseEvent);
    }

    private void drawShape(MouseEvent mouseEvent) {
        switch (brush) {
            case PENCIL:
                gc.fillRect(mouseEvent.getX(), mouseEvent.getY(), size, size);
                break;
            case BUCKET:
                gc.fillRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
                break;
            case ERASE:
            case CIRCLE:
                gc.fillOval(mouseEvent.getX(), mouseEvent.getY(), size, size);
                break;
            case SQUARE:
                gc.fillRect(mouseEvent.getX(), mouseEvent.getY(), size, size);
                break;
            case TRIANGLE:
                dotX = new double[4];
                dotX[0] = mouseEvent.getX(); dotX[1] = mouseEvent.getX() + size; dotX[2] = mouseEvent.getX() - size;
                dotX[3] = mouseEvent.getX();
                dotY = new double[4];
                dotY[0] = mouseEvent.getY(); dotY[1] = mouseEvent.getY() + size; dotY[2] = mouseEvent.getY() + size;
                dotY[3] = mouseEvent.getY();
                gc.fillPolygon(dotX, dotY, 4);
                break;
            case CROSSYES:
                dotX = new double[5];
                dotX[0] = mouseEvent.getX(); dotX[1] = mouseEvent.getX() + size; dotX[2] = mouseEvent.getX();
                dotX[3] = mouseEvent.getX() - size; dotX[4] = mouseEvent.getX();
                dotY = new double[5];
                dotY[0] = mouseEvent.getY(); dotY[1] = mouseEvent.getY() - size; dotY[2] = mouseEvent.getY() + size;
                dotY[3] = mouseEvent.getY() - size; dotY[4] = mouseEvent.getY();
                gc.fillPolygon(dotX, dotY, 5);
                break;
            case CROSSNO:
                break;
            case WEIRDA:
                dotX = new double[6];
                dotX[0] = mouseEvent.getX() - size*2; dotX[1] = mouseEvent.getX() + size; dotX[2] = mouseEvent.getX();
                dotX[3] = mouseEvent.getX() - size; dotX[4] = mouseEvent.getX()+size*2; dotX[5] = mouseEvent.getX() - size;
                dotY = new double[6];
                dotY[0] = mouseEvent.getY(); dotY[1] = mouseEvent.getY() - size; dotY[2] = mouseEvent.getY() + size*2;
                dotY[3] = mouseEvent.getY() - size; dotY[4] = mouseEvent.getY() + size*3; dotY[5] = mouseEvent.getY()+size;
                gc.fillPolygon(dotX, dotY, 5);
                break;
        }

    }

    public void clear(ActionEvent actionEvent) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
    }

    public void setSmall(ActionEvent actionEvent) { size = 1; }
    public void setMedium(ActionEvent actionEvent) { size = 5; }
    public void setLarge(ActionEvent actionEvent) { size = 10; }
    public void setXL(ActionEvent actionEvent) { size = 20; }
    public void setXXL(ActionEvent actionEvent) { size = 40; }
    public void setXXXL(ActionEvent actionEvent) { size = 60; }

    public void setPencil(ActionEvent actionEvent) { brush = BrushEnum.PENCIL; }
    public void setBucket(ActionEvent actionEvent) { brush = BrushEnum.BUCKET; }
    public void setErase(ActionEvent actionEvent) { brush = BrushEnum.ERASE; }
    public void setSquare(ActionEvent actionEvent) { brush = BrushEnum.SQUARE;}
    public void setCircle(ActionEvent actionEvent) { brush = BrushEnum.CIRCLE; }
    public void setTriangle(ActionEvent actionEvent) { brush = BrushEnum.TRIANGLE;}
    public void setcrossYes(ActionEvent actionEvent) { brush = BrushEnum.CROSSYES; }
    public void setcrossNo(ActionEvent actionEvent) { brush = BrushEnum.CROSSNO; }
    public void setWeirdA(ActionEvent actionEvent) { brush = BrushEnum.WEIRDA; }

    public void setBlack(ActionEvent actionEvent) { color = Color.BLACK; }
    public void setRed(ActionEvent actionEvent) { color = Color.RED; }
    public void setGreen(ActionEvent actionEvent) { color = Color.GREEN; }
    public void setBlue(ActionEvent actionEvent) { color = Color.BLUE; }
    public void setFuschia(ActionEvent actionEvent) { color = Color.FUCHSIA; }
    public void setYellow(ActionEvent actionEvent) { color = Color.YELLOW; }
    public void setCyan(ActionEvent actionEvent) { color = Color.CYAN; }

    private enum BrushEnum {
        PENCIL,
        BUCKET,
        ERASE,
        SQUARE,
        CIRCLE,
        TRIANGLE,
        CROSSYES,
        CROSSNO,
        WEIRDA
    }
}
