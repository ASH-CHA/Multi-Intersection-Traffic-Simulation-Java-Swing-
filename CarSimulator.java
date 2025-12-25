/*
 * Ashton Chavez
 * CarSimulator.java
 * December 10, 2024
 * 
 * Purpose: Simulates the movement of a car through the intersections. 
 * It calculates the car's position and speed based on a simple distance formula 
 * (distance = speed * time) and interacts with traffic lights to stop for red lights
 *  and proceed through green or yellow lights.
 */

import javax.swing.*;

public class CarSimulator implements Runnable {
    private final String name;
    private double position = 0.0;
    private final double speed; // in km/h
    private boolean running = true;
    private boolean paused = false;
    private TrafficLightSimulator currentLight;
    private final JLabel positionLabel;

    public CarSimulator(String name, JLabel positionLabel) {
        this.name = name;
        this.speed = 60.0; // Set default speed to 60 km/h
        this.positionLabel = positionLabel;
    }

    public void setTrafficLight(TrafficLightSimulator light) {
        this.currentLight = light;
    }

    @Override
    public void run() {
        while (running) {
            try {
                if ((!paused && currentLight.getColor() == TrafficLightColor.GREEN) || (!paused && currentLight.getColor() == TrafficLightColor.YELLOW)) {
                    position += (speed / 3.6); // convert km/h to m/s
                    updatePositionLabel();
                }
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePositionLabel() {
        SwingUtilities.invokeLater(() -> positionLabel.setText(name + " Distance: " + String.format("%.2f", position) + " meters, Speed: " + speed + " km/h"));
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void cancel() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
