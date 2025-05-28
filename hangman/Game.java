package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JFrame {
    // GUI components
    protected JLabel wordLabel, attemptsLabel, timeLabel;
    protected JTextField inputField;

    // The word the player has to guess (fixed for now)
    protected String currentWord = "example";

    // Stores the letters guessed by the player
    protected ArrayList<Character> guessedLetters = new ArrayList<>();

    // Number of tries left
    protected int attemptsLeft = 6;

    // Time left (not yet functional – stays at 60)
    protected int timeLeft = 60;

    public Game() {
        setTitle("Hangman Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Start button
        JButton startButton = new JButton("Start Game");
        add(startButton);

        // Word label
        wordLabel = new JLabel("Word: ", SwingConstants.CENTER);
        add(wordLabel);

        // Attempts left
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        add(attemptsLabel);

        // Time label
        timeLabel = new JLabel("Time left: " + timeLeft, SwingConstants.CENTER);
        add(timeLabel);

        // Input field
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setEnabled(false);
        add(inputField);

        // Start game action
        startButton.addActionListener(e -> {
            startGame();
            startButton.setEnabled(false);
        });

        // Input processing
        processInput();

        setVisible(true);
    }

    protected void startGame() {
        guessedLetters.clear();
        attemptsLeft = 6;
        timeLeft = 60;
        updateDisplay();
        inputField.setEnabled(true);
        inputField.requestFocus();
    }

    protected void updateDisplay() {
        StringBuilder display = new StringBuilder();
        for (char c : currentWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        wordLabel.setText("Word: " + display.toString());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        timeLabel.setText("Time left: " + timeLeft);
    }

    public void processInput() {
        inputField.addActionListener(e -> {
            String input = inputField.getText().toLowerCase();
            inputField.setText("");

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter exactly one letter.");
                return;
            }

            char guess = input.charAt(0);
            handleGuess(guess);
        });
    }

    protected void handleGuess(char guess) {
        if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed that letter.");
            return;
        }

        guessedLetters.add(guess);

        if (!currentWord.contains(String.valueOf(guess))) {
            attemptsLeft--;
        }

        updateDisplay();

        if (hasWon()) {
            endGame(true);
        } else if (attemptsLeft <= 0) {
            endGame(false);
        }
    }

    private boolean hasWon() {
        for (char c : currentWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    protected void endGame(boolean won) {
        if (won) {
            System.out.println("Congratulations! You guessed the word: " + currentWord);
        } else {
            System.out.println("Game over. The word was: " + currentWord);
        }

        inputField.setEnabled(false);
        System.exit(0); // Ends the program
    }
}
