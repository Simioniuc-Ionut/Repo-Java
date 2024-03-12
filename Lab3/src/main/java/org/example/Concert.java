package org.example;

import java.time.LocalDate;

public class Concert extends Attraction implements  Payable{

    public Concert(String name, String description, String openDays, String openHours, double ticketPrice) {
        super(name, description, openDays, openHours, ticketPrice);
    }

    @Override
    public double getTicketPrice() {
        return 0;
    }

    @Override
    public int compareTo(Attraction o) {
        return this.getName().compareTo(o.getName());
    }

}
