package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

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
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");

    double[] dotX;
    double[] dotY;

    /**
     * menuOpacityFalse(MouseEvent mouseEvent)
     * ajust the opacity of the menu depending of the mouse position.
     * @param mouseEvent
     */
    public void menuOpacityFalse(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.8; -fx-border-color: transparent;"); }

    /**
     * menuOpacityTrue(MouseEvent mouseEvent)
     * ajust the opacity of the menu depending of the mouse position.
     * @param mouseEvent
     */
    public void menuOpacitytrue(MouseEvent mouseEvent) { menuBar.setStyle("-fx-opacity: 0.4; -fx-border-color: transparent;"); }

    /**
     * draw(MouseEvent mouseEvent)
     * if not ERASE, get the last selected color and draw the last selected shape calling the function drawShape(MouseEvent mouseEvent).
     * is ERASE, load a white color and use the brush ERASE then call the function drawShape(MouseEvent mouseEvent).
     * @param mouseEvent
     */
    public void draw(MouseEvent mouseEvent) {
        gc = canvas.getGraphicsContext2D();
        if (brush == BrushEnum.ERASE) { gc.setFill(Color.WHITE); }
        else { gc.setFill(color); }
        drawShape(mouseEvent);
    }

    /**
     * drawShape(MouseEvent mouseEvent)
     * will draw the shape corresponding of the value of the enum brush with the corresponding color.
     * @param mouseEvent
     */
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

    /**
     * clear(ActionEvent actionEvent)
     * clear the canvas by filling it in white.
     * @param actionEvent
     */
    public void clear(ActionEvent actionEvent) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
    }

    /**
     * save(ActionEvent actionEvent)
     * save the canvas in a png file.
     * @param actionEvent
     * @exception IOException
     */
    public void save(ActionEvent actionEvent) {
        menuBar.setVisible(false);
        WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
        File file = new File("dumBPaint_"+DTF.format(LocalDateTime.now())+".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            showMessageDialog(null, "Snapshot "+"dumBPaint_"+DTF.format(LocalDateTime.now())+".png successfully");
        } catch (IOException e) {
            showMessageDialog(null, "Error in the writing process");
        }
        menuBar.setVisible(true);
    }

    //The following methods are used to change the size of the brush
    public void setSmall(ActionEvent actionEvent) { size = 1; }
    public void setMedium(ActionEvent actionEvent) { size = 5; }
    public void setLarge(ActionEvent actionEvent) { size = 10; }
    public void setXL(ActionEvent actionEvent) { size = 20; }
    public void setXXL(ActionEvent actionEvent) { size = 40; }
    public void setXXXL(ActionEvent actionEvent) { size = 60; }

    //The following methods are used to change the type of the brush
    public void setPencil(ActionEvent actionEvent) { brush = BrushEnum.PENCIL; }
    public void setBucket(ActionEvent actionEvent) { brush = BrushEnum.BUCKET; }
    public void setErase(ActionEvent actionEvent) { brush = BrushEnum.ERASE; }
    public void setSquare(ActionEvent actionEvent) { brush = BrushEnum.SQUARE;}
    public void setCircle(ActionEvent actionEvent) { brush = BrushEnum.CIRCLE; }
    public void setTriangle(ActionEvent actionEvent) { brush = BrushEnum.TRIANGLE;}
    public void setcrossYes(ActionEvent actionEvent) { brush = BrushEnum.CROSSYES; }
    public void setcrossNo(ActionEvent actionEvent) { brush = BrushEnum.CROSSNO; }
    public void setWeirdA(ActionEvent actionEvent) { brush = BrushEnum.WEIRDA; }

    //The following methods are used to change the color of the brush
    public void setBlack(ActionEvent actionEvent) { color = Color.BLACK; }
    public void setRed(ActionEvent actionEvent) { color = Color.RED; }
    public void setGreen(ActionEvent actionEvent) { color = Color.GREEN; }
    public void setBlue(ActionEvent actionEvent) { color = Color.BLUE; }
    public void setFuchsia(ActionEvent actionEvent) { color = Color.FUCHSIA; }
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
