package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;

public class MainFormController {
    public Canvas cnvMain;
    public ColorPicker clrStroke;
    public ColorPicker clrFill;
    public AnchorPane root;
    public ToggleButton btnPencil;
    private double x1;
    private double y1;
    private Rect[] rectangles = new Rect[50];
    private int index = 0;
//    private WritableImage snapshot;

    public void initialize() {
        /* It seems like the canvas is not resizing in spite of setting the anchors */
        cnvMain.widthProperty().bind(root.widthProperty());
        cnvMain.heightProperty().bind(root.heightProperty());

        cnvMain.getGraphicsContext2D().setStroke(clrStroke.getValue());
        cnvMain.getGraphicsContext2D().setFill(clrFill.getValue());
    }

    public void toPractice() {
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
//        gc.setFill(Color.RED);
//        gc.fillRect(50,50, 150, 150);

        gc.setStroke(Color.RED);
        gc.strokeRect(50, 50, 150, 150);
        gc.clearRect(50, 50, 151, 151);

//        gc.setFill(Color.BLUE);
//        gc.fillRoundRect(250,50,150,150,20,20);

        gc.setStroke(Color.BLUE);
        gc.strokeRoundRect(250, 50, 150, 150, 20, 20);

        gc.setFont(new Font(32));
//        gc.setFill(Color.GREEN);
//        gc.fillText("IJSE", 50, 250);

        gc.setStroke(Color.GREEN);
        gc.strokeText("IJSE", 50, 250);

        gc.setFill(Color.ORANGE);
        gc.fillOval(450, 50, 150, 150);
    }

    public void cnvMainOnMouseDragged(MouseEvent mouseEvent) {
        double x2 = mouseEvent.getX();
        double y2 = mouseEvent.getY();
        double width = x2 - x1;
        double height = y2 - y1;
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
        if (!btnPencil.isSelected()){
//            gc.drawImage(snapshot, 0,0);
            gc.clearRect(0, 0, cnvMain.getWidth(), cnvMain.getHeight());
            for (int i = 0; i < index; i++) {
                Rect rect = rectangles[i];
                gc.setStroke(rect.stroke);
                gc.setFill(rect.fill);
                gc.strokeRect(rect.x, rect.y, rect.width, rect.height);
                gc.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
            gc.setFill(clrFill.getValue());
            gc.setStroke(clrStroke.getValue());
            gc.strokeRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height));
            gc.fillRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height));
        }else{
            gc.lineTo(x2, y2);
            gc.stroke();
        }
    }

    public void cnvMainOnMousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
//        snapshot = cnvMain.snapshot(new SnapshotParameters(), null);
        if (btnPencil.isSelected()){
            cnvMain.getGraphicsContext2D().beginPath();
        }
    }

    public void cnvMainOnMouseReleased(MouseEvent mouseEvent) {
        double x2 = mouseEvent.getX();
        double y2 = mouseEvent.getY();
        double width = x2 - x1;
        double height = y2 - y1;
        Rect rect = new Rect(x1, y1, width, height, clrFill.getValue(), clrStroke.getValue());
        rectangles[index++] = rect;
        System.out.println(Arrays.toString(rectangles));
    }

    public void clrFillOnAction(ActionEvent actionEvent) {
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
        gc.setFill(clrFill.getValue());
    }

    public void clrStrokeOnAction(ActionEvent actionEvent) {
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
        gc.setStroke(clrStroke.getValue());
    }
}

class Rect{
    double x;
    double y;
    double width;
    double height;
    Color fill;
    Color stroke;

    public Rect(double x, double y, double width, double height, Color fill, Color stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.stroke = stroke;
    }
}
