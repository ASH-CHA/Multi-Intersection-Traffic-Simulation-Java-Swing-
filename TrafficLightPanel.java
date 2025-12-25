/*
 * Ashton Chavez
 * TrafficLightPanel.java
 * December 10, 2024
 * 
 * Purpose: Represents the visual component of a traffic light within the GUI. 
 * It displays the current light state (green, yellow, or red) and updates its 
 * appearance based on the state of the associated TrafficLightSimulator.
 */

import javax.swing.*;
import java.awt.*;

public class TrafficLightPanel extends JPanel {
    private TrafficLightColor currentColor = TrafficLightColor.RED;  // Default to RED

    public void setCurrentColor(TrafficLightColor color) {
        this.currentColor = color;
        repaint();  // Request a repaint to update the display
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diameter = 40;  // Diameter of the circles
        int x = getWidth() / 2 - diameter / 2;
        int yRed = 10;
        int yYellow = yRed + diameter + 10;
        int yGreen = yYellow + diameter + 10;

        // Draw red circle
        g.setColor(currentColor == TrafficLightColor.RED ? Color.RED : Color.DARK_GRAY);
        g.fillOval(x, yRed, diameter, diameter);

        // Draw yellow circle
        g.setColor(currentColor == TrafficLightColor.YELLOW ? Color.YELLOW : Color.DARK_GRAY);
        g.fillOval(x, yYellow, diameter, diameter);

        // Draw green circle
        g.setColor(currentColor == TrafficLightColor.GREEN ? Color.GREEN : Color.DARK_GRAY);
        g.fillOval(x, yGreen, diameter, diameter);
    }
}
