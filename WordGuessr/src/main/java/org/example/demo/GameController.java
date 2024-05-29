package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import java.util.*;
import java.io.*;

public class GameController {
    @FXML
    private Label feedbackLabel;
    @FXML
    private TextField userInputField;
    @FXML
    private GridPane guessGrid;
    private final String[] words;
    private String correctWord;
    private int remainingAttempts = 6;

    public GameController() throws IOException {
        String filename = "./src/main/words.txt";
        String fileContent = Utilities.readFile(filename);
        words = fileContent.split("\\r?\\n");
    }

    public void initialize() {
        startNewGame();
        userInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleGuess();
            }
        });
    }


    private void updateFeedbackLabel(String text) {
        feedbackLabel.setText(text);
    }

    @FXML
    public void startNewGame() {
        // Reset the game state, select a new word, clear the grid, etc.
        Random random = new Random();
        correctWord = words[random.nextInt(words.length)];
        remainingAttempts = 6;
        updateFeedbackLabel("Start guessing!");
        guessGrid.getChildren().clear();
        userInputField.setDisable(false);
        userInputField.requestFocus();
    }

    @FXML
    private void handleGuess() {
        String guess = userInputField.getText().toLowerCase();
        userInputField.clear();  // Clear input after each guess

        if (Utilities.IsAValidWord(guess, words) != 0) {
            updateFeedbackLabel("Invalid word!");
            return;
        }

        addGuessToGrid(guess);
        if (guess.equals(correctWord)) {
            updateFeedbackLabel("Correct! You win!");
        } else {
            remainingAttempts--;
            if (remainingAttempts > 0) {
                updateFeedbackLabel("Try again! " + remainingAttempts + " attempts remaining.");
            } else {
                updateFeedbackLabel("Game over! The correct word was: " + correctWord);
                userInputField.setDisable(true);
            }
        }
    }

    private void addGuessToGrid(String guess) {
        guess = guess.toUpperCase();
        int rowIndex = 6 - remainingAttempts;  // Calculate the row index based on the remaining attempts

        for (int i = 0; i < guess.length(); i++) {
            Label label = new Label(String.valueOf(guess.charAt(i)));
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); //
            label.setAlignment(Pos.CENTER); // Center-align the label text
            label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            // background color
            if (guess.charAt(i) == correctWord.toUpperCase().charAt(i)) {
                label.setStyle(label.getStyle() + "-fx-background-color: green; -fx-text-fill: white;");
            } else if (correctWord.toUpperCase().contains(String.valueOf(guess.charAt(i)))) {
                label.setStyle(label.getStyle() + "-fx-background-color: yellow; -fx-text-fill: black;");
            } else {
                label.setStyle(label.getStyle() + "-fx-background-color: grey; -fx-text-fill: white;");
            }

            guessGrid.add(label, i, rowIndex); // Add label to grid at the correct column (i) and row (rowIndex)
        }
    }
    @FXML
    private void handleKeyboardInput(ActionEvent event) {
        Button button = (Button) event.getSource();
        String letter = button.getText();
        userInputField.appendText(letter.toLowerCase());
    }

}
