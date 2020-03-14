package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Main extends Application {
    private static final double MAX_HEIGHT = 250;
    private Pane rootPane = new Pane();
    private static final double rect1Width = 20;
    private static final double rect1Height = 110;
    private Rectangle rect1;
    private Rectangle rect2;
    private Rectangle rect3;
    private Rectangle rect4;

    @Override
    public void start(Stage primaryStage) {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Лабораторная работа №2");
        primaryStage.setResizable(false);
        createRectangulars();
        createLabels();
        primaryStage.setScene(new Scene(rootPane, 800, 400, Color.TRANSPARENT));
        primaryStage.show();
    }

    private void createLabels() {
        Text mainLabel = createDisplay(200, 50, "Площади прямоугольников в пикселях", Color.RED);
        Text thread1Label = createDisplay(80, 100, "Thread 1", Color.GRAY);
        Text thread2Label = createDisplay(230, 100, "Thread 2", Color.GRAY);
        Text thread3Label = createDisplay(380, 100, "Thread 3", Color.GRAY);
        Text thread4Label = createDisplay(530, 100, "Thread 4", Color.GRAY);
        Text thread5Label = createDisplay(120, 330, "Thread 5", Color.GRAY);
        rootPane.getChildren().addAll(mainLabel, thread1Label, thread2Label, thread3Label, thread4Label, thread5Label);
    }

    private void createRectangulars() {
        rect1 = createRectangle(100, 110, rect1Width, rect1Height, Color.RED);
        rect2 = createRectangle(250, 110, rect1Width, rect1Height, Color.GRAY);
        rect3 = createRectangle(400, 110, rect1Width, rect1Height, Color.BLUE);
        rect4 = createRectangle(550, 110, rect1Width, rect1Height, Color.GREEN);
        rootPane.getChildren().addAll(rect1, rect2, rect3, rect4);
    }

    Rectangle createRectangle(double x, double y, double width, double height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        return rectangle;
    }

    Text createDisplay(double x, double y, String text, Color color) {
        Text t = new Text(x, y, text);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        t.setFill(color);
        return t;
    }

    private void pulseRectangle(Rectangle rect, boolean directionFlag) {
        if (directionFlag) {
            rect.setHeight(rect.getHeight() + 1);
        } else {
            rect.setHeight(rect.getHeight() - 1);
        }
        if (rect.getHeight() == 1) {
            directionFlag = true;
        }
        if (rect.getHeight() == MAX_HEIGHT) {
            directionFlag = false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
