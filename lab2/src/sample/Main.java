package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;
import sun.rmi.runtime.Log;

public class Main extends Application {
    private static final double MAX_HEIGHT = 250;
    private boolean flagDirection1 = false;
    private boolean flagDirection2 = true;
    private boolean flagDirection3 = false;
    private boolean flagDirection4 = true;
    private Pane rootPane = new Pane();
    private static final double rect1Width = 20;
    private static final double rect1Height = 110;
    private static final double rect2Width = 30;
    private static final double rect2Height = 100;
    private static final double rect3Width = 40;
    private static final double rect3Height = 120;
    private static final double rect4Width = 50;
    private static final double rect4Height = 90;
    private Rectangle rect1;
    private Rectangle rect2;
    private Rectangle rect3;
    private Rectangle rect4;
    private Text labelSquare1;
    private Text labelSquare2;
    private Text labelSquare3;
    private Text labelSquare4;
    private Text labelSquare5;
    private Task<Void> task1;
    private Task<Void> task2;
    private Task<Void> task3;
    private Task<Void> task4;
    private Task<Void> task5;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Лабораторная работа №2");
        primaryStage.setResizable(false);
        createRectangles();
        createLabels();
        primaryStage.setScene(new Scene(rootPane, 700, 500, Color.TRANSPARENT));
        primaryStage.show();
        initializeTasks();
        initializeAndStartThreads();
        primaryStage.setOnCloseRequest(event -> {
            task1.cancel();
            task2.cancel();
            task3.cancel();
            task4.cancel();
            task5.cancel();
        });
    }

    private void initializeAndStartThreads() {
        Thread thread1 = new Thread(task1);
        thread1.setName("Thread 1");
        thread1.start();
        Thread thread2 = new Thread(task2);
        thread2.setName("Thread 2");
        thread2.start();
        Thread thread3 = new Thread(task3);
        thread3.setName("Thread 3");
        thread3.start();
        Thread thread4 = new Thread(task4);
        thread4.setName("Thread 4");
        thread4.start();
        Thread thread5 = new Thread(task5);
        thread5.setName("Thread 5");
        thread5.start();
    }

    private void initializeTasks() {
        task1 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        double square = rect1.getHeight() * rect1.getWidth();
                        labelSquare1.setText(String.valueOf(square));
                        pulseRectangle1();
                    });
                    Thread.sleep(10);
                }
            }
        };
        task2 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        double square = rect2.getHeight() * rect2.getWidth();
                        labelSquare2.setText(String.valueOf(square));
                        pulseRectangle2();
                    });
                    Thread.sleep(10);
                }
            }
        };
        task3 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        double square = rect3.getHeight() * rect3.getWidth();
                        labelSquare3.setText(String.valueOf(square));
                        pulseRectangle3();
                    });
                    Thread.sleep(10);
                }
            }
        };
        task4 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        double square = rect4.getHeight() * rect4.getWidth();
                        labelSquare4.setText(String.valueOf(square));
                        pulseRectangle4();
                    });
                    Thread.sleep(10);
                }
            }
        };
        task5 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        double averageSquare = rect1.getHeight() * rect1.getWidth() + rect2.getHeight() * rect2.getWidth()
                                + rect3.getHeight() * rect3.getWidth() + rect4.getHeight() * rect4.getWidth();
                        labelSquare5.setText(String.valueOf(averageSquare));
                    });
                    Thread.sleep(10);
                }
            }
        };
    }


    private void createLabels() {
        Text mainLabel = createDisplay(200, 50, "Площади прямоугольников в пикселях", Color.RED);
        Text thread1Label = createDisplay(80, 100, "Thread 1", Color.GRAY);
        Text thread2Label = createDisplay(230, 100, "Thread 2", Color.GRAY);
        Text thread3Label = createDisplay(385, 100, "Thread 3", Color.GRAY);
        Text thread4Label = createDisplay(540, 100, "Thread 4", Color.GRAY);
        Text thread5Label = createDisplay(120, 450, "Thread 5 (Общая площадь):", Color.GRAY);
        labelSquare1 = createDisplay(90, 80, "", Color.RED);
        labelSquare2 = createDisplay(240, 80, "", Color.GREEN);
        labelSquare3 = createDisplay(395, 80, "", Color.BLUE);
        labelSquare4 = createDisplay(550, 80, "", Color.ORANGE);
        labelSquare5 = createDisplay(340, 450, "", Color.RED);
        rootPane.getChildren().addAll(mainLabel, thread1Label, thread2Label, thread3Label, thread4Label, thread5Label,
                labelSquare1, labelSquare2, labelSquare3, labelSquare4, labelSquare5);
    }

    private void createRectangles() {
        rect1 = createRectangle(100, 110, rect1Width, rect1Height, Color.RED);
        rect2 = createRectangle(250, 110, rect2Width, rect2Height, Color.GREEN);
        rect3 = createRectangle(400, 110, rect3Width, rect3Height, Color.BLUE);
        rect4 = createRectangle(550, 110, rect4Width, rect4Height, Color.ORANGE);
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

    private void pulseRectangle1() {
        if (flagDirection1) {
            rect1.setHeight(rect1.getHeight() + 1);
        } else {
            rect1.setHeight(rect1.getHeight() - 1);
        }
        if (rect1.getHeight() == 1 || rect1.getHeight() == MAX_HEIGHT) {
            flagDirection1 = !flagDirection1;
        }
    }

    private void pulseRectangle2() {
        if (flagDirection2) {
            rect2.setHeight(rect2.getHeight() + 1);
        } else {
            rect2.setHeight(rect2.getHeight() - 1);
        }
        if (rect2.getHeight() == 1 || rect2.getHeight() == MAX_HEIGHT) {
            flagDirection2 = !flagDirection2;
        }
    }

    private void pulseRectangle3() {
        if (flagDirection3) {
            rect3.setHeight(rect3.getHeight() + 1);
        } else {
            rect3.setHeight(rect3.getHeight() - 1);
        }
        if (rect3.getHeight() == 1 || rect3.getHeight() == MAX_HEIGHT) {
            flagDirection3 = !flagDirection3;
        }
    }

    private void pulseRectangle4() {
        if (flagDirection4) {
            rect4.setHeight(rect4.getHeight() + 1);
        } else {
            rect4.setHeight(rect4.getHeight() - 1);
        }
        if (rect4.getHeight() == 1 || rect4.getHeight() == MAX_HEIGHT) {
            flagDirection4 = !flagDirection4;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
