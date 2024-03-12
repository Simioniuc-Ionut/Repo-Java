package org.example;

public class Church extends Attraction implements Visitable {


    public Church(String name, String description, String openDays, String openHours, double ticketPrice) {
        super(name, description, openDays, openHours, ticketPrice);
    }

    @Override
    public String getOpenDays() {
        return getOpenDays();
    }

    @Override
    public String getOpenHours() {
        return getOpenHours();
    }

    @Override
    public int compareTo(Attraction o) {
        return this.getName().compareTo(o.getName());
    }
}
