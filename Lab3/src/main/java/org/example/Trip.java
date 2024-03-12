package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.jar.Attributes;

    public class Trip implements Comparable<Trip>,Payable {
    private String nameCity;
    private String periodOfTime;
    private int ticketPrice;
    private ArrayList<Attraction> attractions = new ArrayList<>();

        public String getNameCity() {
            return nameCity;
        }

        public Trip(String nameCity, String periodOfTime) {
            this.nameCity = nameCity;
            this.periodOfTime = periodOfTime;
        }

        public void addAttractions (Attraction atr){
            this.attractions.add(atr);
            Collections.sort(this.attractions);
        }
        public void setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        @Override
        public double getTicketPrice() {
            return ticketPrice;
        }
        @Override
        public int compareTo(Trip other) {
            return this.nameCity.compareTo(other.getNameCity());
        }

        @Override
        public String toString() {
            return "Trip{" +
                    "nameCity='" + nameCity + '\'' +
                    ", periodOfTime='" + periodOfTime + '\'' +
                    ", ticketPrice=" + ticketPrice +
                    ", attractions=" + attractions +
                    '}';
        }
    }


