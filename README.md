# Multi-Intersection Traffic Simulation (Java Swing)

## Description

This project is a Java Swing–based traffic simulation that models cars moving through multiple intersections controlled by traffic lights. Each traffic light cycles through red, yellow, and green states on timed intervals, while cars respond dynamically to the light color by stopping or proceeding forward. The simulation runs in real time, displaying the current system clock and allowing the user to control and expand the simulation through an interactive graphical interface.

The project demonstrates **multithreading**, **GUI programming**, and **concurrency control** in Java.

---

## How It Works

### Main GUI (`MainFrame`)

* Serves as the central window of the application.
* Displays:

  * Traffic light visuals
  * Car position updates
  * Current time (real-time clock)
* Provides control buttons:

  * **Start** – begins traffic light and car simulations
  * **Pause** – pauses all moving components
  * **Resume** – resumes paused simulations
  * **Stop** – terminates all running threads
  * **Add Car** – dynamically adds a new car
  * **Add Intersection** – dynamically adds a new traffic light

---

### Traffic Lights

* Each intersection is represented by a `TrafficLightPanel`.
* The `TrafficLightSimulator` controls:

  * Light color transitions (RED → GREEN → YELLOW → RED)
  * Timed state changes using threads and scheduling
* Each light starts at a staggered time to simulate realistic intersections.

---

### Cars

* Each car is simulated by a `CarSimulator` running in its own thread.
* Cars:

  * Move forward when the light is **green or yellow**
  * Stop when the light is **red**
  * Update their distance traveled every second
* Speed is fixed at **60 km/h**, converted to meters per second.

---

### Time Display

* A separate thread (`TimeDisplay`) continuously updates the current time.
* Time is displayed in **HH:mm:ss** format at the top of the GUI.

---

## Program Structure

* **`MainFrame.java`**

  * Main application window
  * Manages layout, controls, and simulation flow

* **`TrafficLightSimulator.java`**

  * Controls traffic light state transitions
  * Manages timing and concurrency

* **`TrafficLightPanel.java`**

  * Visual representation of traffic lights

* **`CarSimulator.java`**

  * Simulates car movement and interaction with lights

* **`TimeDisplay.java`**

  * Real-time clock running in a background thread

---

## How to Run

1. Place all `.java` files in the same directory.
2. Compile the program:

   ```bash
   javac *.java
   ```
3. Run the application:

   ```bash
   java MainFrame
   ```
4. Use the GUI buttons to start, pause, resume, and expand the simulation.

---

## Screenshots

1. **Initial Application Window**

   * Shows traffic lights, car panels, and control buttons before starting.
   * <img width="883" height="587" alt="Screenshot 2025-12-26 002624" src="https://github.com/user-attachments/assets/aa5318e0-add1-476c-a20b-e72695055fbd" />

2. **Simulation Running**

   * Traffic lights in different states (red, yellow, green).
   * Cars actively moving and updating distance.
   * <img width="883" height="588" alt="Screenshot 2025-12-26 002712" src="https://github.com/user-attachments/assets/afee9a5f-49c5-458d-b1d5-5e6c1384053e" />

3. **Add Car Button**

   * Screenshot showing a newly added car with its distance label.
   * <img width="1919" height="1032" alt="Screenshot 2025-12-26 002822" src="https://github.com/user-attachments/assets/edf7fc04-b2ad-4d73-abdb-5a0d9743146d" />

4. **Add Intersection Button**

   * Screenshot showing an additional traffic light panel added dynamically.
   * <img width="1919" height="1030" alt="Screenshot 2025-12-26 002852" src="https://github.com/user-attachments/assets/4ae5466e-9fb0-4a4a-83c4-97883ec40434" />


5. **Real-Time Clock Display**

   * Close-up showing the live clock updating at the top of the window.
   * <img width="1919" height="1030" alt="Screenshot 2025-12-26 002912" src="https://github.com/user-attachments/assets/13ecc536-4708-43d2-9325-31cf0dd21e43" />

---

## Concepts Demonstrated

* Java Swing GUI design
* Multithreading and concurrency
* Thread synchronization
* Event-driven programming
* Real-time simulation
* Object-oriented design
