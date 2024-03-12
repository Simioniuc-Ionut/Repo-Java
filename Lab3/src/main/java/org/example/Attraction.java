package org.example;

public abstract class Attraction implements Comparable<Attraction>{
    private String name;
    private String description;
    private  String openDays;
    private String openHours;
    private double ticketPrice;

    public Attraction(String name, String description, String openDays, String openHours, double ticketPrice) {
        this.name = name;
        this.description = description;
        this.openDays = openDays;
        this.openHours = openHours;
        this.ticketPrice = ticketPrice;
    }

    public String getOpenDays() {
        return openDays;
    }

    public String getOpenHours() {
        return openHours;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", openDays='" + openDays + '\'' +
                ", openHours='" + openHours + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
