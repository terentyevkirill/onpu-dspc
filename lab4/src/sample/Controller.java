package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;


public class Controller {
    @FXML
    CheckBox cb1;
    @FXML
    CheckBox cb2;
    @FXML
    CheckBox cb3;
    @FXML
    CheckBox cb4;
    @FXML
    CheckBox cb5;
    @FXML
    CheckBox cb6;
    @FXML
    CheckBox cb7;
    @FXML
    CheckBox cb8;
    @FXML
    TextField arraySizeText;
    @FXML
    TextField threadsNumText;
    @FXML
    Button buttonCalculate;
    @FXML
    TextArea textAreaOutput;
    @FXML
    GridPane checkboxGridPane;
    private Alert incorrectInputAlert;
    private Alert noProcessorsSelectedAlert;
    public static ForkJoinPool forkJoinPool;
    private int selectedProcessorsCount = 0;
    private int[] array;
    private Integer arraySize = 100000000;
    private Integer threadsNum = 100;

    @FXML
    private void initialize() {
        countProcessors();
    }

    @FXML
    private void onCheckBoxAction() {
        AtomicInteger selectedCount = new AtomicInteger(0);
        Arrays.stream(new CheckBox[]{cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8}).forEach(cb -> {
            if (cb.isSelected())
                selectedCount.set(selectedCount.get() + 1);
        });
        selectedProcessorsCount = selectedCount.get();
        System.out.println("Active processors: " + selectedProcessorsCount);
    }

    @FXML
    private void onButtonAction() {
        System.out.println("Button clicked!");
        if (selectedProcessorsCount != 0) {
            forkJoinPool = new ForkJoinPool(selectedProcessorsCount);
            arraySize = Integer.valueOf(arraySizeText.getText());
            array = generateRandomIntArray(arraySize);
            threadsNum = Integer.valueOf(threadsNumText.getText());
            if (inputIsCorrect()) {
                long startTime = System.currentTimeMillis();
                CalculateMinRecursiveTask task = new CalculateMinRecursiveTask(array, threadsNum);
                Integer maxValue = forkJoinPool.invoke(task);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                printOutput(selectedProcessorsCount, maxValue, elapsedTime);
            } else {
                incorrectInputAlert.showAndWait();
            }
        } else {
            noProcessorsSelectedAlert.showAndWait();
        }
    }

    private boolean inputIsCorrect() {
        return arraySize > 0 && threadsNum > 0;
    }

    private void printOutput(int selectedProcessorsCount, Integer minValue, long elapsedTime) {
        textAreaOutput.appendText("\nВыбрано процессоров: " + selectedProcessorsCount);
        textAreaOutput.appendText("\nМин. значение в массиве: " + minValue);
        textAreaOutput.appendText("\nВремя выполнения: " + elapsedTime);
    }

    public void setupUI() {
        noProcessorsSelectedAlert = new Alert(AlertType.ERROR);
        noProcessorsSelectedAlert.setTitle("Error!");
        noProcessorsSelectedAlert.setHeaderText("No processors selected!");
        noProcessorsSelectedAlert.setContentText("Please, select at least one processor to perform calculation!");
        incorrectInputAlert = new Alert(AlertType.ERROR);
        incorrectInputAlert.setTitle("Error!");
        incorrectInputAlert.setHeaderText("Incorrect input!");
        incorrectInputAlert.setContentText("Please check input values!");
        arraySizeText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^\\d*")) return;
            arraySizeText.setText(newValue.replaceAll("[^\\d]", ""));
        });
        threadsNumText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^\\d*")) return;
            threadsNumText.setText(newValue.replaceAll("[^\\d]", ""));
        });

    }

    private void countProcessors() {
        int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + processorCount);
        for (int i = 0; i < processorCount; i++) {
            checkboxGridPane.getChildren().get(i).setDisable(false);
        }
        textAreaOutput.setText("Доступно процессоров: " + processorCount);
    }

    private int[] generateRandomIntArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(2001) - 1000;
        }
        return array;
    }

}
