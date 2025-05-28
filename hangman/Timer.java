package hangman;
public class Timer extends Game {
    protected Thread timerThread;
    protected boolean timerRunning;
    // TODO: Override startGame() to also start the timer
    @Override
    protected void startGame() {
        super.startGame(); // Calls the original method to reset game state
        startTimer(); // Starts the countdown timer
    }

    // TODO: Implement startTimer() to create and start the timer thread
    protected void startTimer() {
        timerRunning = true;
        timerThread = new Thread (new TimerRunnable(this));
        timerThread.start();
    }

}