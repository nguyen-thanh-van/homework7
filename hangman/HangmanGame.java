package hangman;

import javax.swing.*;
import java.util.ArrayList;

abstract class HangmanGame extends GameGUI implements GameLogic, TimerLogic {
    private Thread timerThread;
    private boolean timerRunning;

    @Override
    public void handleGuess(char guess) {
        if (guessedLetters.contains(guess)) {
            System.out.println("Letter already guessed.");
            return;
        }
        guessedLetters.add(guess);

        if (!currentWord.contains(String.valueOf(guess))) {
            attemptsLeft--;
        }

        updateDisplay();
        if (hasWon()) {
            endGame(true);
        } else if (attemptsLeft == 0) {
            endGame(false);
        }
    }

    @Override
    public boolean hasWon() {
        for (char c : currentWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void endGame(boolean won) {
        System.out.println(won ? "You won! The word was: " + currentWord : "Game over! The word was: " + currentWord);
        inputField.setEnabled(false);
    }

    @Override
    public void startTimer() {
        timerRunning = true;
        timerThread = new Thread(new TimerRunnable(this));
        timerThread.start();
    }
}
