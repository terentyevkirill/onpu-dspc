package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int MAX_VAL = 50;
    private static final int MIN_VAL = 1;

    private int counter = 0;

    private Pane pane = new Pane();
    private Group group = new Group();

    private Slider slider;
    private Button btnInc;
    private Button btnDec;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;

    private TextArea textArea;
    private DropShadow dropShadow;
    private Text text;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Лабораторная работа №1");
        primaryStage.setResizable(false);
        createGraphNodes();
        createControlNodes();
        onAction();
        pane.setOpacity(0.50);
        primaryStage.setScene(new Scene(pane, 500, 250, Color.TRANSPARENT));
        primaryStage.show();
    }

    private void onAction() {
        btnInc.setOnAction((ActionEvent event) -> {
            if (counter < MAX_VAL) {
                counter++;
            }
            toDisplay(counter);
        });

        btnDec.setOnAction((ActionEvent event) -> {
            if (counter > MIN_VAL) {
                counter--;
            }
            toDisplay(counter);
        });
        slider.valueProperty().addListener((observable) -> {
            if (slider.isValueChanging()) {
                counter = (int) slider.getValue();
                toDisplay(counter);
            }
        });

        cb1.setOnAction((ActionEvent event) -> {
            if (cb1.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 1");
            } else {
                textArea.appendText("\nСнят CheckBox 1");
            }
        });
        cb2.setOnAction((ActionEvent event) -> {
            if (cb2.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 2");
            } else {
                textArea.appendText("\nСнят CheckBox 2");
            }
        });
        cb3.setOnAction((ActionEvent event) -> {
            if (cb3.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 3");
            } else {
                textArea.appendText("\nСнят CheckBox 3");
            }
        });
        rb1.setOnAction((ActionEvent event) -> {
            textArea.appendText("\nУстановлен RadioButton 1");
        });
        rb2.setOnAction((ActionEvent event) -> {
            textArea.appendText("\nУстановлен RadioButton 2");
        });
        rb3.setOnAction((ActionEvent event) -> {
            textArea.appendText("\nУстановлен RadioButton 3");
        });
    }

    private void createControlNodes() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb1 = new RadioButton("RadioButton 1");
        rb1.setToggleGroup(toggleGroup);
        rb2 = new RadioButton("RadioButton 2");
        rb2.setToggleGroup(toggleGroup);
        rb2.setSelected(true);
        rb3 = new RadioButton("RadioButton 3");
        rb3.setToggleGroup(toggleGroup);
        cb1 = new CheckBox("CheckBox 1");
        cb2 = new CheckBox("CheckBox 2");
        cb3 = new CheckBox("CheckBox 3");
        btnInc = new Button("Увеличить");
        btnInc.setLayoutX(120);
        btnInc.setLayoutY(200);
        btnInc.setTextFill(Color.BROWN);
        btnInc.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        btnDec = new Button("Уменьшить");
        btnDec.setLayoutX(20);
        btnDec.setLayoutY(200);
        btnDec.setTextFill(Color.BROWN);
        btnDec.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        textArea = new TextArea("Лабораторная работа №1");
        textArea.setLayoutX(290);
        textArea.setLayoutY(20);
        textArea.setPrefSize(200, 150);
        textArea.setWrapText(true);

        slider = new Slider();
        slider.setLayoutX(290);
        slider.setLayoutY(200);
        slider.setPrefWidth(200);
        slider.setBlockIncrement(1.0);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(5);
        slider.setMax(MAX_VAL);
        slider.setMin(MIN_VAL);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(false);
        slider.setValue(counter);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 0, 0, 20));
        gridPane.add(rb1, 0, 0);
        gridPane.add(rb2, 0, 1);
        gridPane.add(rb3, 0, 2);
        gridPane.add(cb1, 1, 0);
        gridPane.add(cb2, 1, 1);
        gridPane.add(cb3, 1, 2);
        gridPane.add(text, 0, 8);
        group.getChildren().addAll(gridPane, slider, btnDec, btnInc, textArea);
        pane.getChildren().addAll(group);

        toDisplay(counter);


    }

    private void toDisplay(int counter) {
        String s = String.valueOf(counter);
        text.setText("Счетчик:" + s);
        textArea.appendText("\nСчетчик: " + s);
        slider.setValue(counter);
    }

    private void createGraphNodes() {
        shadow();
        group.setEffect(dropShadow);
        text = new Text();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        text.setFill(Color.BLUE);
    }

    private void shadow() {
        dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.GRAY);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
