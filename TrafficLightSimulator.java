/*
 * Ashton Chavez
 * TrafficLightSimulator.java
 * Decemeber 10, 2024
 * 
 * Purpose: Simulates the behavior of a traffic light, switching between green, yellow, 
 * and red lights at regular intervals. It controls the timing of the light changes and 
 * interacts with CarSimulator instances to manage traffic flow through intersections.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

enum TrafficLightColor {
    RED, GREEN, YELLOW
}

class TrafficLightSimulator implements Runnable {
    private TrafficLightColor tlc;
    private boolean stop = false;
    private boolean paused = false;
    private boolean running = false;
    private ScheduledExecutorService scheduler;
    private final TrafficLightPanel lightPanel;

    public TrafficLightSimulator(TrafficLightPanel panel, int initialDelay) {
        this.tlc = TrafficLightColor.RED;
        this.lightPanel = panel;

        this.scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the light to start after the initial delay
        scheduler.scheduleAtFixedRate(this::run, initialDelay, 5000, TimeUnit.MILLISECONDS);
    }

    public synchronized TrafficLightColor getColor() {
        return tlc;
    }

    public void run() {
        running = true;
        while (!stop) {
            try {
                synchronized (this) {
                    while (paused) wait();
                }
                switch (tlc) {
                    case GREEN -> Thread.sleep(12000);
                    case YELLOW -> Thread.sleep(2000);
                    case RED -> Thread.sleep(10000);
                }
                changeColor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notify();
    }

    synchronized void changeColor() {
        switch (tlc) {
            case RED -> tlc = TrafficLightColor.GREEN;
            case GREEN -> tlc = TrafficLightColor.YELLOW;
            case YELLOW -> tlc = TrafficLightColor.RED;
        }
        lightPanel.setCurrentColor(tlc);
    }

    public synchronized void cancel() {
        stop = true;
    }

    public boolean isRunning() {
        return running;
    }
}
