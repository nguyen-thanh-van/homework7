package hangman;

import javax.swing.SwingUtilities;

public class TimerRunnable implements Runnable {
    private final HangmanGame game;

    public TimerRunnable(HangmanGame game) {
        this.game = game;
    }

    public TimerRunnable(Timer timer, HangmanGame game) {
        this.game = game;
    }

    public TimerRunnable(Timer timer) {
    }

    @Override
    public void run() {
        while (game.timeLeft > 0 && game.timerRunning) {
            try {
                Thread.sleep(1000);
                game.timeLeft--;
                SwingUtilities.invokeLater(game::updateDisplay);
            } catch (InterruptedException e) {
                return;
            }
        }
        if (game.timeLeft == 0) {
            SwingUtilities.invokeLater(() -> game.endGame(false));
        }
    }
}
