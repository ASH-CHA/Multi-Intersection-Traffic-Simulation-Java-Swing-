/*
 * Ashton Chavez
 * TimeDisplay.java
 * December 10, 2024
 * 
 * Purpose: Manages and displays the current time in 1-second intervals. 
 * It runs in a separate thread, continuously updating a JLabel with the 
 * current time in HH:mm:ss format to provide real-time clock functionality within the simulation.
 */

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDisplay implements Runnable {
    private JLabel timeLabel;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public TimeDisplay(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void run() {
        while (true) {
            String currentTime = timeFormat.format(new Date());
            SwingUtilities.invokeLater(() -> timeLabel.setText("Current Time: " + currentTime));
            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
