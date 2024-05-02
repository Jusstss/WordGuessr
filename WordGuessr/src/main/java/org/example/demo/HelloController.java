package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to WordGuessr Application!");
    }

    @FXML
    private void submitGuess() {

        System.out.println("Guess submitted!");
    }
}