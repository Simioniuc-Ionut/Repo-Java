package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimeKeeper extends Thread {
    private final long startTime;
    private final long timeLimit;
    private AtomicBoolean running;

    public TimeKeeper( long timeLimit,AtomicBoolean running) {
        this.startTime = System.currentTimeMillis();
        this.timeLimit = timeLimit;
        this.running = running;
        setDaemon(true);
    }
    public void stopGame(){
        running.set(false);
    }
    public boolean isRunning() {
        return running.get();
    }
    @Override
    public void run() {
        while (running.get()) {
            long runningTime = System.currentTimeMillis() - startTime;
       //     System.out.println("Running time: " + runningTime / 1000 + " seconds");

            if (runningTime > timeLimit) {
                System.out.println("Time limit exceeded. Stopping game.");

                stopGame();
            }

            try {
                Thread.sleep(10);//pun pe sleep o secunda
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
