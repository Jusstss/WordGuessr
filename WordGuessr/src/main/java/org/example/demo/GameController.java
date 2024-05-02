package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class GameController {
    @FXML
    private Label feedbackLabel;

    @FXML
    private TextField userInputField;

    private String[] words = {"SHAKE", "SHARE", "PANIC", "AMUSE", "SHADE"};
    private String correctWord;
    private int remainingAttempts = 6;

    public void initialize() {
        startNewGame();
    }

    @FXML
    private void startNewGame() {
        Random random = new Random();
        correctWord = words[random.nextInt(words.length)];
        remainingAttempts = 6;
        updateFeedbackLabel("");
    }

    @FXML
    private void handleGuess() {
        String guess = userInputField.getText().toUpperCase();

        while (guess.length() > 5){
            updateFeedbackLabel("Word is too long!");
            return;
        }

        if (guess.equals(correctWord)) {
            updateFeedbackLabel("Correct! You win!");
        } else {
            remainingAttempts--;
            if (remainingAttempts > 0) {
                updateFeedbackLabel("Wrong! " + remainingAttempts + " attempts remaining.");
            } else {
                updateFeedbackLabel("Game over! The correct word was: " + correctWord);
            }
        }
    }

    private void updateFeedbackLabel(String text) {
        feedbackLabel.setText(text);
    }
}
