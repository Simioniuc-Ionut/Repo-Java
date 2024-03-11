package org.example;

import java.time.LocalTime;

/**
 * Clasa Drone este o clasa copil a superclasei Vehicle. Aceasta adauga in plus proprietate de duration of flight.
 */
public class Drone extends Vehicle {
    private LocalTime duration;
    public Drone(String name, Depot depot) {
        super(name, depot);
    }
    public Drone(String name, Depot depot,LocalTime duration) {
        super(name, depot);
        this.duration=duration;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
