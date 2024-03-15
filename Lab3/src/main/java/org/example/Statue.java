package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue extends Attraction implements Visitable{

    public Statue(String name, String description, String[] openDays, LocalTime openHours, double ticketPrice) {
        super(name, description, openDays, openHours, ticketPrice);
    }

    @Override
    public int compareTo(Attraction o) {
        return this.getName().compareTo(o.getName());
    }

}
