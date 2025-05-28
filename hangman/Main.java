package hangman;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanGame() {
            @Override
            public void endGame() {

            }
        });
    }
}
