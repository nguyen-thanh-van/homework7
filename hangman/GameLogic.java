package hangman;

public interface GameLogic {
    void handleGuess (char guess);
    boolean hasWon();
    void endGame();
}
