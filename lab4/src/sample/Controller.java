package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


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
    TextArea textAreaConsole;
    @FXML
    GridPane checkboxGridPane;

    @FXML
    private void initialize() {
        for (int i = 0; i < countCores(); i++) {
            checkboxGridPane.getChildren().get(i).setDisable(false);
        }
    }

    private int countCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    private List<Integer> generateRandomList(int capacity) {
        List<Integer> list = new ArrayList<>(capacity);
        Random rand = new Random();
        for (int i = 0; i < capacity; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }

    private void test() {
        List<Integer> array = generateRandomList(10000000);
        long start = System.currentTimeMillis();
        int max = Collections.max(array);
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(elapsedTime + " ms");
    }
}
