package org.example;

import java.time.LocalDate;
import java.util.*;

public class Trip implements Comparable<Trip>,Payable {
    private String nameCity;
    private Pair<LocalDate,LocalDate> periodOfTime;
    private int ticketPrice;
    private ArrayList<Attraction> attractions = new ArrayList<>();

        public String getNameCity() {
            return nameCity;
        }

    public Pair<LocalDate, LocalDate> getPeriodOfTime() {
        return periodOfTime;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public Trip(String nameCity, Pair<LocalDate,LocalDate> periodOfTime) {
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

        /**
         * pentru a sti care obiecte din cele instantiate implementeaza interfata Visitable sau Payable
         * ne folosim de "instanceof"
         */
        public void displayLocations(){
            ArrayList<Attraction> sortedBstartHour = new ArrayList<>();
            if(this.attractions!=null){
                for(Attraction a : this.attractions){
                    if(a instanceof Visitable){
                        sortedBstartHour.add(a);
                    }
                }

                sortedBstartHour.sort(Comparator.comparing(Attraction::getOpenHours));
            System.out.println("Sort visitable locations: " + sortedBstartHour);
            }else {
                System.out.println("Attractions array is empty!");
            }

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
                ", periodOfTime=" + periodOfTime.getFirst() + " - " + periodOfTime.getSecond() +
                ", ticketPrice=" + ticketPrice +
                ", attractions=" + attractions +
                '}';
    }


//    @Override
//    public void getDifferentsAttractionsPerDays() {
//    }
}


