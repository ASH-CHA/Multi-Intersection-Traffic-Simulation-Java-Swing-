/*
 * Ashton Chavez
 * MainFrame.java
 * December 10, 2024
 * 
 * Purpose: Serves as the main GUI window for the traffic simulation. 
 * It displays traffic lights, cars, and the current time. 
 * It also provides buttons to add new intersections and cars to the simulation, 
 * updating the GUI as the simulation progresses.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel trafficPanel, carPanel, controlPanel;
    private JButton startButton, pauseButton, stopButton, resumeButton, addCarButton, addIntersectionButton;
    private ArrayList<TrafficLightPanel> trafficLightPanels;
    private ArrayList<TrafficLightSimulator> trafficLights;
    private ArrayList<CarSimulator> cars;
    private JLabel timeLabel;
    @SuppressWarnings("unused")
    private boolean isPaused = false;

    public MainFrame() {
        setTitle("CMSC-335 Final");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        timeLabel = new JLabel("Current Time: ");
        add(timeLabel, BorderLayout.NORTH);
        
        // Start time display in a separate thread
        new Thread(new TimeDisplay(timeLabel)).start();

        // Traffic Lights Panel
        trafficPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        trafficLightPanels = new ArrayList<>();
        trafficLights = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TrafficLightPanel panel = new TrafficLightPanel();
            int delay = i * 15000; // Each traffic light starts 15 seconds apart
            TrafficLightSimulator light = new TrafficLightSimulator(panel, delay);
            trafficLightPanels.add(panel);
            trafficLights.add(light);
            trafficPanel.add(panel);
        }
        add(trafficPanel, BorderLayout.CENTER);


        // Car Panel
        carPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        cars = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            JLabel carLabel = new JLabel("Car " + (i + 1) + " Distance: 0.0 meters");
            CarSimulator car = new CarSimulator("Car " + (i + 1), carLabel);
            car.setTrafficLight(trafficLights.get(i % trafficLights.size())); // Assign a traffic light to each car
            cars.add(car);
    
            carPanel.add(carLabel);  // Add JLabel to panel
    
            // Start the car thread
            Thread carThread = new Thread(car);
            carThread.start();
        }
        add(carPanel, BorderLayout.EAST);


        // Control Panel
        controlPanel = new JPanel();
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        resumeButton = new JButton("Resume");
        addCarButton = new JButton("Add Car");
        addIntersectionButton = new JButton("Add Intersection");

        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(resumeButton);
        controlPanel.add(stopButton);
        controlPanel.add(addCarButton);
        controlPanel.add(addIntersectionButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Button Listeners
        startButton.addActionListener(e -> startSimulation());
        pauseButton.addActionListener(e -> pauseSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        resumeButton.addActionListener(e -> resumeSimulation());
        addCarButton.addActionListener(e -> addCar());
        addIntersectionButton.addActionListener(e -> addIntersection());
    }


    private void startSimulation() {
        for (TrafficLightSimulator light : trafficLights) {
            if (!light.isRunning()) {
                Thread lightThread = new Thread(light);
                lightThread.start();
            }
        }
        for (CarSimulator car : cars) {
            if (!car.isRunning()) {
                Thread carThread = new Thread(car);
                carThread.start();
            }
        }
    }
    
    private void pauseSimulation() {
        isPaused = true;
        trafficLights.forEach(TrafficLightSimulator::pause);
        cars.forEach(CarSimulator::pause);
    }
    
    private void stopSimulation() {
        trafficLights.forEach(TrafficLightSimulator::cancel);
        cars.forEach(CarSimulator::cancel);
    }
    
    private void resumeSimulation() {
        isPaused = false;
        trafficLights.forEach(TrafficLightSimulator::resume);
        cars.forEach(CarSimulator::resume);
    }
    
    private void addIntersection() {
        TrafficLightPanel panel = new TrafficLightPanel();
        int delay = 5000;
        TrafficLightSimulator light = new TrafficLightSimulator(panel, delay);
        trafficLightPanels.add(panel);
        trafficLights.add(light);
        Thread intersectionThread = new Thread(light);
        intersectionThread.start();
        trafficPanel.add(panel);
        trafficPanel.revalidate();
    }

    private void addCar() {
        JLabel carLabel = new JLabel("New Car Position: 0.0 meters");
        CarSimulator car = new CarSimulator("Car " + (cars.size() + 1 + " assigned to Intersection " + trafficLights.size()), carLabel);
        car.setTrafficLight(trafficLights.get(trafficLights.size() - 1)); // Assigns to the last traffic light added
        cars.add(car);
        carPanel.add(carLabel);
        carPanel.revalidate();
        carPanel.repaint();
        Thread carThread = new Thread(car);
        carThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
