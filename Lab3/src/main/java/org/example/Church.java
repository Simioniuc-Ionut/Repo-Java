package org.example;

import java.time.LocalTime;

public class Church extends Attraction implements Visitable {


    public Church(String name, String description, String[] openDays, LocalTime openHours, double ticketPrice) {
        super(name, description, openDays, openHours, ticketPrice);
    }


    @Override
    public int compareTo(Attraction o) {
        return this.getName().compareTo(o.getName());
    }

}
