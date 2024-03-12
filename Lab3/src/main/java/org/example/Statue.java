package org.example;

public class Statue extends Attraction implements Visitable{

    public Statue(String name, String description, String openDays, String openHours, double ticketPrice) {
        super(name, description, openDays, openHours, ticketPrice);
    }

    @Override
    public int compareTo(Attraction o) {
        return this.getName().compareTo(o.getName());
    }
}
