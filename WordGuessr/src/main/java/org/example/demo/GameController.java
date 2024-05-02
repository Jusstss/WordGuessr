package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class GameController {
    @FXML
    private Label feedbackLabel;

    @FXML
    private TextField userInputField;

    private final String filename = "./src/main/words.txt";
    private final String[] words;
    private String correctWord;
    private int remainingAttempts = 6;

    public GameController() throws IOException {
        String fileContent = Utilities.readFile(filename);
        words = fileContent.split("\\r?\\n");
    }

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
        String guess = userInputField.getText().toLowerCase();

        int validationCode = Utilities.IsAValidWord(guess, words);
        while (validationCode != 0) {
            if (validationCode == 1) {
                updateFeedbackLabel("Word isn't 5 letters!");
                return;
            }
            if (validationCode == 2) {
                updateFeedbackLabel("Word doesn't exist!");
                return;
            }
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
