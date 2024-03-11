package org.example;

/**
 * Clasa Trucks este o clasa copil a superclasei Vehicle. Aceasta adauga in plus proprietate de capacity.
 */
public class Trucks extends Vehicle{
    private int capacity;
    public Trucks(String name, Depot depot) {
        super(name, depot);
    }
    public Trucks(String name, Depot depot,int capacity) {
        super(name, depot);
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
