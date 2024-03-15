package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public abstract class Attraction implements Comparable<Attraction> , Visitable,Payable{
    private String name;
    private String description;
    private String[]openDays;
    private LocalTime openHours;
    private LocalTime startHour;
    private LocalTime endHour;
    private double ticketPrice;

    /**
     * acest map implementeaza key ca find data iar value ca fiind timeinterval care reprezinta
     * durata de timp dintre start and finish al obiectivului.
     */
    private Map<String,TimeInterval> timetable;

    public Attraction(String name, String description, String[] openDays, LocalTime openHours, double ticketPrice) {
        this.name = name;
        this.description = description;
        this.openDays = new String[openDays.length];
        int i=0;
        for(String s : openDays){
            this.openDays[i]=s;
            i++;
        }

        this.openHours = openHours;
        this.ticketPrice = ticketPrice;
        this.timetable = new HashMap<>();
    }

    public Map<String, TimeInterval> getTimetable() {
        return timetable;
    }

    public String[] getOpenDays() {
        return openDays;
    }


    public String getName() {
        return name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
    public void setTimetable( TimeInterval value) {
        for(String key: openDays){
            this.timetable.put(key,value);
        }
        this.startHour=value.getFirst();//pun ora deschiderii din intervaul setat.
        this.endHour=value.getSecond();

    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public LocalTime getOpenHours() {
        return openHours;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", openDays='" + Arrays.toString(openDays) + '\'' +
                ", openHours='" + openHours + '\'' + " Interval : " + startHour +"-" + endHour +
               // ", ticketPrice=" + ticketPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attraction that)) return false;
        return Objects.equals(getStartHour(), that.getStartHour());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getStartHour());
        result = 31 * result + Arrays.hashCode(getOpenDays());
        return result;
    }
}
